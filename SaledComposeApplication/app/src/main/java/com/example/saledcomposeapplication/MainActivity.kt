package com.example.saledcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saledcomposeapplication.ui.theme.SaledComposeApplicationTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaledComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SaledComposeApplicationTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
) {

    val screenState = counterViewModel.screenState.value
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(key1 = true) {
        counterViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        it
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = screenState.displayingResult,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.DarkGray
            )
            OutlinedTextField(
                value = screenState.inputValue,
                onValueChange = {
                    counterViewModel.onEvent(CounterEvent.ValueEntered(it))
                },
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = Color.LightGray,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                label = { Text(text = "New Count") }
            )
            if (screenState.isCountButtonVisible) {
                Button(
                    onClick = {
                        counterViewModel.onEvent(CounterEvent.CountButtonClicked)
                    },
                    modifier = modifier.fillMaxWidth()
                )
                {
                    Text(
                        text = "Count",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Button(
                onClick = {
                    counterViewModel.onEvent(CounterEvent.ResetButonClicked)
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reset",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }

}



