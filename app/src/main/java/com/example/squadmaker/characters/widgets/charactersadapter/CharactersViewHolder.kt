package com.example.squadmaker.characters.widgets.charactersadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.characters.uimodel.UICharacter
import kotlinx.android.synthetic.main.list_item_character.view.*

class CharacterItemViewHolder
constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: UICharacter) {
        setHeroThumbnail(item.thumbnailPath)
        setHeroName(item.name)
        itemView.setOnClickListener {
            item.navigateToDetailedViewAction.invoke(it)
        }
    }

    private fun setHeroName(name: String) {
        itemView.character_list_item_character_name.text = name
    }

    private fun setHeroThumbnail(thumbnailPath: String) {
        Glide
            .with(itemView.context)
            .load(thumbnailPath)
            .error(R.drawable.logo)
            .circleCrop()
            .into(itemView.character_list_item_character_image)
    }
}