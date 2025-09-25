package net.dentabros.vehicle

import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun GarageScreen(state: GarageUIState, createVehicle: () -> Unit, editVehicle: (Long) -> Unit) {
    when (state) {
        GarageUIState.Empty -> EmptyGarage(createVehicle)
        is GarageUIState.Success -> {
            Column {
                Button(onClick = { createVehicle() }) {
                    Text(text = "Create Vehicle")
                }
                VehiclesList(state.data, editVehicle)
            }
        }

    }
}

@Composable
fun EmptyGarage(createVehicle: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "No Vehicles"
        )

        Button(
            onClick = { createVehicle() }
        ) {
            Text(
                text = "Create Vehicle"
            )
        }
    }
}

@Composable
fun VehiclesList(vehicles: List<Vehicle>, editVehicle: (Long) -> Unit) {
    val listState = rememberLazyListState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            items(vehicles, key = { it.id }) { vehicle ->
                VehicleItem(vehicle, editVehicle)
            }
        }
    }
}

@Composable
fun VehicleItem(vehicle: Vehicle, editVehicle: (Long) -> Unit) {

    ElevatedCard() {
        Row {
            AsyncImage(
                model = vehicle.uri,
                contentDescription = "Vehicle Image",
                modifier = Modifier.size(120.dp)
            )

            Column {
                val text = "${vehicle.name} ${vehicle.make?.let { it }} ${vehicle.model}"
                Text(
                    text = text
                )

                Button(
                    onClick = {editVehicle(vehicle.id)}
                ) {
                    Text("Edit")
                }
            }

        }

    }
}