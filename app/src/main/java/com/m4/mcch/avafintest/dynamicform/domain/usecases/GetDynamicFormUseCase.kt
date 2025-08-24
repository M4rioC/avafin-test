package com.m4.mcch.avafintest.dynamicform.domain.usecases

import com.m4.mcch.avafintest.dynamicform.domain.models.FormFieldDetail
import com.m4.mcch.avafintest.dynamicform.domain.repository.DynamicFormRepository
import com.m4.mcch.avafintest.remote.Response
import javax.inject.Inject

/**
 * Use case for getting the dynamic form.
 */
class GetDynamicFormUseCase @Inject constructor(
    private val repository: DynamicFormRepository
) {

    suspend fun execute(): GetDynamicFormResult {
        return when (val result = repository.getDynamicForm()) {
            is Response.Success -> {
                val formFields = result.data
                    .filter { it.isVisible }
                    .sortedBy { it.order }
                GetDynamicFormResult.Success(formFields)
            }
            is Response.Error -> GetDynamicFormResult.Error(result.message)
        }
    }
}

/**
 * Represents the result of the [GetDynamicFormUseCase].
 */
sealed class GetDynamicFormResult {
    data class Success(val data: List<FormFieldDetail>): GetDynamicFormResult()
    data class Error(val message: Int): GetDynamicFormResult()
}