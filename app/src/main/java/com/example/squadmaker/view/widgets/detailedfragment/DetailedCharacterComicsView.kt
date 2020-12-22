package com.example.squadmaker.view.widgets.detailedfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import com.example.squadmaker.view.uimodel.UIComic
import kotlinx.android.synthetic.main.view_detailed_character_comic.view.*

class DetailedCharacterComicsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_detailed_character_comic, this)
    }

    // region Public Functions

    fun updateComics(comicsList: List<UIComic>) {
        when {
            comicsList.isEmpty() -> {
                setNoComicsView()
                return
            }
            comicsList.size > 1 -> {
                setFirstComic(comicsList[0])
                setSecondComic(comicsList[1])
            }
            else -> {
                setFirstComic(comicsList[0])
            }
        }
        revealChildren()
    }

    // endregion

    // region Private Functions

    private fun setFirstComic(comic: UIComic) {
        Glide
            .with(context)
            .load(comic.resourceUri)
            .centerCrop()
            .error(R.drawable.logo)
            .into(first_comic_image)

        first_comic_title.text = comic.name
    }

    private fun setSecondComic(comic: UIComic) {
        Glide
            .with(context)
            .load(comic.resourceUri)
            .centerCrop()
            .error(R.drawable.logo)
            .into(second_comic_image)

        second_comic_title.text = comic.name
    }

    private fun setNoComicsView() {
        removeChildren()
    }

    private fun removeChildren() {
        for (child in character_detailed_view_comics_cl.children) {
            child.visibility = View.GONE
        }
    }

    private fun revealChildren() {
        for (child in character_detailed_view_comics_cl.children) {
            child.visibility = View.VISIBLE
        }
    }

    // endregion
}