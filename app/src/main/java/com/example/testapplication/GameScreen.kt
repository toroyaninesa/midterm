package com.example.testapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Composable
@Preview(showBackground = true)
fun GameScreen() {
    var targetValue: Int by remember { mutableStateOf(generateRandomTarget()) }
    var playerGuess by remember { mutableStateOf(50f) }
    var score: Int by remember { mutableStateOf(0) }
    var feedback by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bull's Eye Game",
            color = Color.Black
        )

        Text(
            text = "Target Value: $targetValue",
            color = Color.Black
        )

        Slider(
            value = playerGuess,
            onValueChange = {
                playerGuess = it
            },
            valueRange = 0f..100f,
        )

        Button(
            onClick = {
                val difference: Int = targetValue - playerGuess.toInt()
                when {
                    difference in -3..3 -> {
                        score += 5
                        feedback = "Excellent! You're very close."
                    }

                    difference in -8..8 -> {
                        score += 1
                        feedback = "Not bad, but you can do better."
                    }

                    else -> {
                        feedback = "Oops! Try again."
                    }
                }
                playerGuess = 50f;
                targetValue = generateRandomTarget()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Hit Me!")
        }
        Text(
            text = "Score: $score",
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = feedback,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

private fun generateRandomTarget(): Int {
    return (0..100).random()
}