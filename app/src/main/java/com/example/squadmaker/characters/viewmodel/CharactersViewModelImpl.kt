package com.example.squadmaker.characters.viewmodel

import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.example.squadmaker.characters.fragment.CharactersFragmentDirections
import com.example.squadmaker.characters.uimodel.UICharacter
import com.example.squadmaker.characters.uimodel.UISquadEntry
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.repository.CharactersRepository
import com.example.squadmaker.model.repository.utils.SquadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class CharactersViewModelImpl
@ViewModelInject constructor(
    private val charactersRepository: CharactersRepository,
    private val squadRepository: SquadRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(),
    CharactersViewModel {

    // region Public Functions

    override fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.fetchAndSaveCharacters()
        }
    }

    // region LiveData observing

    override fun getCharacters(): LiveData<List<UICharacter>> {
        return charactersRepository.getCharactersListFlow()?.map {
            return@map transformToUICharacter(it)
        }?.asLiveData() ?: throw Exception("ree")
    }

    override fun getSquad(): LiveData<List<UISquadEntry>> {
        return squadRepository.getSquadListFlow().mapNotNull { squadList ->
            squadList.map { squadEntry ->
                transformToUISquadEntry(squadEntry)
            }
        }.asLiveData()
    }

    // endregion

    // region Private Functions

    private fun transformToUICharacter(it: List<CharacterEntity>): List<UICharacter> {
        val charList = mutableListOf<UICharacter>()
        it.map { character ->
            val clickAction = getClickActionForCharacter(character.id)
            val uiCharacter = getUICharacter(character, clickAction)
            charList.add(uiCharacter)
        }
        return charList
    }

    private fun transformToUISquadEntry(squadEntry: SquadEntity): UISquadEntry {
        return UISquadEntry(
            squadEntry.id,
            squadEntry.thumbnailPath,
            getClickActionForCharacter(squadEntry.id)
        )
    }

    private fun getUICharacter(
        character: CharacterEntity,
        clickAction: (View) -> Unit
    ): UICharacter {
        return UICharacter(
            character.id,
            character.name,
            character.description,
            character.thumbnailStringUrl,
            character.isSquadMember,
            clickAction
        )
    }

    private fun getClickActionForCharacter(characterId: Int): (View) -> Unit {
        return { view: View ->
            val action =
                CharactersFragmentDirections.actionMainFragmentToDetailedCharacterFragment(
                    characterId
                )
            view.findNavController().navigate(action)
        }
    }

    // endregion

}