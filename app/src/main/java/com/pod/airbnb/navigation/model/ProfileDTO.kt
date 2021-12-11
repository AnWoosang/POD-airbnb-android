package com.pod.airbnb.navigation.model

import android.net.Uri
import java.sql.Timestamp

// 이름, 성별, 이메일, 핸드폰 번호, 유저 코드, 유저의 email 주소
data class ProfileDTO(
    var name : String? = null,
    var sex : String? = null,
    var birth : Int? = null,
    var email : String? = null,
    var phone_num : String? = null,
    var uid : String? = null,
    var timestamp: Long? = null,
    var photoUri: String? = null,
    var job: String? = null,
    var address: String? = null
) {}