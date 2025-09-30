package net.dentabros.shared

import android.content.Intent
import android.widget.ScrollView
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    vehicle: MutableVehicle,
    onSave: (onSuccess: () -> Unit) -> Unit,
    onSuccess: () -> Unit,
    onVehicleUiEvent: (VehicleUiEvent) -> Unit,
    nameHasErros: Boolean,
) {
    val context = LocalContext.current

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri ->
            uri?.also {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(it, flag)
            }

            onVehicleUiEvent(VehicleUiEvent.UriChanged(uri))
        }
    )

    Column(modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

        AsyncImage(
            model = vehicle.uri,
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
            value = vehicle.name,
            onValueChange = { onVehicleUiEvent(VehicleUiEvent.NameChanged(it)) },
            label = { Text(text = "Name") },
            isError = nameHasErros,
            supportingText = {
                if (nameHasErros) {
                    Text("Name is required")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.make,
            onValueChange = { onVehicleUiEvent(VehicleUiEvent.MakeChanged(it)) },
            label = { Text(text = "Make") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.model,
            onValueChange = { onVehicleUiEvent(VehicleUiEvent.ModelChanged(it)) },
            label = { Text(text = "Model") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = textFieldModifier,
            value = vehicle.VIN,
            onValueChange = { onVehicleUiEvent(VehicleUiEvent.VINChanged(it)) },
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