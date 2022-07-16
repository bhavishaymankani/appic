package com.bhavishaymankani.machinetestappic.activities.base

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class BaseActivity : AppCompatActivity() {

    fun RecyclerView.create(recyclerviewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        this.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = recyclerviewAdapter
        }

    }
}