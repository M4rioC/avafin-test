package com.m4.mcch.avafintest.dynamicform.domain.repository

import com.m4.mcch.avafintest.dynamicform.domain.models.FormFieldDetail
import com.m4.mcch.avafintest.remote.Response

/**
 * Repository interface for retrieving dynamic form data.
 */
interface DynamicFormRepository {

    /**
     * Retrieves dynamic form data.
     */
    suspend fun getDynamicForm(): Response<List<FormFieldDetail>>

}