package com.bimabagaskhoro.myapplicatio.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bimabagaskhoro.myapplicatio.R
import com.bimabagaskhoro.myapplicatio.databinding.ItemsAlphabetBinding
import com.bimabagaskhoro.myapplicatio.domain.model.ItemAlphabet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@SuppressLint("NotifyDataSetChanged")
class AlphabetAdapter : RecyclerView.Adapter<AlphabetAdapter.ViewHolder>(){
    private var listData = ArrayList<ItemAlphabet>()
    var onItemClick: ((ItemAlphabet) -> Unit)? = null

    fun setData(newListData: List<ItemAlphabet>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_alphabet, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount()= listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsAlphabetBinding.bind(itemView)
        fun bind(data: ItemAlphabet) {
            binding.apply {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(EXTRA_LINK + data.imageAlphabet)
                    .into(imgAlphabet)
                tvAlphabet.text = data.tittleAlphabet
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
    companion object {
        const val EXTRA_LINK = "http://172.20.10.11/bisindo-api-php/image_alphabet/"
    }
}