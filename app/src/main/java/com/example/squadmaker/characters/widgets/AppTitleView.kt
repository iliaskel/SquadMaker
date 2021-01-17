package com.example.squadmaker.characters.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.squadmaker.R
import kotlinx.android.synthetic.main.view_app_logo.view.*

class AppTitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_app_logo, this)
        orientation = VERTICAL
        updateApplicationTitleText()
    }

    fun updateApplicationTitleText() {
        Glide.with(context)
            .load(R.drawable.logo)
            .into(main_fragment_logo)
        Glide.with(context)
            .load(R.drawable.logo_squad_text_white)
            .centerCrop()
            .into(main_fragment_logo_squad_text)
        Glide.with(context)
            .load(R.drawable.logo_maker_text_white)
            .centerCrop()
            .into(main_fragment_logo_maker_text)

    }
}