package net.dentabros.socket

import kotlinx.serialization.Serializable

@Serializable
data class StockDTO(val name: String, val price: Double, val increase: Boolean) {
}