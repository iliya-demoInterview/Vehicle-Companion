package net.dentabros.socket

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

const val EVENT = "price_update"

@Singleton
class SocketServiceImp @Inject constructor() : SocketService {

    private val _connected = MutableStateFlow<Boolean>(false)
    private val _data = MutableStateFlow<List<StockDTO>>(mutableListOf())
    private val _stocks = mutableListOf<String>("APPL", "NDVI", "INTL", "GOOG", "INTZ", "TES",
        "AAAA", "BBBB", "CCCC", "DDDD", "EEEE", "FFFF", "GGGG", "HHHH", "IIII", "JJJJ", "KKKK", "MMMM", "ZZZZ", "PPPP")

    private lateinit var socket: WebSocket

    private val webSocketListener = object : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            val stocks = Json.decodeFromString<List<StockDTO>>(text).sortedByDescending { it.price }
            _data.value = stocks
        }
    }

    init {
        setSocket()
    }
    
    override fun connect() {
        setSocket()
    }

    override fun disconnect() {
        socket.close(1000, "disconnect")
        _connected.value = false
    }

    override suspend fun autoGenerate() {
        while (true){
            if (_connected.value) {
                val data = generateData()
                send(data)
            }
            delay(2000)
        }
    }

    private fun send(data: List<StockDTO>) {
        if (::socket.isInitialized) {
            socket.send(Json.encodeToString(data))
        }
    }

    override val connected: StateFlow<Boolean>
        get() = _connected.asStateFlow()

    override val data: StateFlow<List<StockDTO>>
        get() = _data.asStateFlow()

    private fun setSocket() {
        try {
            val client = OkHttpClient()
            val request = Request.Builder().url(url = "wss://ws.postman-echo.com/raw").build()
            socket = client.newWebSocket(request, webSocketListener)
            _connected.value = true
        } catch (ex: Exception) {

        }
    }

    private fun generateData() : List<StockDTO> {
        val data = mutableListOf<StockDTO>()
        for (s in _stocks){
            val price = Random.nextDouble(from = 1.00, until = 10.00)
            val increase = Random.nextInt(from = 1, until = 3) % 2 == 0
            data.add(StockDTO(s, price, increase))
        }

        return data
    }


}