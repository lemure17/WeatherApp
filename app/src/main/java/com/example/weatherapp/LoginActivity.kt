package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                LoginPage()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(all = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Bem-vindo/a!", fontSize = 24.sp
            )
            Spacer(modifier = Modifier.size(12.dp))
            OutlinedTextField(
                value = email,
                label = { Text(text = "Digite seu e-mail") },
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.size(12.dp))
            OutlinedTextField(
                value = password,
                label = { Text(text = "Digite sua senha") },
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        Firebase.auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(activity) { task ->
                                if (task.isSuccessful) {
                                    activity.startActivity(
                                        Intent(activity, MainActivity::class.java).setFlags(
                                            FLAG_ACTIVITY_SINGLE_TOP
                                        )
                                    )
                                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(activity, "Login FALHOU!", Toast.LENGTH_LONG).show()

                                }
                            }
                    },
                    enabled = email.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text("Login")
                }

                Button(
                    onClick = {
                        activity.startActivity(
                            Intent(activity, RegisterActivity::class.java).setFlags(
                                Intent.FLAG_ACTIVITY_SINGLE_TOP
                            )
                        )
                    }
                ) {
                    Text("Registrar")
                }

                Button(
                    onClick = { email = ""; password = "" }
                ) {
                    Text("Limpar")
                }
            }
        }
    }
}
