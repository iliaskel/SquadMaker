package com.example.squadmaker.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.squadmaker.model.repository.Repository
import com.example.squadmaker.view.uimodel.UIComic
import com.example.squadmaker.view.uimodel.UIDetailedCharacter
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

    override fun getDetailedCharacter(): LiveData<UIDetailedCharacter?> {
        return repository.getDetailedCharacter().map { detailedCharacterEntity ->
            if (detailedCharacterEntity == null)
                return@map null
            return@map UIDetailedCharacter(
                detailedCharacterEntity.id,
                detailedCharacterEntity.name,
                detailedCharacterEntity.description,
                detailedCharacterEntity.resourceUrl,
                detailedCharacterEntity.availableComics,
                detailedCharacterEntity.isSquadMember
            )
        }
    }

    override fun getComics(): LiveData<List<UIComic>> {
        return repository.getComics().map { comicList ->
            comicList.map { comicEntity ->
                UIComic(
                    comicEntity.id,
                    comicEntity.availableComics,
                    comicEntity.resourceUri,
                    comicEntity.name
                )
            }
        }
    }

    // endregion

}