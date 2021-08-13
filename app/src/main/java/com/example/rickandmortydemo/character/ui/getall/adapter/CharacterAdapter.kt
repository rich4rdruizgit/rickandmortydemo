package com.example.rickandmortydemo.character.ui.getall.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortydemo.R
import com.example.rickandmortydemo.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class RecyclerCharacterAdapter (private var _characters: MutableList<CharacterCard>,
                            private var _listener: OnCardClickListener) :
    RecyclerView.Adapter<RecyclerCharacterAdapter.ViewHolder>() {

    private lateinit var _mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _mContext = parent.context
        val view = LayoutInflater.from(_mContext).inflate(R.layout.item_character, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = _characters[position]

        with(holder){
            binding.tvCardview.text = card.text
            Picasso.get().load(card.imageUrl).into(binding.imgCardview)
            setListener(card)
        }
    }

    override fun getItemCount(): Int = _characters.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemCharacterBinding.bind(view)

        fun setListener(card: CharacterCard){
            binding.root.setOnClickListener { _listener.onClick(card) }
        }
    }
}