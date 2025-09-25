package net.dentabros.edit_vehicle.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import net.dentabros.edit_vehicle.EditVehicleViewModel
import net.dentabros.shared.ModifyVehicleScreen

const val editVehicleRoute = "edit_vehicle_route/{vehicleId}"
const val editVehicleScreen = "edit_vehicle_screen/{vehicleId}"

fun NavController.navigateToEditVehicle(vehicleId: Long, navOptions: NavOptions? = null) {
    this.navigate("edit_vehicle_route/$vehicleId", navOptions)
}


fun NavGraphBuilder.editVehicleScreen(
    onSuccess: () -> Unit
) {
    navigation(startDestination = editVehicleScreen, route = editVehicleRoute) {
        composable(route = editVehicleScreen) {
            val editVehicleViewModel: EditVehicleViewModel = hiltViewModel()

            ModifyVehicleScreen (
                vehicle = editVehicleViewModel.vehicle,
                onSave = editVehicleViewModel::update,
                onSuccess = onSuccess,
            )
        }

    }
}