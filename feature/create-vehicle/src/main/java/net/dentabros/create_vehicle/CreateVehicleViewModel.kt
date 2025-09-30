package net.dentabros.create_vehicle

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.dentabros.shared.ModifyVehicleBaseViewModel
import net.dentabros.vehicle.MutableVehicle
import net.dentabros.vehicle.VehicleRepository
import javax.inject.Inject


@HiltViewModel
internal class CreateVehicleViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ModifyVehicleBaseViewModel() {

    override fun save(onSuccess: () -> Unit) {
        viewModelScope.launch {
            runCatching {
                vehicleRepository.insert(vehicle.value)
                onSuccess()
            }
        }
    }
}