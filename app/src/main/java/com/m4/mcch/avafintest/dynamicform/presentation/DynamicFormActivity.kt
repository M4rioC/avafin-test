package com.m4.mcch.avafintest.dynamicform.presentation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.m4.mcch.avafintest.R
import com.m4.mcch.avafintest.databinding.ActivityDynamicFormBinding
import com.m4.mcch.avafintest.dynamicform.domain.models.FormInputField
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity that displays the dynamic form.
 */
@AndroidEntryPoint
class DynamicFormActivity: AppCompatActivity(), Observer<DynamicFormUIState> {

    private val viewModel: DynamicFormViewModel by viewModels()
    private var progressDialog: Dialog? = null

    private lateinit var binding: ActivityDynamicFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoadingDialog()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.viewState.observe(this, this)
        viewModel.loadForm()
    }

    private fun showForm(formInputFields: List<FormInputField>) {
        val adapter = DynamicFormAdapter(this)
        adapter.items = formInputFields

        binding.run {
            rvForm.adapter = adapter
            rvForm.layoutManager = LinearLayoutManager(this@DynamicFormActivity)

            btnValidateForm.setOnClickListener {
                viewModel.validateForm()
            }
        }
    }

    private fun initLoadingDialog() {
        progressDialog = Dialog(this)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog?.setContentView(R.layout.dialog_loading)
        progressDialog?.setCancelable(false)
    }

    /**
     * Shows the loading screen.
     */
    private fun showLoading() {
        progressDialog?.show()
    }

    /**
     * Dismisses the loading screen.
     */
    private fun dismissLoading() {
        progressDialog?.dismiss()
    }

    /**
     * Shows a generic toast with a message.
     */
    private fun showToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onChanged(value: DynamicFormUIState) {
        when (value) {
            is DynamicFormUIState.Loading -> showLoading()
            is DynamicFormUIState.ShowForm -> {
                dismissLoading()
                showForm(value.fields)
            }
            is DynamicFormUIState.SuccessfullyValidated -> {
                dismissLoading()
                showToast(value.message)
            }
            is DynamicFormUIState.Error -> {
                dismissLoading()
                showToast(value.message)
            }
        }
    }
}