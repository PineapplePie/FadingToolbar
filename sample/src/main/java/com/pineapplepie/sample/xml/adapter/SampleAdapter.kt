package com.pineapplepie.sample.xml.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pineapplepie.sample.SampleModel
import com.pineapplepie.sample.TitleModel
import com.pineapplepie.sample.UiModel
import com.pineapplepie.sample.databinding.ItemSampleBinding
import com.pineapplepie.sample.databinding.ItemTitleBinding

class SampleAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    // it's only for the simplicity purposes and it's not a proper way to update your list, check DiffUtil instead.
    var data = mutableListOf<UiModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is TitleModel) TITLE_ITEM else DEFAULT_ITEM
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
       when (getItemViewType(position)) {
           TITLE_ITEM -> (holder as TitleViewHolder).bind(data[position] as TitleModel)
           DEFAULT_ITEM -> (holder as SampleViewHolder).bind(data[position] as SampleModel)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DEFAULT_ITEM -> SampleViewHolder(ItemSampleBinding.inflate(inflater, parent, false))
            TITLE_ITEM -> TitleViewHolder(ItemTitleBinding.inflate(inflater, parent, false))
            else -> throw Exception("No way!")
        }
    }
}


private const val TITLE_ITEM = 0
private const val DEFAULT_ITEM = 1