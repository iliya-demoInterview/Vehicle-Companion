package net.dentabros.vehiclecompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.dentabros.create_vehicle.navigation.createVehicleScreen
import net.dentabros.create_vehicle.navigation.navigateToCreateVehicle
import net.dentabros.edit_vehicle.navigation.editVehicleScreen
import net.dentabros.edit_vehicle.navigation.navigateToEditVehicle
import net.dentabros.vehicle.navigation.vehicleRoute
import net.dentabros.vehicle.navigation.garageScreen
import net.dentabros.vehicle.navigation.navigateToVehicles
import net.dentabros.vehiclecompanion.ui.theme.VehicleCompanionTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleCompanionTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = vehicleRoute,
                        Modifier.padding(innerPadding)
                    ){
                        garageScreen(navController::navigateToCreateVehicle, navController::navigateToEditVehicle)
                        createVehicleScreen(navController::popBackStack)
                        editVehicleScreen(navController::popBackStack)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VehicleCompanionTheme {
        Greeting("Android")
    }
}