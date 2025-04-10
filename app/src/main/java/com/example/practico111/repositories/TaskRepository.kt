package com.example.practico111.repositories

import com.example.practico111.models.Task

object TaskRepository {
    private    val tasks= arrayListOf(
        Task(1,"Cocinar almuerzooooooooooooooooooo", "Debes hacer pollo y arroz"),
        Task(2, "Estudiar Examen de Redes", "Debes hacer pollo y arrozzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"),

    )
    fun getTask():ArrayList<Task>{
        return  tasks.clone() as ArrayList<Task>

    }
    fun saveTask(task: Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task // Actualizar
        } else {
            tasks.add(task.copy(id = getNextId())) // Crear nueva tarea con ID único
        }
    }

    fun getNextId(): Int {
        return (tasks.maxOfOrNull { it.id } ?: 0) + 1
    }

}