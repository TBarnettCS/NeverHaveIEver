package com.example.neverhaveiever

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neverhaveiever.ui.theme.NeverHaveIEverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeverHaveIEverTheme {
                    NavHost()
                }
            }
        }
    }

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            Homepage(
                onNavigateToGame = { navController.navigate("gameScreen") },
            )
        }
            composable("gameScreen") {
                GameScreen()

        }
    }
}

@Composable
fun Homepage(onNavigateToGame: () -> Unit) {
    val image = painterResource(id = R.drawable.gamemode_standard)
    val textimage = painterResource(id = R.drawable.logo_textonly)
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(111, 69, 237)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(10.dp),
            painter = textimage,
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxSize().background(Color(255,255,255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = 100.dp),
                text = "Game Decks",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Box() {
                ElevatedButton(
                    onClick = onNavigateToGame,
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = image,
                            contentDescription = null,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                        Text(
                            modifier = Modifier,
                            text = "Normal",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp),
                color = Color.LightGray,
                text = "This is a development version of the game. More modes will be available soon",
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun GameScreen() {
    val textimage = painterResource(id = R.drawable.logo_textonly)
    val NHIE = listOf(
        "got a tattoo",
        "had a speeding ticket",
        "given a fake name",
        "broken a bone",
        "used a pick up line",
        "Googled my own name",
        "dropped my phone in a toilet",
        "used someone else’s Netflix account",
        "cheated on a test or exam",
        "pulled an all nighter"
    )
    var Question by remember { mutableStateOf("Click to begin") }
    var Colour by remember { mutableStateOf(Color(100, 100, 100)) }
    Column(
        modifier = Modifier.fillMaxWidth().background(Color(111, 69, 237)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(10.dp),
            painter = textimage,
            contentDescription = null)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    val randomnum1 = (0..150).random()
                    val randomnum2 = (0..150).random()
                    val randomnum3 = (0..150).random()
                    val randomindex = (0..9).random()
                    Question = "Never have I ever " + NHIE[randomindex]
                    Colour = Color(randomnum1, randomnum2, randomnum3)
                }
                .background(Colour),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = Question,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NeverHaveIEverTheme {
        Homepage {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    NeverHaveIEverTheme {
        GameScreen()
    }
}

