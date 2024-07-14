package ru.ayuandrey.notesmvvmapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ayuandrey.notesmvvmapp.navigation.NavRoute
import ru.ayuandrey.notesmvvmapp.ui.theme.NotesMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
 fun StartScreen(navController: NavHostController) {
       Scaffold(
           modifier = Modifier.fillMaxSize(),
           containerColor = MaterialTheme.colorScheme.tertiary
       ) {
           Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,

           ) {
               Text(text = "СТАРТОВЫЙ ЭКРАН")
               Button(
                   onClick = {
                          navController.navigate(route = NavRoute.Main.route)
                          },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "ROOM БД")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "FIREBASE БД")
            }
        }
       }
}


@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
     NotesMVVMAppTheme{
          StartScreen(navController = rememberNavController())
 }
}