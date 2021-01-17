package com.example.squadmaker.detailedcharacter.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.characters.uimodel.UICharacter
import com.example.squadmaker.detailedcharacter.uimodel.UIDetailedCharacter
import kotlinx.android.synthetic.main.fragment_detailed.view.*
import kotlinx.android.synthetic.main.view_detailed_character_information.view.*

class DetailedCharacterInformationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // region Fields

    private lateinit var characterViewInteraction: CharacterViewInteraction

    // endregion

    init {
        LayoutInflater.from(context).inflate(R.layout.view_detailed_character_information, this)
        setUpFabListener()
    }

    // region Public Functions

    fun updateCharacterInformation(character: UIDetailedCharacter) {
        setCharacterImage(character.resourceUrl)
        setCharacterName(character.name)
        setCharacterDescription(character.description)
        setFloatingActionButton(character.isSquadMember)
        signalViewReady()
    }

    fun switchIcons() {
        if (character_detailed_view_fab.tag == R.drawable.fire_white) {
            character_detailed_view_fab.setImageResource(R.drawable.heart_white)
            character_detailed_view_fab.tag = R.drawable.heart_white
        } else {
            character_detailed_view_fab.setImageResource(R.drawable.fire_white)
            character_detailed_view_fab.tag = R.drawable.fire_white
        }
    }

    fun setListener(characterViewInteraction: CharacterViewInteraction) {
        this.characterViewInteraction = characterViewInteraction
    }

    // endregion

    // region Private Functions

    private fun setCharacterImage(resourceUrl: String) {
        Glide
            .with(context)
            .load(resourceUrl)
            .centerCrop()
            .error(R.drawable.logo)
            .into(character_detailed_view_character_image)
    }

    private fun setCharacterName(name: String) {
        character_detailed_view_hero_name.text = name
    }

    private fun setCharacterDescription(description: String) {
        if (description.isBlank()) {
            character_detailed_view_character_description.text =
                resources.getText(R.string.no_description_available)
            return
        }
        character_detailed_view_character_description.text = description
    }

    private fun setFloatingActionButton(squadMember: Boolean) {
        if (squadMember) {
            character_detailed_view_fab.setImageResource(R.drawable.fire_white)
            character_detailed_view_fab.tag = R.drawable.fire_white
        } else {
            character_detailed_view_fab.setImageResource(R.drawable.heart_white)
            character_detailed_view_fab.tag = R.drawable.heart_white

        }
    }

    private fun setUpFabListener() {
        character_detailed_view_fab.setOnClickListener {
            val isSquadMember = character_detailed_view_fab.tag == R.drawable.fire_white
            characterViewInteraction.fabClicked(
                isSquadMember,
                detailed_character_information_view.character_detailed_view_hero_name.text.toString()
            )
        }
    }

    private fun signalViewReady() {
        characterViewInteraction.signalViewReady()
    }

    // endregion

    interface CharacterViewInteraction {
        fun fabClicked(isSquadMember: Boolean, name: String)
        fun signalViewReady()
    }
}