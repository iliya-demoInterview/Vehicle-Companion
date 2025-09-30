package net.dentabros.shared

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.dentabros.vehicle.MutableVehicle

abstract class ModifyVehicleBaseViewModel : ViewModel() {
    protected val _vehicle = MutableStateFlow<MutableVehicle>(MutableVehicle())
    val vehicle = _vehicle.asStateFlow()

    fun onTextFieldChangeEvent(event: VehicleUiEvent) {
        when (event) {
            is VehicleUiEvent.NameChanged -> {
                _vehicle.value = _vehicle.value.copy(name = event.text)
                nameHasErrors.value = event.text.isEmpty()
            }

            is VehicleUiEvent.MakeChanged -> _vehicle.value = _vehicle.value.copy(make = event.text)
            is VehicleUiEvent.ModelChanged -> _vehicle.value = _vehicle.value.copy(model = event.text)
            is VehicleUiEvent.VINChanged -> _vehicle.value = _vehicle.value.copy(VIN = event.text)
            is VehicleUiEvent.UriChanged -> _vehicle.value = _vehicle.value.copy(uri = event.uri)
        }
    }

    val nameHasErrors = MutableStateFlow<Boolean>(false)

    fun saveChanged(onSuccess: () -> Unit){
        if (vehicle.value.name.isNotEmpty()){
            save(onSuccess)
        } else {
            nameHasErrors.value = true
        }
    }

    protected abstract fun save(onSuccess: () -> Unit)
}