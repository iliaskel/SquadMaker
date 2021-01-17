package com.example.squadmaker.characters.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.squadmaker.R
import com.example.squadmaker.characters.viewmodel.CharactersViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_main) {

    // region Fields

    private val charactersViewModel: CharactersViewModelImpl by viewModels()

    // endregion

    // region Lifecycle overrides functions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogo()
        requestCharacters()
        attachObservers()
    }

    // endregion

    // region Private Functions

    private fun requestCharacters() {
        charactersViewModel.fetchCharacters()
    }


    private fun attachObservers() {
        charactersViewModel.getCharacters()
            .observe(viewLifecycleOwner,
                { characterList ->
                    main_fragment_characters_view.updateCharactersList(characterList)
                })

        charactersViewModel.getSquad()
            .observe(viewLifecycleOwner,
                { squadList ->
                    if (squadList.isEmpty()) {
                        main_fragment_my_squad_view.updateViewToInvisible()
                    } else {
                        main_fragment_my_squad_view.updateView(squadList)
                    }
                })
    }

    private fun setLogo() {
        main_fragment_app_title_view.updateApplicationTitleText()
    }

    // endregion

    // region Click Listeners functions

    // endregion
}