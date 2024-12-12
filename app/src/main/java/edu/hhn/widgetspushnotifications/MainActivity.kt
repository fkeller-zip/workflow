package edu.hhn.widgetspushnotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainAppScreen()
        }
    }
}

@Composable
fun MainAppScreen(viewModel: CounterViewModel = viewModel()) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Counter: ${viewModel.counter.value}", modifier = Modifier.align(Alignment.CenterHorizontally))

        TextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Enter Message") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Button(onClick = {
            viewModel.sendNotification("Broadcast Title", message)
        }) {
            Text("Send")
        }
    }
}
