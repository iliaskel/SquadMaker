package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squadmaker.model.database.entity.ComicsEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.repository.Repository
import com.example.squadmaker.model.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailedViewModelImpl(private val repository: Repository) : ViewModel(),
    DetailedViewModel {

    // region Public Functions

    override fun updateDetailedCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                repository.fetchAndSaveDetailedCharacterById(id)
                repository.fetchAndSaveComicsByCharacterId(id)
            }
        }
    }

    override fun removeDetailedCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeDetailedCharacter()
        }
    }

    override fun updateSquadList(isSquadMember: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSquadEntry(isSquadMember)
        }
    }

    // endregion

    // region LiveData observing

    override fun getDetailedCharacter(): LiveData<DetailedCharacterEntity> {
        return repository.getDetailedCharacter()
    }

    override fun getComics(): LiveData<List<ComicsEntity>> {
        return repository.getComics()
    }

    // endregion

}