package com.udacoding.appskesehatan.model.crud

data class DataResponse(
    val data: List<DataItem>? = null,
    val isSuccess: Boolean? = null,
    val message: String? = null
)