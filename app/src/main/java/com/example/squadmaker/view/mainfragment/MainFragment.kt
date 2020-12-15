package com.example.squadmaker.view.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.squadmaker.R
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.view.uimodel.UICharacter
import com.example.squadmaker.view.widgets.mainfragment.MySquadView
import com.example.squadmaker.viewmodel.MainViewModelImpl
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(),
    MySquadView.SquadInteraction {

    // region Fields

    private val mainViewModel by viewModel<MainViewModelImpl>()

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
        requestCharacters()
        attachObservers()
        attachListeners()
    }

    // endregion

    // region Private Functions

    private fun requestCharacters() {
        mainViewModel.fetchCharacters()
    }


    private fun attachObservers() {
        mainViewModel.getCharacters()
            .observe(viewLifecycleOwner,
                Observer<List<UICharacter>> { characterList ->
                    main_fragment_characters_view.updateCharactersList(characterList)
                })

        mainViewModel.getSquad()
            .observe(viewLifecycleOwner,
                Observer<List<SquadEntity>> { squadList ->
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
        main_fragment_my_squad_view.setListener(this)
    }

    // endregion

    // region Click Listeners functions

    override fun squadCharacterClickerClicked(id: Int) {
        navigateToDetailedCharacter(id)
    }

    // endregion
}