package net.dentabros.vehicle

sealed class GarageUIState {
    data object Empty : GarageUIState()
    data class Success(val data : List<Vehicle>) : GarageUIState()
}