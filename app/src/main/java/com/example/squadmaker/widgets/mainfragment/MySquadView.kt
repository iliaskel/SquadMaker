package com.example.squadmaker.widgets.mainfragment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squadmaker.R
import com.example.squadmaker.model.database.entity.SquadEntity
import com.example.squadmaker.view.mainfragment.MySquadAdapter
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.view_my_squad.view.*

class MySquadView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs), MySquadAdapter.Interaction {

    // region Fields

    private lateinit var mySquadAdapter: MySquadAdapter
    private lateinit var squadInteraction: SquadInteraction

    // endregion

    init {
        LayoutInflater.from(context).inflate(R.layout.view_my_squad, this)
        orientation = VERTICAL
        initRecyclerView()
    }

    fun setListener(squadInteraction: SquadInteraction) {
        this.squadInteraction = squadInteraction
    }

    fun updateView(mySquadModelList: List<SquadEntity>) {
        setTitle()
        mySquadAdapter.submitList(mySquadModelList)
    }

    private fun setTitle() {
        main_fragment_my_squad_title.text = resources.getString(R.string.my_squad_title)
    }

    fun updateViewToInvisible() {
        main_fragment_my_squad_view.visibility = GONE
    }

    private fun initRecyclerView() {
        main_fragment_squad_rv.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        main_fragment_squad_rv.setHasFixedSize(false)
        mySquadAdapter = MySquadAdapter(this)
        main_fragment_squad_rv.adapter = mySquadAdapter
    }

    override fun squadMemberClicked(id: Int) {
        squadInteraction.squadCharacterClickerClicked(id)
    }

    interface SquadInteraction {
        fun squadCharacterClickerClicked(id: Int)
    }
}