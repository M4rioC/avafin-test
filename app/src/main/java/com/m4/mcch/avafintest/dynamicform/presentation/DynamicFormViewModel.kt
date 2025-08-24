package com.m4.mcch.avafintest.dynamicform.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m4.mcch.avafintest.R
import com.m4.mcch.avafintest.dynamicform.domain.models.FormInputField
import com.m4.mcch.avafintest.dynamicform.domain.usecases.GetDynamicFormResult
import com.m4.mcch.avafintest.dynamicform.domain.usecases.GetDynamicFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Manages the state of the dynamic form screen.
 */
@HiltViewModel
class DynamicFormViewModel @Inject constructor(
    private val getDynamicFormUseCase: GetDynamicFormUseCase
): ViewModel() {

    private val _viewState = MutableLiveData<DynamicFormUIState>()
    val viewState: LiveData<DynamicFormUIState> = _viewState

    private var formInputFields: List<FormInputField> = listOf()

    /**
     * Loads the dynamic form.
     */
    fun loadForm() {
        _viewState.postValue(DynamicFormUIState.Loading)
        viewModelScope.launch {
            when(val result = getDynamicFormUseCase.execute()) {
                is GetDynamicFormResult.Success -> {
                    formInputFields = result.data.map {
                        FormInputField(detail = it)
                    }
                    _viewState.postValue(DynamicFormUIState.ShowForm(formInputFields))
                }
                is GetDynamicFormResult.Error -> {
                    _viewState.postValue(DynamicFormUIState.Error(result.message))
                }
            }
        }
    }

    /**
     * Validates each field of the form.
     */
    fun validateForm() {
        val isValid = formInputFields.all { it.isValid() }
        if (isValid) {
            _viewState.postValue(
                DynamicFormUIState.SuccessfullyValidated(R.string.message_form_successfully_validated)
            )
        } else {
            _viewState.postValue(
                DynamicFormUIState.Error(R.string.message_form_validation_error)
            )
        }
    }

}