package net.dentabros.vehicle

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class GarageViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    val garageUIState : StateFlow<GarageUIState> =
        vehicleRepository.getVehicles().map {
            if (it.isEmpty())
                GarageUIState.Empty
            else
                GarageUIState.Success(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GarageUIState.Empty,
        )


}