package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity
import com.example.squadmaker.model.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModelImpl(private val repository: RepositoryImpl) : ViewModel(), KoinComponent,
    MainViewModel {

    // region Public Functions

    override fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAndSaveCharacters()
        }
    }

    // region LiveData observing

    override fun getCharacters(): LiveData<List<CharacterEntity>> {
        return repository.getCharacters()
    }

    override fun getSquad(): LiveData<List<SquadEntity>> {
        return repository.getSquad()
    }

    // endregion

}