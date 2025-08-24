package com.m4.mcch.avafintest.dynamicform.data.remote.dto

import com.m4.mcch.avafintest.dynamicform.domain.models.FormFieldDetail
import com.m4.mcch.avafintest.dynamicform.domain.models.FieldType
import com.m4.mcch.avafintest.utils.kotlinextensions.EMPTY

/**
 * Entity that represents the response from the dynamic form API.
 *
 * @param ok[Int] The status of the response.
 * @param data[Map<String, FormFieldDetailDto>] A map of form field details.
 *
 */
data class DynamicFormDTO(
    val ok: Int,
    val data: Map<String, FormFieldDetailDto>
)

/**
 * Entity that represents the form field details.
 */
data class FormFieldDetailDto(
    val req: Boolean,
    val group: String,
    val label: String,
    val visible: Boolean,
    val order: Int,
    val maxlength: Int,
    val type: String,
    val newline: Boolean,
    val hidetitle: Boolean,
    val split: Boolean,
    val mapper: String?,
    val clientzoneVisible: Boolean,
    val clientzoneEditable: Boolean,
    val clientzoneCheck: String?,
    val clientzoneRequired: Boolean,
    val clVisible: Boolean,
    val step: Int,
    val autoApprove: Boolean,
    val conditionType: Int,
    val condition: List<Any>,
    val regex: String? = null,
    val values: Any? = null
) {

    /**
     * Converts the DTO to a domain model.
     */
    fun toDomain(id: String): FormFieldDetail {
        return FormFieldDetail(
            id = id,
            label = label,
            order = order,
            type = FieldType.from(type),
            maxLength = maxlength,
            regex = regex ?: EMPTY,
            isVisible = visible,
            options = if (values is List<*>) {
                values.filterIsInstance<String>()
            } else {
                listOf()
            }
        )
    }

}