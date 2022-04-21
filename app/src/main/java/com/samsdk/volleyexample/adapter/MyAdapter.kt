package com.samsdk.volleyexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsdk.volleyexample.databinding.ItemLayoutBinding
import com.samsdk.volleyexample.mode.Course

class MyAdapter(
    private val items: ArrayList<Course>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var onClickItem: (Course) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val course = items[position]

        holder.binding.apply {
            textId.text = course.id.toString()
            textCcy.text = course.Ccy
            textDate.text = course.Date
        }
        holder.itemView.setOnClickListener {
            onClickItem.invoke(course)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MyViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
}