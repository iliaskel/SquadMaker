package com.example.squadmaker.detailedcharacter.fragment

import com.example.squadmaker.detailedcharacter.viewmodel.DetailedCharacterViewModelImpl
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.squadmaker.R
import com.example.squadmaker.detailedcharacter.widgets.DetailedCharacterInformationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detailed.*
import kotlinx.android.synthetic.main.view_detailed_character_information.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailedFragment() : Fragment(R.layout.fragment_detailed),
    DetailedCharacterInformationView.CharacterViewInteraction {

    // region Fields

    private val detailedViewModel: DetailedCharacterViewModelImpl by viewModels()
    private val args: DetailedFragmentArgs by navArgs()
    private val characterId: Int
        get() = args.characterId
    // endregion

    // region Lifecycle overrides functions

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateProgressBarVisibility(true)
//        updateDetailedCharacterInformation()
        initObservers()
        attachListeners()
    }

    override fun onStop() {
        super.onStop()
    }

    // endregion

    // region Private Function

    private fun initObservers() {
        detailedViewModel.getDetailedCharacter(characterId).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                detailed_character_information_view.updateCharacterInformation(it)
            }
        })
        detailedViewModel.getComics().observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                updateComicViewVisibility(true)
                detailed_character_comic_view.updateComics(it)
            } else {
                updateComicViewVisibility(shouldShowComics = false)
            }
        })
    }

    private fun updateComicViewVisibility(shouldShowComics: Boolean) {
        if (shouldShowComics) {
            detailed_character_comic_view.visibility = VISIBLE
        } else {
            detailed_character_comic_view.visibility = GONE
        }
    }

    private fun attachListeners() {
        detailed_character_information_view.setListener(this)
    }

    private fun showToast(text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun showConfirmationControlDialog(isSquadMember: Boolean, name: String) {
        val builder = context?.let { AlertDialog.Builder(it) }

        builder?.setTitle(R.string.alert_dialog_title)
        builder?.setMessage(getString(R.string.alert_dialog_text, name))

        builder?.setPositiveButton(R.string.positive_button_text) { _, _ ->
            detailed_character_information_view.switchIcons()
            val toastText = (getString(R.string.hero_removed_text, name))
            showToast(toastText)
        }

        builder?.setNegativeButton(R.string.negative_button_text) { _, _ ->
        }
        val dialog: AlertDialog? = builder?.create()

        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(resources.getColor(R.color.marvelRedDark, null))
        dialog?.getButton(AlertDialog.BUTTON_NEGATIVE)
            ?.setTextColor(resources.getColor(R.color.color_forth_layer, null))

        dialog?.show()
    }

    private fun updateProgressBarVisibility(shouldBeShown: Boolean) {
        if (shouldBeShown) {
            character_detailed_view_pb.visibility = VISIBLE
        } else {
            character_detailed_view_pb.visibility = GONE
        }
    }


    // endregion

    // region CharacterViewInteraction functions

    override fun fabClicked(isSquadMember: Boolean, name: String) {
        if (isSquadMember) {
            showConfirmationControlDialog(isSquadMember, name)
        } else {
            val text = getString(R.string.hero_added_to_squad, name)
            detailed_character_information_view.switchIcons()
            showToast(text)
        }
    }

    override fun signalViewReady() {
        updateProgressBarVisibility(false)
    }

    // endregion

}