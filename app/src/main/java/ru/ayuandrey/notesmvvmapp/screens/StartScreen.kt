package ru.ayuandrey.notesmvvmapp.screens

import android.annotation.SuppressLint
import android.app.Application
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ayuandrey.notesmvvmapp.MainViewModel
import ru.ayuandrey.notesmvvmapp.MainViewModelFactory
import ru.ayuandrey.notesmvvmapp.navigation.NavRoute
import ru.ayuandrey.notesmvvmapp.ui.theme.NotesMVVMAppTheme
import ru.ayuandrey.notesmvvmapp.utils.Constants
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.FIREBASE_DATABASE
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.ROOM_DATABASE
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.WHAT_WILL_WE_USE
import ru.ayuandrey.notesmvvmapp.utils.TYPE_FIREBASE
import ru.ayuandrey.notesmvvmapp.utils.TYPE_ROOM

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

       Scaffold(
           modifier = Modifier.fillMaxSize(),
           containerColor = MaterialTheme.colorScheme.tertiary
       ) {
           Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               Text(text = WHAT_WILL_WE_USE)
               Button(
                   onClick = {
                       mViewModel.initDatabase(TYPE_ROOM) {
                           navController.navigate(route = NavRoute.Main.route)
                       }
                   },
                   modifier = Modifier
                       .width(200.dp)
                       .padding(vertical = 8.dp)
               ) {
                   Text(text = ROOM_DATABASE)
               }
               Button(
                   onClick = {
                       mViewModel.initDatabase(TYPE_FIREBASE) {
                           navController.navigate(route = NavRoute.Main.route)
                       }

                   },
                   modifier = Modifier
                       .width(200.dp)
                       .padding(vertical = 8.dp)
               ) {
                   Text(text = FIREBASE_DATABASE)
               }
           }
       }
}


@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {
     NotesMVVMAppTheme{
         val context = LocalContext.current
         val mViewModel: MainViewModel =
             viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
          StartScreen(navController = rememberNavController(), viewModel = mViewModel)
 }
}