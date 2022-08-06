package com.pineapplepie.fadingtoolbar.xml.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pineapplepie.fadingtoolbar.SampleModel
import com.pineapplepie.fadingtoolbar.TitleModel
import com.pineapplepie.fadingtoolbar.databinding.ItemSampleBinding
import com.pineapplepie.fadingtoolbar.databinding.ItemTitleBinding

open class BaseViewHolder<T : ViewBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {

}

class SampleViewHolder(private val binding: ItemSampleBinding) : BaseViewHolder<ItemSampleBinding>(binding) {

    fun bind(model: SampleModel) = with(binding) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, model.colorRes))
    }
}

class TitleViewHolder(private val binding: ItemTitleBinding) : BaseViewHolder<ItemTitleBinding>(binding) {

    fun bind(model: TitleModel) = with(binding) {
        footerText.text = root.context.getString(model.text)
    }
}
