package com.example.squadmaker.widgets.mainfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squadmaker.R
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.view.mainfragment.CharactersAdapter
import kotlinx.android.synthetic.main.view_characters.view.*

class CharactersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs), CharactersAdapter.Interaction {

    private lateinit var characterInteraction: CharacterInteraction

    private lateinit var charactersAdapter: CharactersAdapter

    init {
        LayoutInflater.from(context).inflate(R.layout.view_characters, this)
        orientation = VERTICAL
        initRecyclerView()
    }

    fun setListener(characterInteraction: CharacterInteraction) {
        this.characterInteraction = characterInteraction
    }

    fun updateCharactersList(characterModelList: List<CharacterEntity>) {
        charactersAdapter.submitList(characterModelList)
    }

    private fun initRecyclerView() {
        main_fragment_characters_rv.layoutManager = LinearLayoutManager(context)
        main_fragment_characters_rv.setHasFixedSize(false)
        charactersAdapter = CharactersAdapter(this)
        main_fragment_characters_rv.adapter = charactersAdapter
    }

    override fun characterClicked(id: Int) {
        characterInteraction.characterClicked(id)
    }

    interface CharacterInteraction {
        fun characterClicked(id: Int)
    }
}