package ru.ayuandrey.notesmvvmapp.database

import androidx.lifecycle.LiveData
import ru.ayuandrey.notesmvvmapp.model.Note

interface DatabaseRepository {
    val readAll: LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)
    suspend fun update(note: Note, onSuccess: ()-> Unit)
    suspend fun delete(note: Note, onSuccess: ()-> Unit)
}