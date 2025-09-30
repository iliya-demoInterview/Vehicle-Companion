package net.dentabros.vehicle

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GarageUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun displayVehicleItem(){
        val vehicle = Vehicle(
            id = 1,
            name = "Vehicle",
            make = "Make",
            model = "Model",
            VIN = "Vin",
            year = 0,
            fuelType = "EV",
            uri = null
        )
        composeTestRule.setContent {
            VehicleItem(vehicle) { }
        }

        composeTestRule
            .onNodeWithText(vehicle.name!!)
            .assertExists()

        composeTestRule
            .onNodeWithText(vehicle.make!!)
            .assertExists()

        composeTestRule
            .onNodeWithText(vehicle.model!!)
            .assertExists()
    }
}