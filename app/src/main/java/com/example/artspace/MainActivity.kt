package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtApp()
                }
            }
        }
    }
}

@Composable
fun ArtApp() {
    var currentState by remember {
        mutableStateOf("1")
    }
    val imageRes = when (currentState) {
        "1" -> R.drawable.art_work1
        "2" -> R.drawable.art_work2
        else -> R.drawable.art_work3
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier)
        ImageSurface(imageRes)
        Column {
            TitleInfo()
            Spacer(modifier = Modifier.height(20.dp))
            ActionButtons(handleNext = {
                currentState = when (currentState) {
                    "1" -> "2"
                    "2" -> "3"
                    else -> "1"
                }
            }, handlePrev = {
                currentState =   when (currentState) {
                    "1" -> "3"
                    "2" -> "1"
                    else -> "1"
                }
            })
        }

    }

}


@Composable
fun ImageSurface(@DrawableRes imgSource: Int) {
    Surface(shadowElevation = 9.dp, modifier = Modifier.background(Color.Yellow)) {
        Image(
            painter = painterResource(id = imgSource),
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .padding(30.dp)
        )

    }
}

@Composable
fun TitleInfo() {
    Column(
        Modifier
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        Text(text = "Still Life of Blue Rose and Other flowers", fontSize = 22.sp)
        Text(text = "Owen scott", fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Composable
fun ActionButtons(handlePrev: () -> Unit, handleNext: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(onClick = handlePrev) {
            Text(text = "Previous")

        }
        Button(onClick = handleNext) {
            Text(text = "Next")
        }
    }
}