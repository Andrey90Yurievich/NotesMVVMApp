package ru.ayuandrey.notesmvvmapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ayuandrey.notesmvvmapp.navigation.NavRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.tertiary,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavRoute.Add.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",

                )
            }
        }
    ) {
        Column {
            NoteItem(title = "ffff", subtitle = "cssdc", navController = navController)
            NoteItem(title = "fffddff", subtitle = "cfdbfdssdc", navController = navController)
            NoteItem(title = "ff3242ff", subtitle = "cssfdvdfdc", navController = navController)
            NoteItem(title = "ff433ff", subtitle = "cbbbbssdc", navController = navController)
        }

    }
}

@Composable
fun NoteItem(title: String, subtitle: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,

                )
            Text(
                text = subtitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun prevMainScreen() {
    MainScreen(navController = rememberNavController())
}