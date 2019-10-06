package com.example.squadmaker.view.detailedfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.squadmaker.R
import com.example.squadmaker.viewmodel.DetailedViewModel
import com.example.squadmaker.widgets.detailedfragment.DetailedCharacterInformationView
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment : Fragment(), DetailedCharacterInformationView.FabInteraction {

    // region fields

    private lateinit var viewModel: DetailedViewModel

    // endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        updateCharacterInformationView()
        initObservers()
        attachListeners()
    }

    // region Private Functions

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this)[DetailedViewModel::class.java]
    }

    private fun updateCharacterInformationView() {
        val id = arguments?.let { DetailedFragmentArgs.fromBundle(it).characterId }
        if (id != null) {
            viewModel.updateDetailedCharacter(id)
        }
    }

    private fun initObservers() {
        viewModel.getDetailedCharacter().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                detailed_character_information_view.updateCharacterInformation(it)
            }
        })
        viewModel.getComics().observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                val extraAvailableComics = it[0].availableComics.minus(it.size)
                val shouldShowMoreLabel = extraAvailableComics > 0
                updateComicViewVisibility(true, shouldShowMoreLabel)
                detailed_character_comic_view.updateComics(it)
                detailed_Character_available_comics_view.updateAvailability(extraAvailableComics)
            } else {
                updateComicViewVisibility(false, false)
            }
        })
    }

    private fun updateComicViewVisibility(shouldShowComics: Boolean, shouldShowMoreLabel: Boolean) {
        if (shouldShowComics) {
            detailed_character_comic_view.visibility = VISIBLE
        } else {
            detailed_character_comic_view.visibility = GONE
        }
        if (shouldShowMoreLabel) {
            detailed_Character_available_comics_view.visibility = VISIBLE
        } else {
            detailed_Character_available_comics_view.visibility = GONE
        }
    }

    private fun attachListeners() {
        detailed_character_information_view.setListener(this)
    }

    @SuppressLint("ResourceType")
    private fun showToast(text: String) {
        // fake margin
        val textToShow = "  ".plus(text).plus("  ")
        val toast = Toast.makeText(context, textToShow, Toast.LENGTH_LONG)
        toast.view.setBackgroundColor(resources.getInteger(R.color.color_second_layer))
        toast.show()
    }

    private fun showConfirmationControlDialog(isSquadMember: Boolean, name: String) {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Attention!")
        builder?.setMessage("Are you sure that you want to remove $name from your squad List?")

        builder?.setPositiveButton("Yes") { _, _ ->
            detailed_character_information_view.switchIcons()
            val toastText = name.plus(" ").plus(" removed from your squad.")
            showToast(toastText)
            viewModel.updateSquadList(isSquadMember)
        }

        builder?.setNegativeButton("No") { dialog, which ->
        }
        val dialog: AlertDialog? = builder?.create()

        dialog?.show()
    }

    // endregion

    // region Overrides function

    override fun fabClicked(isSquadMember: Boolean, name: String) {
        if (isSquadMember) {
            showConfirmationControlDialog(isSquadMember, name)
        } else {
            val text =
                name
                    .plus(" ")
                    .plus("added to your squad!")
            detailed_character_information_view.switchIcons()
            viewModel.updateSquadList(isSquadMember)
            showToast(text)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.removeDetailedCharacter()
    }

    // endregion

}