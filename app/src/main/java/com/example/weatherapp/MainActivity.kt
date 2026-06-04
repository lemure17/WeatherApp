package com.example.weatherapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() { // classe principal para criação do projeto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {HomePage()}
        }
    }




@Preview (showBackground = true)

@Composable
fun HomePage(modifier: Modifier =  Modifier){
    val activity = LocalActivity.current as Activity
    Column(modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF6650A4))
            .padding(all = 24.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,)
    {
        Text(text = "Bem vindo/a!", fontSize = 24.sp,)

        Spacer(modifier = Modifier.size(12.dp))

        Button(onClick = {
            activity.finish()
        })
        {
            Text("Sair")
        }
    }}