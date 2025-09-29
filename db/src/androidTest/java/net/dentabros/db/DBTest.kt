package net.dentabros.db

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import net.dentabros.db.vehicle.VehicleDAO
import net.dentabros.db.vehicle.VehicleEntity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var vehicleDAO: VehicleDAO
    private lateinit var db: VehicleDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext, VehicleDatabase::class.java).build()
        vehicleDAO = db.getVehicleDAO()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePoiAndReadInList() = runBlocking {
        val vehicleEntity = VehicleEntity(
            id = 1,
            name = "Vehicle",
            make = "Make",
            model = "Model",
            VIN = "Vin",
            year = 0,
            fuelType = "EV",
            uri = null
        )
        vehicleDAO.insert(vehicleEntity)
        val dbEntry = vehicleDAO.getVehicle(1)
        assertEquals(dbEntry, vehicleEntity )
    }

    @Test
    @Throws(Exception::class)
    fun updatePoiAndReadInList() = runBlocking {
        val vehicleEntity = VehicleEntity(
            id = 1,
            name = "Vehicle",
            make = "Make",
            model = "Model",
            VIN = "Vin",
            year = 0,
            fuelType = "EV",
            uri = null
        )

        vehicleDAO.insert(vehicleEntity)


        val updateEntity = vehicleEntity.copy(name = "Update vehicle")

        vehicleDAO.update(updateEntity)


        val dbEntry = vehicleDAO.getVehicle(1)
        assertEquals(dbEntry, updateEntity )
    }
}