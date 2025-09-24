package net.dentabros.vehicle

data class Vehicle (
    val id: Long,
    val name: String?,
    val make: String?,
    val model: String?,
    val year : Int,
    val VIN: String?,
    val fuelType: String?
){
}