package com.example.squadmaker.view.widgets.detailedfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.squadmaker.R
import kotlinx.android.synthetic.main.view_available_comics.view.*

class DetailedCharactesAvailableComicsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_available_comics, this)
    }

    fun updateAvailability(availableComics: Int) {
        if (availableComics == 0) {
            detailed_character_comic_view_more_comics_mcv.visibility = View.GONE
        } else {
            detailed_character_comic_view_more_comics_label.text =
                availableComics.toString()
                    .plus(" ")
                    .plus(
                        context.getString(R.string.more_comics_available_label_substring)
                    )
        }
    }
}