package com.example.squadmaker.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.repository.Repository
import com.example.squadmaker.view.uimodel.UIComic
import com.example.squadmaker.view.uimodel.UIDetailedCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
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

    override fun getDetailedCharacter(): LiveData<UIDetailedCharacter?> {
        return repository.getDetailedCharacter().map { detailedCharacterEntity ->
            detailedCharacterEntity ?: return@map null
            return@map transformToUiDetailedCharacter(detailedCharacterEntity)
        }.asLiveData()
    }

    override fun getComics(): LiveData<List<UIComic>> {
        return repository.getComics().map { comicList ->
            comicList.map { comicEntity ->
                transformToUIComic(comicEntity)
            }
        }.asLiveData()
    }

    // endregion

    // region Private Functions

    private fun transformToUiDetailedCharacter(detailedCharacterEntity: DetailedCharacterEntity): UIDetailedCharacter {
        return UIDetailedCharacter(
            detailedCharacterEntity.id,
            detailedCharacterEntity.name,
            detailedCharacterEntity.description,
            detailedCharacterEntity.resourceUrl,
            detailedCharacterEntity.isSquadMember
        )
    }

    private fun transformToUIComic(comicEntity: ComicsEntity): UIComic {
        return UIComic(
            comicEntity.id,
            comicEntity.resourceUri,
            comicEntity.name
        )
    }

    // endregion

}