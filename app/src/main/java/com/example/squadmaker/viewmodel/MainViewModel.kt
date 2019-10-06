package com.example.squadmaker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.squadmaker.model.Repository
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    // region Fields

    private val repository = Repository.getInstance(application)

    // endregion

    // region Public Functions

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAndSaveCharacters()
        }
    }

    // region LiveData observing

    fun getCharacters(): LiveData<List<CharacterEntity>> {
        return repository.getCharacters()
    }

    fun getSquad(): LiveData<List<SquadEntity>> {
        return repository.getSquad()
    }

    // endregion

}