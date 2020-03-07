package com.example.squadmaker.view.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.squadmaker.R
import com.example.squadmaker.viewmodel.MainViewModel
import com.example.squadmaker.widgets.mainfragment.CharactersView
import com.example.squadmaker.widgets.mainfragment.MySquadView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), CharactersView.CharacterInteraction, MySquadView.SquadInteraction {

    // region fields

    private lateinit var viewModel: MainViewModel

    // endregion

    // region Lifecycle overrides functions

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogo()
        initializeViewModel()
        requestCharacters()
        attachObservers()
        attachListeners()
    }

    // endregion

    // region Private Functions

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    private fun requestCharacters() {
        viewModel.fetchCharacters()
    }


    private fun attachObservers() {
        viewModel.getCharacters()
            .observe(viewLifecycleOwner,
                Observer { characterList ->
                    main_fragment_characters_view.updateCharactersList(characterList)
                })

        viewModel.getSquad()
            .observe(viewLifecycleOwner,
                Observer { squadList ->
                    if (squadList.isEmpty()) {
                        main_fragment_my_squad_view.updateViewToInvisible()
                    } else {
                        main_fragment_my_squad_view.updateView(squadList)
                    }
                })
    }

    private fun navigateToDetailedCharacter(id: Int) {
        val action =
            MainFragmentDirections.actionMainFragmentToDetailedCharacterFragment(id)

        findNavController().navigate(action)
    }

    private fun setLogo() {
        main_fragment_app_title_view.updateApplicationTitleText()
    }

    private fun attachListeners() {
        main_fragment_characters_view.setListener(this)
        main_fragment_my_squad_view.setListener(this)
    }

    // endregion

    // region Click Listeners functions

    override fun characterClicked(id: Int) {
        navigateToDetailedCharacter(id)
    }

    override fun squadCharacterClickerClicked(id: Int) {
        navigateToDetailedCharacter(id)

    }

    // endregion
}