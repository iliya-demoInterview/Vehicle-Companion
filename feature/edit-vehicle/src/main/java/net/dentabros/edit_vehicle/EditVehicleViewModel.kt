package net.dentabros.edit_vehicle

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import net.dentabros.shared.ModifyVehicleBaseViewModel
import net.dentabros.vehicle.MutableVehicle
import net.dentabros.vehicle.VehicleRepository
import javax.inject.Inject
import kotlin.text.toLong

@HiltViewModel
internal class EditVehicleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val vehicleRepository: VehicleRepository
) : ModifyVehicleBaseViewModel() {
    private var vehicleId: String? = savedStateHandle["vehicleId"]

    init {
        getVehicle()
    }


    private fun getVehicle() {
        vehicleId?.let {
            viewModelScope.launch {
                _vehicle.value = vehicleRepository.getVehicle(it.toLong())
            }
        }
    }


    override fun save(onSuccess: () -> Unit) {
        viewModelScope.launch {
            runCatching {
                vehicleRepository.update(vehicle.value)
                onSuccess()
            }
        }
    }
}