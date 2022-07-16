package com.bhavishaymankani.machinetestappic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.machinetestappic.listeners.OnItemClickListener
import com.bhavishaymankani.machinetestappic.databinding.ListBinding

class Adapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private lateinit var list: List<String>

    class ViewHolder(val binding: ListBinding, val onItemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return  ViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.selectTv.text = list[position]
    }

    override fun getItemCount() = list.size

    fun setList(list: List<String>) = list.also { this.list = it }
}