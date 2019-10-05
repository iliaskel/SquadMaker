package com.example.squadmaker.widgets.detailedfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.squadmaker.R

class DetailedCharacterComicsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {


    init {
        LayoutInflater.from(context).inflate(R.layout.view_detailed_character_comic, this)
    }
}