package com.m4.mcch.avafintest.dynamicform.data

import com.m4.mcch.avafintest.R
import com.m4.mcch.avafintest.dynamicform.data.remote.DynamicFormApi
import com.m4.mcch.avafintest.dynamicform.domain.models.FormFieldDetail
import com.m4.mcch.avafintest.dynamicform.domain.repository.DynamicFormRepository
import com.m4.mcch.avafintest.remote.RESPONSE_CODE_SUCCESS
import com.m4.mcch.avafintest.remote.Response

/**
 * Implementation of the [DynamicFormRepository] interface.
 */
class DynamicFormRepositoryImpl(
    private val api: DynamicFormApi
): DynamicFormRepository {

    override suspend fun getDynamicForm(): Response<List<FormFieldDetail>> {
        val response = api.getDynamicForm()
        return if (response.ok == RESPONSE_CODE_SUCCESS) {
            val data = response.data.entries.map { it.value.toDomain(id = it.key) }
            Response.Success(data)
        } else {
            Response.Error(R.string.generic_error_message)
        }
    }

}