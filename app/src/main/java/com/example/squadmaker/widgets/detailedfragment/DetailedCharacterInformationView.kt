package com.example.squadmaker.widgets.detailedfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import kotlinx.android.synthetic.main.fragment_detailed.view.*
import kotlinx.android.synthetic.main.view_detailed_character_information.view.*

class DetailedCharacterInformationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    // region Fields

    private lateinit var fabInteraction: FabInteraction

    // endregion

    init {
        LayoutInflater.from(context).inflate(R.layout.view_detailed_character_information, this)
        setUpFabListener()
    }

    // region Public Functions

    fun updateCharacterInformation(characterInformation: DetailedCharacterEntity) {
        setCharacterImage(characterInformation.resourceUrl)
        setCharacterName(characterInformation.name)
        setCharacterDescription(characterInformation.description)
        setFloatingActionButton(characterInformation.isSquadMember)
    }

    fun setListener(fabInteraction: FabInteraction) {
        this.fabInteraction = fabInteraction
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
            character_detailed_view_fab.setImageResource(R.drawable.fire_vector)
            character_detailed_view_fab.tag = R.drawable.fire_vector
        } else {
            character_detailed_view_fab.setImageResource(R.drawable.heart_white)
            character_detailed_view_fab.tag = R.drawable.heart_white

        }
    }

    private fun setUpFabListener() {
        character_detailed_view_fab.setOnClickListener {
            val isSquadMember = character_detailed_view_fab.tag == R.drawable.fire_vector
            fabInteraction.fabClicked(
                isSquadMember,
                detailed_character_information_view.character_detailed_view_hero_name.text.toString()
            )
        }
    }

    fun switchIcons() {
        if (character_detailed_view_fab.tag == R.drawable.fire_vector) {
            character_detailed_view_fab.setImageResource(R.drawable.heart_white)
            character_detailed_view_fab.tag = R.drawable.heart_white
        } else {
            character_detailed_view_fab.setImageResource(R.drawable.fire_vector)
            character_detailed_view_fab.tag = R.drawable.fire_vector
        }
    }

    // endregion

    interface FabInteraction {
        fun fabClicked(isSquadMember: Boolean, name: String)
    }
}