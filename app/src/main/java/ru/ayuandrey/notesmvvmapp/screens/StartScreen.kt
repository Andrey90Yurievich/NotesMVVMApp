package ru.ayuandrey.notesmvvmapp.screens

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.ayuandrey.notesmvvmapp.MainViewModel
import ru.ayuandrey.notesmvvmapp.MainViewModelFactory
import ru.ayuandrey.notesmvvmapp.model.Note
import ru.ayuandrey.notesmvvmapp.navigation.NavRoute
import ru.ayuandrey.notesmvvmapp.ui.theme.NotesMVVMAppTheme
import ru.ayuandrey.notesmvvmapp.utils.Constants
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.FIREBASE_DATABASE
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.ROOM_DATABASE
import ru.ayuandrey.notesmvvmapp.utils.Constants.Keys.WHAT_WILL_WE_USE
import ru.ayuandrey.notesmvvmapp.utils.DB_TYPE
import ru.ayuandrey.notesmvvmapp.utils.LOGIN
import ru.ayuandrey.notesmvvmapp.utils.PASSWORD
import ru.ayuandrey.notesmvvmapp.utils.TYPE_FIREBASE
import ru.ayuandrey.notesmvvmapp.utils.TYPE_ROOM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constants.Keys.EMPTY) }
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
                       viewModel.initDatabase(TYPE_ROOM) {
                           DB_TYPE = TYPE_FIREBASE
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
                       coroutineScope.launch {
                           showBottomSheet = true
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
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet= false },
            sheetState = bottomSheetState,
        ) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.LOG_IN,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDatabase(TYPE_FIREBASE) {
                                DB_TYPE = TYPE_FIREBASE
                                navController.navigate(NavRoute.Main.route)
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = Constants.Keys.SIGN_IN)
                    }
                }
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