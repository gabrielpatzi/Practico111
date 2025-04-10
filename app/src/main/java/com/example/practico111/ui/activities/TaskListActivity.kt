package com.example.practico111.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico111.R
import com.example.practico111.databinding.ActivityTaskListBinding
import com.example.practico111.models.Task
import com.example.practico111.repositories.TaskRepository
import com.example.practico111.ui.adapters.TaskListAdapter

class TaskListActivity : AppCompatActivity(), TaskListAdapter.TaskListClickListener {
    private lateinit var binding: ActivityTaskListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityTaskListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnCreateTask.setOnClickListener{
            onTaskCreateClick()
        }
    }

    private fun onTaskCreateClick() {

        val intent = TaskDetailActivity.createIntent(this)
        startActivity(intent)
    }

    override fun onResume (){

        super.onResume()
        //Log.d("TaskListActivity", "onResume called")
        reloadData()

    }

    private fun reloadData() {
        val tasks = TaskRepository.getTask()
        val adapter = binding.rvTaskList.adapter as TaskListAdapter
        adapter.setData(tasks)

    }

    private fun setupRecyclerView() {

        val adapter= TaskListAdapter(arrayListOf())



        binding.rvTaskList.apply {
            this.adapter=adapter
            layoutManager= LinearLayoutManager(this@TaskListActivity).apply {
                orientation= RecyclerView.VERTICAL
            }
            addItemDecoration(
                DividerItemDecoration(
                    this@TaskListActivity,
                    LinearLayoutManager.VERTICAL)
            )
        }
        adapter.setOnTaskListClickListener(this)


    }

    override fun onTaskListClick(task: Task) {
        Toast.makeText(this,"Click en tarea: ${task.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onTaskEditClick(task: Task) {
        Log.d("TaskListActivity", "Editando tarea: ${task.title}")
        val intent = TaskDetailActivity.detailIntent(this,task)
        startActivity(intent)
    }



}