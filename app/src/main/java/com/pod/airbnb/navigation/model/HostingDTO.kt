package com.pod.airbnb.navigation.model

import java.io.Serializable

data class HostingDTO(
    var name: String? = null,
    var isVisited: Boolean = false,
    var isFavorite: Boolean = false,
    var addedByUser: String? = null,
    var description: String? = null,
    var address: String? = null,
    var lat: Double? = null,
    var lng: Double? = null
) : Serializable