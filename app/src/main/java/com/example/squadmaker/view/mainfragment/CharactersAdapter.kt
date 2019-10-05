package com.example.squadmaker.view.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.model.database.entity.CharacterEntity
import kotlinx.android.synthetic.main.list_item_character.view.*

class CharactersAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CharacterEntity>() {

        override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterEntity,
            newItem: CharacterEntity
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return CharacterItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_character,
                parent,
                false
            ),
            interaction
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

    fun submitList(list: List<CharacterEntity>) {
        differ.submitList(list)
    }

    class CharacterItemViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CharacterEntity) {
            setHeroThumbnail(item.thumbnailPath)
            setHeroName(item.name)
            setClickListeners(item.id)
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

        private fun setClickListeners(id: Int) {
            itemView.setOnClickListener {
                interaction?.characterClicked(id)
            }
        }
    }

    interface Interaction {
        fun characterClicked(id: Int)
    }
}
