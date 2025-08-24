package com.m4.mcch.avafintest.dynamicform.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.m4.mcch.avafintest.databinding.ItemCheckboxFieldBinding
import com.m4.mcch.avafintest.databinding.ItemInputFieldBinding
import com.m4.mcch.avafintest.databinding.ItemSelectFieldBinding
import com.m4.mcch.avafintest.dynamicform.domain.models.FieldType
import com.m4.mcch.avafintest.dynamicform.domain.models.FormInputField

/**
 * Adapter for the dynamic form.
 */
class DynamicFormAdapter(
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<FormInputField>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].detail.type) {
            FieldType.TEXT -> 0
            FieldType.CHECKBOX -> 1
            FieldType.SELECT -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        return when (viewType) {
            0 -> TextFieldViewHolder(ItemInputFieldBinding.inflate(inflater, parent, false))
            1 -> CheckboxFieldViewHolder(ItemCheckboxFieldBinding.inflate(inflater, parent, false))
            2 -> SpinnerFieldViewHolder(ItemSelectFieldBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextFieldViewHolder -> holder.bind(items[position])
            is CheckboxFieldViewHolder -> holder.bind(items[position])
            is SpinnerFieldViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size

    // ViewHolder for text input field
    inner class TextFieldViewHolder(private val binding: ItemInputFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(inputField: FormInputField) {
            binding.etInputField.hint = inputField.detail.label
            binding.etInputField.doOnTextChanged { text, _, _, _ ->
                inputField.input = text.toString()
            }
        }
    }

    // ViewHolder for checkbox field
    inner class CheckboxFieldViewHolder(private val binding: ItemCheckboxFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(inputField: FormInputField) {
            binding.llContainer.removeAllViews()
            inputField.detail.options.forEach { option ->
                val checkBox = CheckBox(binding.root.context).apply {
                    text = option
                    setOnCheckedChangeListener { _, isChecked ->
                        val selected = inputField.input.split(",").toMutableList()
                        if (isChecked) selected.add(option) else selected.remove(option)
                        inputField.input = selected.joinToString(",")
                    }
                }
                binding.llContainer.addView(checkBox)
            }
        }
    }

    // ViewHolder for spinner field
    inner class SpinnerFieldViewHolder(private val binding: ItemSelectFieldBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(inputField: FormInputField) {
            val adapter = ArrayAdapter(binding.root.context, android.R.layout.simple_spinner_item, inputField.detail.options)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spOptions.adapter = adapter

            binding.spOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    inputField.input = inputField.detail.options[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }
    }
}