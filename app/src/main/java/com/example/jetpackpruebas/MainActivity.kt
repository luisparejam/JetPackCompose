package com.example.jetpackpruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackpruebas.ui.theme.JetPackPruebasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimerComposable()
        }
    }
}

@Composable
fun MiPrimerComposable(){
    Column(modifier = Modifier.fillMaxSize().padding(top=35.dp)){
        Row{
            Text(text="Texto fila 1")
            Text(text="Texto fila 2")
        }

        Spacer(modifier=Modifier.height(35.dp))

        Text(text="Hola alumnos de Kotlin")
        Text(text="Este es nuestro primer composable")
    }
}