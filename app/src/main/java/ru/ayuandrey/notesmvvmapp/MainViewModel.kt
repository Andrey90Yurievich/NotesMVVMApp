package ru.ayuandrey.notesmvvmapp

import android.app.Application
import android.util.Log
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ayuandrey.notesmvvmapp.database.firebase.AppFirebaseRepository
import ru.ayuandrey.notesmvvmapp.database.room.AppRoomDatabase
import ru.ayuandrey.notesmvvmapp.database.room.repository.RoomRepository
import ru.ayuandrey.notesmvvmapp.model.Note
import ru.ayuandrey.notesmvvmapp.utils.Constants
import ru.ayuandrey.notesmvvmapp.utils.DB_TYPE
import ru.ayuandrey.notesmvvmapp.utils.REPOSITORY
import ru.ayuandrey.notesmvvmapp.utils.TYPE_FIREBASE
import ru.ayuandrey.notesmvvmapp.utils.TYPE_ROOM


class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("проверка данных", "MainViewModel создание БД $type")
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    { onSuccess() },
                    { Log.d("checkData", "Error: ${it}") }
                )
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
    fun readAllNotes() = REPOSITORY.readAll


    fun signOut(onSuccess: () -> Unit) {
        when (DB_TYPE.value) {
            TYPE_FIREBASE,
            TYPE_ROOM -> {
                REPOSITORY.signOut()
                DB_TYPE.value = Constants.Keys.EMPTY
                onSuccess()
            }
            else -> { Log.d("checkData", "signOut: ELSE: ${DB_TYPE.value}") }
        }
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("неизвестный ViewModel class")

    }
}