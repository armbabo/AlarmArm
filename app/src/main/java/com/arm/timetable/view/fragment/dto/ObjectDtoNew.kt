package com.arm.timetable.view.fragment.dto

import asia.teqnological.bittoco.data.dto.BaseApi
import com.google.gson.annotations.SerializedName

class ObjectDtoNew<T> : BaseApi() {
    @SerializedName("data")
    var data: T? = null
}