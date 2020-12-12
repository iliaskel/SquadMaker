package com.example.squadmaker.view.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.view.uimodel.UICharacter
import kotlinx.android.synthetic.main.list_item_character.view.*

class CharactersAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UICharacter>() {

        override fun areItemsTheSame(oldItem: UICharacter, newItem: UICharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UICharacter,
            newItem: UICharacter
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.thumbnailPath == oldItem.thumbnailPath
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_character,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterItemViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<UICharacter>) {
        differ.submitList(list)
    }

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
}
