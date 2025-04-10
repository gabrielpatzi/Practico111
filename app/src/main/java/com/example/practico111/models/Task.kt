package com.example.practico111.models

import android.app.ActivityManager.TaskDescription
import android.graphics.Color
import java.io.Serializable

data class Task (
    val id: Int,
    val title: String,
    val description: String,
    var checked: Boolean = false,
    val color: String =  "#FFFFFF"
): Serializable