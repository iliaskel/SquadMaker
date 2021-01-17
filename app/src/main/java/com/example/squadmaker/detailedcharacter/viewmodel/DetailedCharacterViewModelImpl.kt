package com.example.squadmaker.detailedcharacter.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.squadmaker.detailedcharacter.uimodel.UIComic
import com.example.squadmaker.detailedcharacter.uimodel.UIDetailedCharacter
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.repository.CharactersRepository
import com.example.squadmaker.model.repository.ComicsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

@ExperimentalCoroutinesApi
class DetailedCharacterViewModelImpl
@ViewModelInject constructor(
    private val charactersRepository: CharactersRepository,
    private val comicsRepository: ComicsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(),
    DetailedCharacterViewModel {

    // region Public Functions

    // endregion

    // region LiveData observing

    override fun getDetailedCharacter(characterId: Int): LiveData<UIDetailedCharacter?> {
        return charactersRepository.getCharacterByCharacterId(characterId)
            .mapNotNull { character ->
                character ?: return@mapNotNull null
                return@mapNotNull transformToUiDetailedCharacter(character)
            }.asLiveData()
    }

    override fun getComics(): LiveData<List<UIComic>> {
        return comicsRepository.getComicsListFlow().map { comicList ->
            comicList.map { comicEntity ->
                transformToUIComic(comicEntity)
            }
        }.asLiveData()
    }

    // endregion

    // region Private Functions

    private fun transformToUiDetailedCharacter(characterEntity: CharacterEntity): UIDetailedCharacter {
        return UIDetailedCharacter(
            characterEntity.id,
            characterEntity.name,
            characterEntity.description,
            characterEntity.thumbnailStringUrl,
            characterEntity.isSquadMember
        )
    }

    private fun transformToUIComic(comicEntity: ComicsEntity): UIComic {
        return UIComic(
            comicEntity.id,
            comicEntity.resourceURI,
            comicEntity.title
        )
    }

    // endregion

}