package com.m4.mcch.avafintest.dynamicform.domain.models

import com.m4.mcch.avafintest.utils.kotlinextensions.EMPTY

/**
 * Represents an input field in the dynamic form.
 *
 * @param input[String] The value of the input field.
 * @param detail[FormFieldDetail] The details and validations of the field.
 *
 */
data class FormInputField(
    var input: String = EMPTY,
    val detail: FormFieldDetail
) {

    /**
     * Validates the given value against the field's constraints.
     *
     */
    fun isValid(): Boolean {
        return detail.isValid(input)
    }

}
