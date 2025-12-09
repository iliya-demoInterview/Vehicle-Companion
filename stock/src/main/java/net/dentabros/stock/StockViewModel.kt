package net.dentabros.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.dentabros.socket.SocketService
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(private val socketService: SocketService): ViewModel() {

    init {
        viewModelScope.launch {
            socketService.autoGenerate()
        }
    }

    val state = socketService.data
    val connected = socketService.connected

    fun disconnect(){
        socketService.disconnect()
    }

    fun connect(){
        socketService.connect()
    }

}