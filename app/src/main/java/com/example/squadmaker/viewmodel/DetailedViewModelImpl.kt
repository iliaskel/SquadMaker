package com.example.squadmaker.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class DetailedViewModelImpl
@ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(),
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