package com.inf2007.lab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inf2007.lab01.ui.theme.Lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Lab01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var username by remember { mutableStateOf("") }
            var showGreeting by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                UserInput(
                    name = username,
                    onNameChange = { username = it }
                )

                Button(
                    onClick = {
                        showGreeting = username.isNotBlank()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("submitButton")
                ) {
                    Text("Submit")
                }

                if (showGreeting) {
                    Greeting(
                        name = username,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("greetingMsg")
                    )
                }
            }
        }
    }
}

@Composable
fun UserInput(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text("Enter your Name") },
        modifier = modifier
            .fillMaxWidth()
            .testTag("nameInput")
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!, Welcome to INF2007!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}
//
//Key Changes Made:
//
//Test Tag Corrections
//kotlinCopy// Changed from .testTag("UserInput") to:
//.testTag("nameInput")
//// Added test tag for greeting:
//.testTag("greetingMsg")
//These changes ensure UI elements can be found by the test suite using the correct identifiers.
//Logic Fix in Button Click
//kotlinCopy// Changed from:
//if (username.isNotBlank()) {
//    showGreeting = false
//}
//// To:
//showGreeting = username.isNotBlank()
//This fixes the core logic - now it shows the greeting when there's text and hides it when empty.
//Greeting Component Fix
//kotlinCopy// Changed from:
//text = "Hello $username!, Welcome to InF2007!"
//// To:
//text = "Hello $name!, Welcome to INF2007!"
//This ensures the greeting uses the passed parameter instead of directly accessing the state.