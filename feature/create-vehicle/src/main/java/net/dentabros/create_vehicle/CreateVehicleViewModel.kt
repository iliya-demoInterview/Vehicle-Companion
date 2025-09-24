package net.dentabros.create_vehicle

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.dentabros.vehicle.ModifyVehicle
import net.dentabros.vehicle.Vehicle
import net.dentabros.vehicle.VehicleRepository
import javax.inject.Inject


@HiltViewModel
internal class CreateVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {
    val vehicle = mutableStateOf(ModifyVehicle())

    fun createVehicle(onSuccess: () -> Unit) {
        viewModelScope.launch {
            //TODO validation
            runCatching {
                vehicleRepository.insert(vehicle.value)
                onSuccess()
            }


        }
    }
}