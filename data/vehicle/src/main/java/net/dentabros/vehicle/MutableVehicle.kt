package net.dentabros.vehicle

import android.net.Uri

data class MutableVehicle(
    var id: Long = 0,
    var name: String = "",
    var make: String = "",
    var model: String = "",
    var year : Int = 0,
    var VIN: String = "",
    var fuelType: String = "",
    var uri: Uri? = null
) {

}