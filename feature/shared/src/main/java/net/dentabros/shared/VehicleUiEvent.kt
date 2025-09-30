package net.dentabros.shared

import android.net.Uri

sealed class VehicleUiEvent() {
    data class NameChanged(val text: String) : VehicleUiEvent()
    data class MakeChanged(val text: String) : VehicleUiEvent()
    data class ModelChanged(val text: String) : VehicleUiEvent()
    data class VINChanged(val text: String) : VehicleUiEvent()
    data class UriChanged(val uri: Uri?) : VehicleUiEvent()
}