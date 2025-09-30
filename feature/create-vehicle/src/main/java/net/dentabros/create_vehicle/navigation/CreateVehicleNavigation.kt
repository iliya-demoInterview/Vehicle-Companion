package net.dentabros.create_vehicle.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.create_vehicle.CreateVehicleViewModel
import net.dentabros.shared.ModifyVehicleScreen

const val createVehicleRoute = "create_vehicle_route"
const val createVehicleScreen = "vehicle_screen"

fun NavController.navigateToCreateVehicle(navOptions: NavOptions? = null) {
    this.navigate(createVehicleRoute, navOptions)
}

fun NavGraphBuilder.createVehicleScreen(
    onSuccess : () -> Unit
) {
    navigation(startDestination = createVehicleScreen, route = createVehicleRoute) {
        composable(route = createVehicleScreen) {
            val createVehicleViewModel: CreateVehicleViewModel = hiltViewModel()
            val vehicle by createVehicleViewModel.vehicle.collectAsStateWithLifecycle()
            val nameHasErrors by createVehicleViewModel.nameHasErrors.collectAsStateWithLifecycle()
            ModifyVehicleScreen(
                vehicle = vehicle,
                onSave = createVehicleViewModel::saveChanged,
                onSuccess = onSuccess,
                onVehicleUiEvent = createVehicleViewModel::onTextFieldChangeEvent,
                nameHasErros = nameHasErrors
            )
        }

    }
}