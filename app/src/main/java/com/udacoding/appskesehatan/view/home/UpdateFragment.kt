package com.udacoding.appskesehatan.view.home

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacoding.appskesehatan.R
import com.udacoding.appskesehatan.data.helper.SessionManager
import com.udacoding.appskesehatan.data.utils.FilePath
import com.udacoding.appskesehatan.data.utils.persistImage
import com.udacoding.appskesehatan.databinding.FragmentUpdateBinding
import com.udacoding.appskesehatan.view.MainActivity
import com.udacoding.appskesehatan.viewmodel.crud.UpdateViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import kotlin.random.Random

class UpdateFragment : Fragment() {

    companion object{
        const val EXTRA_ID = "id"
    }

    private var image_path: String? = null
    private var viewModel: UpdateViewModel? = null
    private var session: SessionManager? = null
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

        binding.btnEditCamera.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 103)
        }

        binding.btnEditGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 104)
        }

        binding.btnEditBatal.setOnClickListener {
            val fm = HomeFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_home_container, fm, HomeFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnSubmitEdit.setOnClickListener {
            session = context?.let { SessionManager(it) }

            val id = arguments?.getString(EXTRA_ID)?.toInt()
            val user_id = session?.id?.toInt()
            val judul = binding.edtEditJudul.editText?.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val deskripsi = binding.edtEditDeskripsi.editText?.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())

            val file = File(image_path)

            val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

            viewModel?.setUpdate(id, user_id, judul, deskripsi, body)?.observe(this, Observer {
                Toast.makeText(activity, "Update sukses", Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity, MainActivity::class.java))
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 103 && resultCode == Activity.RESULT_OK) {
            val bmp = data?.extras?.get("data") as Bitmap
            binding.editImageView.setImageBitmap(bmp)

            val random = Random.nextInt(0, 999999)
            val name_file ="PurchaseImage$random"

            image_path = activity?.persistImage(bmp, name_file)

        } else if (requestCode == 104 && resultCode == Activity.RESULT_OK) {
            binding.editImageView.setImageURI(data?.data)

            image_path = context?.let { data?.data?.let { it1 -> FilePath.getPath(it, it1) } }
        }
    }
}