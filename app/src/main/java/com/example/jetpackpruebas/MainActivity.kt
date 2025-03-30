package com.example.jetpackpruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackpruebas.ui.theme.JetPackPruebasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiSegundoComposable()
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

@Composable
fun MiSegundoComposable(){
    Box(modifier=Modifier.fillMaxSize().padding(16.dp)){
        /* Text(text="Hola arriba izquierda", modifier=Modifier.align(Alignment.TopStart))
        Text(text="Hola centrado", modifier=Modifier.align(Alignment.Center))
        Text(text="Hola abajo derecha", modifier=Modifier.align(Alignment.BottomEnd)) */

        Image(
            painter = painterResource(id = R.drawable.demo),
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            contentDescription = "Coca-Cola"
        )

        Text(text = "Coca-Cola"
            , fontSize = 24.sp
            , color = Color.Red
            , textAlign = TextAlign.Center
            , modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun MiSegundoComposablePreview(){
    MiSegundoComposable()
}