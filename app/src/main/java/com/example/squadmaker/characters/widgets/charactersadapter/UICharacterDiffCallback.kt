package com.example.squadmaker.characters.widgets.charactersadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.squadmaker.characters.uimodel.UICharacter

class UICharacterDiffCallback : DiffUtil.ItemCallback<UICharacter>() {
    override fun areItemsTheSame(oldItem: UICharacter, newItem: UICharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UICharacter, newItem: UICharacter): Boolean {
        return oldItem.name == newItem.name && oldItem.thumbnailPath == oldItem.thumbnailPath
    }
}