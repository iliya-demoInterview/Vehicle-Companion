package net.dentabros.vehiclecompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.dentabros.create_vehicle.navigation.createVehicleScreen
import net.dentabros.create_vehicle.navigation.navigateToCreateVehicle
import net.dentabros.edit_vehicle.navigation.editVehicleScreen
import net.dentabros.edit_vehicle.navigation.navigateToEditVehicle
import net.dentabros.favourites.navigation.favouritesScreen
import net.dentabros.poi.navigation.poiScreen
import net.dentabros.vehicle.navigation.vehicleRoute
import net.dentabros.vehicle.navigation.garageScreen
import net.dentabros.vehicle.navigation.navigateToVehicles
import net.dentabros.vehiclecompanion.ui.theme.VehicleCompanionTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleCompanionTheme {
                val navController = rememberNavController()
                val startDestination = Destination.GARAGE
                var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        verticalArrangement = Arrangement.Top
                    ) {


                        PrimaryTabRow(
                            selectedTabIndex = selectedDestination,
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            Destination.entries.forEachIndexed { index, destination ->
                                Tab(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = destination.route)
                                        selectedDestination = index
                                    },
                                    text = {
                                        Text(
                                            text = destination.label,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                )
                            }
                        }

                        NavHost(
                            navController = navController,
                            startDestination = startDestination.route,
                            Modifier.padding(innerPadding)
                        ) {
                            garageScreen(
                                navController::navigateToCreateVehicle,
                                navController::navigateToEditVehicle
                            )
                            createVehicleScreen(navController::popBackStack)
                            editVehicleScreen(navController::popBackStack)
                            poiScreen()
                            favouritesScreen()
                        }
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