package com.example.practico111.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1.databinding.TaskListItemBinding

class TaskAdapter(
    var tasks: ArrayList<String>,
): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskListItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item)

    }

    class ViewHolder(private val binding : TaskListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: String) {
            binding.lblText.text= item
            binding.lblTitle.text=item
        }
    }
}