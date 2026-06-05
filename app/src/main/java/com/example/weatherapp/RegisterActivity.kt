package com.example.weatherapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            RegisterPage()
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable {mutableStateOf(value="")}
    var confirmPassword by rememberSaveable {mutableStateOf(value="")}
    val activity = LocalActivity.current as Activity
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Bem-vindo/a!", fontSize = 24.sp
        )

        Spacer(modifier = Modifier.size(12.dp))
        OutlinedTextField(
            value = name,
            label = { Text(text = "Digite seu nome") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { name = it })

        Spacer(modifier = Modifier.size(12.dp))
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it })

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.size(12.dp))
        OutlinedTextField(
            value = confirmPassword,
            label = { Text(text = "Confirme sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { confirmPassword = it },
            visualTransformation = PasswordVisualTransformation()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    Toast.makeText(activity, "Registro OK!", Toast.LENGTH_LONG).show()
                    activity.finish()
                },
                enabled = email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && confirmPassword ==password
            )

            { Text("Registrar") }

            Spacer(modifier = Modifier.size(12.dp))
            Button(
                onClick = {
                activity.finish()
                }
            ) {
                Text("Voltar")
            }

            Spacer(modifier = Modifier.size(12.dp))

            Button(

                onClick = { email = ""; password = "" ; confirmPassword=""; name = ""}) {
                Text("Limpar")
            }
        }
    }}