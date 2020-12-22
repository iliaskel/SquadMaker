package com.example.squadmaker.view.mainfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.view.uimodel.UISquadEntry
import kotlinx.android.synthetic.main.list_item_squad.view.*

class MySquadAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UISquadEntry>() {
        override fun areItemsTheSame(oldItem: UISquadEntry, newItem: UISquadEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UISquadEntry, newItem: UISquadEntry): Boolean {
            return oldItem.id == newItem.id && oldItem.thumbnailPath == newItem.thumbnailPath
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MySquadItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_squad,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MySquadItemViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<UISquadEntry>) {
        differ.submitList(list)
    }

    class MySquadItemViewHolder
    constructor(
        itemView: View,
        private val interactions: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UISquadEntry) {
            setHeroesThumbnail(item.thumbnailPath)
            setClickListeners(item.id)
        }

        private fun setHeroesThumbnail(thumbnailPath: String) {
            Glide
                .with(itemView.context)
                .load(thumbnailPath)
                .circleCrop()
                .into(itemView.my_squad_list_item_image)
        }

        private fun setClickListeners(id: Int) {
            itemView.setOnClickListener {
                interactions?.squadMemberClicked(id)
            }
        }
    }

    interface Interaction {
        fun squadMemberClicked(id: Int)
    }
}
