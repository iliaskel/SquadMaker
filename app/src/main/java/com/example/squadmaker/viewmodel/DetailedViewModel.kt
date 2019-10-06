package com.example.squadmaker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.squadmaker.model.Repository
import com.example.squadmaker.model.database.entity.ComicsEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailedViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "DetailedViewModel "

    // region Fields

    private val repository = Repository.getInstance(application)

    //end region

    // region Public Functions

    fun updateDetailedCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            async {
                repository.fetchAndSaveDetailedCharacterById(id)
                repository.fetchAndSAveComicsByCharacterId(id)
            }
        }
    }

    fun removeDetailedCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeDetailedCharacter()
        }
    }

    fun updateSquadList(isSquadMember: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSquadEntry(isSquadMember)
        }
    }

    // endregion

    // region LiveData observing

    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity> {
        return repository.getDetailedCharacter()
    }

    fun getComics(): LiveData<List<ComicsEntity>> {
        return repository.getComics()
    }

    // endregion

}