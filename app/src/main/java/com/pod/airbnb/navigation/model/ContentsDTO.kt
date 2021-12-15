package com.pod.airbnb.navigation.model

import android.net.Uri

data class ContentsDTO(
    var uid : String? = null,
    var user_name : String? =null,
    var email : String? = null,
    var timestamp: Long? = null,

    var accom_name : String? = null,
    var accom_type:String? = null,
    var accom_scope:String? =null,
    var accom_fee : Int? = null,
    var accom_address: String? = null,

    var photo_uri: Uri? = null,
)