package com.example.squadmaker.characters.widgets.charactersadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.squadmaker.R
import com.example.squadmaker.characters.uimodel.UICharacter

class CharactersAdapter :
    ListAdapter<UICharacter, CharacterItemViewHolder>(UICharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        return CharacterItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}
