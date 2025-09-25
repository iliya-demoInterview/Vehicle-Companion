package net.dentabros.shared

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import net.dentabros.vehicle.MutableVehicle


@Composable
fun ModifyVehicleScreen(
    vehicle: MutableState<MutableVehicle>,
    onSave: (onSuccess: () -> Unit) -> Unit,
    onSuccess: () -> Unit
) {
    val context = LocalContext.current

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            uri?.also {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(it, flag)
            }

            vehicle.value = vehicle.value.copy(uri = uri)
        }
    )

    Column() {
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

        AsyncImage(
            model = vehicle.value.uri,
            contentDescription = "Vehicle Image",
            modifier = Modifier.clickable{
                photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }.size(200.dp)
        )

        Button(onClick ={ photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}) {
            Text("Pick image")
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.name,
            onValueChange = { vehicle.value = vehicle.value.copy(name = it) },
            label = { Text(text = "Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.make,
            onValueChange = { vehicle.value = vehicle.value.copy(make = it) },
            label = { Text(text = "Make") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.model,
            onValueChange = { vehicle.value = vehicle.value.copy(model = it) },
            label = { Text(text = "Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.value.VIN,
            onValueChange = { vehicle.value = vehicle.value.copy(VIN = it) },
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