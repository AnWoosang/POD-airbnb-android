package com.pod.airbnb.navigation.model

import com.google.firebase.Timestamp

data class ChattingDTO(
    var thread: Thread? = null,
    var name: String? = null
)

data class Thread(
    var content: String? = null,
    var created: Timestamp? = null,
    var senderId: String? = null,
    var senderName: String? = null
)