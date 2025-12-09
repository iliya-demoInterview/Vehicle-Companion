package net.dentabros.socket

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SocketService {
    fun connect()
    fun disconnect()
    suspend fun autoGenerate()
    val connected: StateFlow<Boolean>
    val data : StateFlow<List<StockDTO>>
}