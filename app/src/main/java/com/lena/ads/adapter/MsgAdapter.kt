package com.lena.ads.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lena.ads.data.Message
import com.lena.ads.databinding.ItemMsgBinding
import kotlinx.android.synthetic.main.item_msg.view.*

class MsgAdapter(
    private var items: MutableList<Message>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<MsgAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMsgBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onBtnRewardClick()
    }

    class ViewHolder(private var binding: ItemMsgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message, listener: OnItemClickListener?) {
            binding.message = message
            if (listener != null) {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition) }
                binding.root.btnShowReward.setOnClickListener { listener.onBtnRewardClick() }
            }
            binding.executePendingBindings()
        }
    }
}