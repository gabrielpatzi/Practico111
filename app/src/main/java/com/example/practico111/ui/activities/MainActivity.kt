package com.example.practico111.ui.activities

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1.R
import com.example.practico1.databinding.ActivityMainBinding
import com.example.practico1.ui.adapters.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {

        val tasks =     arrayListOf(
            "Cocinar almuerzo",
            "Lavar Servicio",
            "Lavar ropa",
            "Terminar tarea de moviles",
            "Estudiar examen de redes"

        )
        val adapter= TaskAdapter(tasks)
        binding.rvTaskList.apply {
            this.adapter= adapter
            layoutManager= LinearLayoutManager(this@MainActivity).apply {
                orientation= RecyclerView.VERTICAL
            }
        }
    }
}