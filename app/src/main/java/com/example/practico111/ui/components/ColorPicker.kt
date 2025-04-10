package com.example.practico111.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.example.practico111.databinding.ButtonSelectedLayoutBinding

class ColorPicker(context: Context?, attrs: AttributeSet?) : LinearLayout(context,attrs) {
    private val binding: ButtonSelectedLayoutBinding
    private var selectedButton: Button? = null
    var value: Int = 0
        set(value){
            field = value
            reloadScreen()
            onValueChange?.invoke(value)
        }

    private var onValueChange: ((Int) -> Unit)? = null


    init {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ButtonSelectedLayoutBinding.inflate(
            inflater,
            this,
            true
        )

    }

    private fun readXMLattrs(attrs: AttributeSet?) {
        if (attrs==null)
        {
            return
        }
        context.withStyledAttributes(
            attrs,
            com.example.practico111.R.styleable.ColorPicker
        ) {
            value = getInt(com.example.practico111.R.styleable.ColorPicker_initialNumber, 1)
            reloadScreen()
        }
    }

    private fun setupEventListeners() {
        val buttons = listOf(binding.btnColor1, binding.btnColor2,
            binding.btnColor3, binding.btnColor4,
            binding.btnColor5, binding.btnColor6,
            binding.btnColor7, binding.btnColor8,
            binding.btnColor9, binding.btnColor10
        )
    }
    fun updateButtonSelection(selected: Button) {
        selectedButton?.setBackgroundColor(0xFF6750A4.toInt())

        // Cambiar el color del botón actual
        selected.setBackgroundColor(0xFFFFD700.toInt())

        // Guardar el botón seleccionado
        selectedButton = selected
    }

    private fun reloadScreen() {
        val buttons = listOf(binding.btnColor1, binding.btnColor2,
            binding.btnColor3, binding.btnColor4,
            binding.btnColor5, binding.btnColor6,
            binding.btnColor7, binding.btnColor8,
            binding.btnColor9, binding.btnColor10
        )
        buttons.forEach { it.setBackgroundColor(0xFF6750A4.toInt()) } // Restaurar color predeterminado
        selectedButton = buttons.getOrNull(value - 1)?.apply {
            setBackgroundColor(0xFFFFD700.toInt()) // Resalta el seleccionado
        }    }
    fun setOnValueChangeListener(listener: (Int) -> Unit) {
        onValueChange = listener
    }

    fun setOnColorChangeListener(listener: (Int) -> Unit) {

    }

}