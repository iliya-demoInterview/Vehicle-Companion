package net.dentabros.stock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import net.dentabros.socket.StockDTO
import net.dentabros.stock.ui.theme.VehicleCompanionTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: StockViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VehicleCompanionTheme {
                val connected by viewModel.connected.collectAsStateWithLifecycle()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = { Title(connected) }, actions = {
                            IconButton(onClick = { if (connected) viewModel.disconnect() else viewModel.connect() }) {
                                Icon(
                                    imageVector = if (connected) Icons.Filled.Close else Icons.Filled.Add,
                                    contentDescription = "Localized description"
                                )
                            }
                        })
                    },
                ) { innerPadding ->
                    val data by viewModel.state.collectAsStateWithLifecycle()
                    StockScreen(
                        data, innerPadding
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}

@Composable
fun StockScreen(data: List<StockDTO>, pading: Any) {
    StockList(data)
}

@Composable
fun StockList(
    data: List<StockDTO>
) {
    val listState = rememberLazyListState()

    LazyColumn(
        //state = listState,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        items(data, key = { it.name }) { stock ->
            StockItem(stock)
        }

    }
}

@Composable
fun StockItem(item: StockDTO) {
    Row {
        Text(item.name, modifier = Modifier.padding(horizontal = 16.dp))
        Text(item.price.toString())

        if (item.increase) {
            Icon(
                imageVector = Icons.Filled.ArrowUpward,
                tint = Color.Green,
                contentDescription = "img"
            )
        } else {
            Icon(
                imageVector = Icons.Filled.ArrowDownward,
                tint = Color.Red,
                contentDescription = "img"
            )
        }
    }

}

@Composable
fun Title(connected: Boolean) {

    Icon(
        imageVector = Icons.Filled.Circle,
        contentDescription = "circle",
        tint = if (connected) Color.Green else Color.Gray
    )
}



