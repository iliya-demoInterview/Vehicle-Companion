package net.dentabros.create_vehicle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.dentabros.vehicle.ModifyVehicle
import net.dentabros.vehicle.Vehicle

@Composable
fun CreateVehicleScreen(
    vehicle: MutableState<ModifyVehicle>,
    onSave : (onSuccess: () -> Unit) -> Unit,
    onSuccess: () -> Unit
) {
    Column() {
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.name,
            onValueChange = { vehicle.value = vehicle.value.copy(name = it)},
            label = { Text(text = "Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.make,
            onValueChange = { vehicle.value = vehicle.value.copy(make = it)},
            label = { Text(text = "Make") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.model,
            onValueChange = { vehicle.value = vehicle.value.copy(model = it)},
            label = { Text(text = "Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.VIN,
            onValueChange = {  vehicle.value = vehicle.value.copy(VIN = it)},
            label = { Text(text = "VIN") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onSave(onSuccess)
            }
        ) {
            Text("Save")
        }
    }
}