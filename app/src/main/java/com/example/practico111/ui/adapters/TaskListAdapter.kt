package com.example.practico111.ui.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1.databinding.TaskListItemBinding
import com.example.practico1.models.Task

class TaskListAdapter(
    var task: ArrayList<Task>
): RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    var taskListClickListener: TaskListClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskListItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return ViewHolder(binding)    }
    fun setOnTaskListClickListener(listener: TaskListClickListener){
        taskListClickListener=listener
    }


    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = task[position]
        holder.bind(item , taskListClickListener )
    }

    fun setData(tasks: java.util.ArrayList<Task>) {
        this.task= tasks
        notifyDataSetChanged()
    }

    class ViewHolder (private val binding : TaskListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task, listener: TaskListClickListener?) {
            binding.lblTitle.text = item.title
            binding.lblText.text= item.description



            binding.checkBox.setOnCheckedChangeListener(null) // Para evitar listeners duplicados
            binding.checkBox.isChecked = item.checked // o true si quieres restaurar desde datos

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                item.checked= isChecked
                updateViewState(isChecked)
            }


            binding.root.setOnClickListener{
                listener?.onTaskListClick(item)
            }
            binding.btnTaskEdit.setOnClickListener{
                listener?.onTaskEditClick(item)
            }


        }
        private fun updateViewState(isChecked: Boolean) {
            val strikeFlag = if (isChecked) {
                Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                0
            }

            binding.lblTitle.paintFlags = strikeFlag
            binding.lblText.paintFlags = strikeFlag

            binding.btnTaskEdit.isEnabled = !isChecked
            binding.lblTitle.isEnabled = !isChecked
            binding.lblText.isEnabled = !isChecked
        }

    }
    interface TaskListClickListener {
        fun onTaskListClick(task: Task )
        fun onTaskEditClick (task:Task)
    }

}