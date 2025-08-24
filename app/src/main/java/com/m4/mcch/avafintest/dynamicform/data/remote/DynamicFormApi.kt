package com.m4.mcch.avafintest.dynamicform.data.remote

import com.m4.mcch.avafintest.dynamicform.data.remote.dto.DynamicFormDTO
import retrofit2.http.GET

/**
 * Interface that represents the API for retrieving dynamic form data.
 */
interface DynamicFormApi {

    /**
     * Retrieves dynamic form data from the API (for this test is a JSON file in Google Drive).
     */
    @GET("uc?export=download&id=1Jr8qnX4T5wi1vhTBtFNh65TPL51uFr8d")
    suspend fun getDynamicForm(): DynamicFormDTO

}