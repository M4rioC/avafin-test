package com.m4.mcch.avafintest.dynamicform.presentation

import com.m4.mcch.avafintest.dynamicform.domain.models.FormInputField

/**
 * Represents the state of the dynamic form screen.
 */
sealed class DynamicFormUIState {
    data object Loading : DynamicFormUIState()
    data class ShowForm(val fields: List<FormInputField>) : DynamicFormUIState()
    data class SuccessfullyValidated(val message: Int) : DynamicFormUIState()
    data class Error(val message: Int) : DynamicFormUIState()
}