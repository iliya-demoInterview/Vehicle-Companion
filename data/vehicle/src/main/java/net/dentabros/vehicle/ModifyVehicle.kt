package net.dentabros.vehicle

import android.net.Uri
import androidx.core.net.toUri

data class ModifyVehicle(
    var id: Int = 0,
    var name: String = "",
    var make: String = "",
    var model: String = "",
    var year : Int = 0,
    var VIN: String = "",
    var fuelType: String = "",
    var uri: Uri = "htt".toUri()
) {

}