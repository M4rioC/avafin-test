package com.m4.mcch.avafintest.dynamicform.domain.models

/**
 * Entity that represents details and validations of a form field.
 *
 * @param id[String] The unique identifier of the field.
 * @param order[Int] The order of the field in the form.
 * @param label[String] The label of the field.
 * @param maxLength[Int] The maximum length of the field.
 * @param regex[String] The regular expression used to validate the field.
 * @param isVisible[Boolean] Whether the field is visible or not.
 *
 */
data class FormFieldDetail(
    val id: String,
    val order: Int,
    val label: String,
    val type: FieldType,
    val maxLength: Int,
    val regex: String,
    val options: List<String>,
    val isVisible: Boolean
) {

    /**
     * Validates the given value against the field's constraints.
     *
     * @param value[String] The value to be validated.
     *
     */
    fun isValid(value: String): Boolean {
        val isLengthValid = value.length <= maxLength
        val isRegexValid = validateRegex(value)
        return isLengthValid && isRegexValid
    }

    private fun validateRegex(value: String): Boolean {
        return try {
            Regex(regex).matches(value)
        } catch (e: Exception) {
            false
        }
    }

}

/**
 * Enum representing the different types of form fields.
 */
enum class FieldType {
    TEXT, SELECT, CHECKBOX;

    companion object {

        /**
         * Converts a string value to a [FieldType].
         */
        fun from(value: String): FieldType {
            return when (value) {
                "text" -> TEXT
                "select" -> SELECT
                "checkbox" -> CHECKBOX
                else -> throw IllegalArgumentException("Invalid form type: $value")
            }
        }
    }
}
