package com.example.jetpackpruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
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
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 35.dp)){
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

    var colorFondo by remember{ mutableStateOf(Color.White) }

    var posicionTexto by remember{ mutableStateOf(Offset(x=0f,y=0f)) }

    var anchoPantalla by remember{ mutableStateOf(0f)}

    var altoPantalla by remember{ mutableStateOf(0f)}

    var anchoTexto by remember { mutableStateOf(0f) }

    var altoTexto by remember { mutableStateOf(0f) }

    Box(modifier=Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(colorFondo)
        .onGloballyPositioned { coordinates ->
            anchoPantalla = coordinates.size.width.toFloat()
            altoPantalla = coordinates.size.height.toFloat()

        }){
        /* Text(text="Hola arriba izquierda", modifier=Modifier.align(Alignment.TopStart))
        Text(text="Hola centrado", modifier=Modifier.align(Alignment.Center))
        Text(text="Hola abajo derecha", modifier=Modifier.align(Alignment.BottomEnd)) */

        /*Image(
            painter = painterResource(id = R.drawable.demo),
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            contentDescription = "Coca-Cola"
        )*/

        ImagenInteractiva()

        Text(text = "Coca-Cola",
            fontSize = 24.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            //modifier = Modifier.align(Alignment.Center)
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    anchoTexto = coordinates.size.width.toFloat()
                    altoTexto = coordinates.size.height.toFloat()

                    // Centrar elemento
                    if (posicionTexto == Offset(0f, 0f)) {
                        posicionTexto =
                            Offset((anchoPantalla - anchoTexto) / 2, (altoPantalla - altoTexto) / 2)
                    }

                }
                .offset {
                    IntOffset(posicionTexto.x.toInt(), posicionTexto.y.toInt())
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        posicionTexto += Offset(dragAmount.x, dragAmount.y)
                    }
                }
            )

        // Boton en la parte superior izquierda
        Button(onClick={colorFondo=colorAleatorio()},
            modifier=Modifier.align(Alignment.TopStart))
        {Text(text="Fondo")}
    }
}

@Composable
fun ImagenInteractiva(){

    var escala by remember { mutableStateOf(1f) } // Escala de la imagen
    var posicion by remember { mutableStateOf (Offset(0f,0f))} // Posicion de la imagen
    var rotacion by remember { mutableStateOf(0f) } // Angulo de rotacion de la imagen

    // Animaciones suaves hacia valores iniciales

    val escalaAnimada by animateFloatAsState(targetValue = escala, animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing))
    val rotacionAnimada by animateFloatAsState(targetValue = rotacion, animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing))
    val posicionAnimada by animateOffsetAsState(targetValue = posicion, animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing))

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTransformGestures { _, desplazamiento, zoom, cambioRotacion ->
                escala *= zoom // Aplicar zoom
                posicion += desplazamiento // Aplicar desplazamientos
                rotacion += cambioRotacion // Aplicar rotacion
            }
        }
        .pointerInput(Unit) {
            detectTapGestures(onDoubleTap = {
                escala=1f
                posicion=Offset(0f,0f)
                rotacion=0f
            }) {

            }
        }, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.demo),
            contentDescription = "Coca-Cola",
            modifier = Modifier.graphicsLayer (scaleX = escalaAnimada.coerceIn(0.5f,3f),
                scaleY = escalaAnimada.coerceIn(0.5f, 3f),
                translationX = posicionAnimada.x,
                translationY = posicionAnimada.y,
                rotationZ = rotacionAnimada

            )
        )
    }
}

fun colorAleatorio():Color{
    val rojo=(0..255).random()
    val verde=(0..255).random()
    val azul=(0..255).random()
    return Color(red=rojo, green=verde, blue=azul)
}

@Preview
@Composable
fun MiSegundoComposablePreview(){
    MiSegundoComposable()
}