package com.example.squadmaker.characters.widgets.charactersadapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squadmaker.R
import com.example.squadmaker.characters.uimodel.UICharacter
import kotlinx.android.synthetic.main.view_characters.view.*

class CharactersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    private lateinit var charactersAdapter: CharactersAdapter

    init {
        initRecyclerView()
    }

    fun updateCharactersList(characterModelList: List<UICharacter>) {
        charactersAdapter.submitList(characterModelList)
    }

    private fun initRecyclerView() {
        LayoutInflater.from(context).inflate(R.layout.view_characters, this)
        orientation = VERTICAL
        main_fragment_characters_rv.layoutManager = LinearLayoutManager(context)
        main_fragment_characters_rv.setHasFixedSize(false)
        charactersAdapter = CharactersAdapter()
        main_fragment_characters_rv.adapter = charactersAdapter
    }
}