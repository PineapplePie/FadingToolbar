package com.pineapplepie.sample.xml.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pineapplepie.sample.SampleModel
import com.pineapplepie.sample.TitleModel
import com.pineapplepie.sample.databinding.ItemSampleBinding
import com.pineapplepie.sample.databinding.ItemTitleBinding

open class BaseViewHolder<T : ViewBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {

}

class SampleViewHolder(private val binding: ItemSampleBinding) : BaseViewHolder<ItemSampleBinding>(binding) {

    fun bind(model: SampleModel) = with(binding) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, model.colorRes))
    }
}

class TitleViewHolder(private val binding: ItemTitleBinding) : BaseViewHolder<ItemTitleBinding>(binding) {

    fun bind(model: TitleModel) = with(binding) {
        headerText.text = root.context.getString(model.text)
    }
}
