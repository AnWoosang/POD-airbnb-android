package com.pod.airbnb.navigation.model

import java.io.Serializable

data class HostingDTO(
    var addedByUser: String? = null,
    var description: String? = null,
    var favorite: Boolean = false,
    var visited: Boolean = false,
    var streetKey: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var name: String? = null,
) : Serializable