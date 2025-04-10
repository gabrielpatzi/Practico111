package com.example.practico111.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.Person
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico111.R
import com.example.practico111.databinding.ActivityTaskDetailBinding
import com.example.practico111.models.Task
import com.example.practico111.repositories.TaskRepository

class TaskDetailActivity : AppCompatActivity() {
    private var task:Task?=null
    private lateinit var binding: ActivityTaskDetailBinding
    private var selectedColor: String = "#FFFFFF"

   // private lateinit var colorButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityTaskDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       /* colorButtons = listOf(
        binding.btnColor1, binding.btnColor2,
        binding.btnColor3, binding.btnColor4,
        binding.btnColor5, binding.btnColor6,
        binding.btnColor7, binding.btnColor8,
        binding.btnColor9, binding.btnColor10
        )*/

        //RECIBE LA TAREA MANDADA POR onTaskEditClick DESDE TASKLISTACTIVITY
        task= intent.getSerializableExtra(param_task) as Task?
        if (task != null) {
            loadTaskDetails(task)
        } else {
            createTaskDetails()
        }
        setupEventListeners()
    }

    private fun createTaskDetails() {


    }

    private fun setupEventListeners() {
        binding.colorPicker.setOnColorChangeListener { newColor ->
            selectedColor = newColor.toString()
            Toast.makeText(this, "Color seleccionado: $selectedColor", Toast.LENGTH_SHORT).show()
        }

        binding.btnCancelTask.setOnClickListener{finish()}
        binding.btnSaveTask.setOnClickListener{
            saveTask()
        }
       /* colorButtons.forEach { button ->
            button.setOnClickListener {
                selectedColor = button.tag.toString()
                // Efecto visual para mostrar el botón seleccionado
                colorButtons.forEach { it.alpha = 1.0f }
                button.alpha = 0.5f
            }
        }*/


    }

    private fun saveTask() {

        val title = binding.txtEditTitle.editText?.text.toString()
        val description = binding.txtEditDescription.editText?.text.toString()

        if (title.isBlank()) {
            binding.txtEditTitle.error = "Debe ingresar un título"
            return
        }
        val newTask = if (task == null) {
            // Creamos una nueva tarea con un nuevo ID
            TaskRepository.getNextId()?.let { newId ->
              //  Task(newId, title, description,false, selectedColor)
                Task(newId, title, description,false, selectedColor)

            }
        } else {
            // Editamos la tarea existente
            //Task(task!!.id, title, description, task!!.checked, selectedColor)
            Task(task!!.id, title, description, task!!.checked, selectedColor)

        }

        newTask?.let {
            TaskRepository.saveTask(it)
        }


        /*TaskRepository.saveTask(
            Task(
                task?.id ?: 0,
                binding.txtEditTitle.editText?.text.toString(),
                binding.txtEditDescription.editText?.text.toString()
            )
        )*/
        finish()

    }

    private fun loadTaskDetails(task: Task?) {
        if (task==null){
            return
        }
        binding.txtEditTitle.editText?.setText(task.title)
        binding.txtEditDescription.editText?.setText(task.description)

    }

    companion object {

        const val param_task = "task"
        fun detailIntent(context: Context, task: Task): Intent {
            val intent = Intent(context, TaskDetailActivity::class.java)
            intent.putExtra(param_task, task)
            return intent
        }
        fun createIntent(context: Context): Intent {
            return Intent(context, TaskDetailActivity::class.java)
        }
    }
}