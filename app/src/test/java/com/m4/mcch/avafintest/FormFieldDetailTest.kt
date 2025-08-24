package com.m4.mcch.avafintest

import com.m4.mcch.avafintest.dynamicform.data.remote.dto.DynamicFormDTO
import com.m4.mcch.avafintest.dynamicform.domain.models.FormInputField
import com.m4.mcch.avafintest.utils.kotlinextensions.fromJson
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class FormFieldDetailTest {

    private lateinit var formInputFields: List<FormInputField>

    @Before
    fun setup() {
        val json = """
        {
              "ok": 1,
              "data": {
                "customer-lastname": {
                  "label": "Last name",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 2,
                  "maxlength": 150,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[a-zA-ZĀČĒĢĪĶĻŅŠŪŽāčēģīķļšūžņ\\-\\ ]*${'$'}"
                },
                "customer-phone": {
                  "label": "Phone",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 3,
                  "maxlength": 50,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": true,
                  "clientzone_check": "sms",
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": true,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^2[0-9]{7}${'$'}"
                },
                "customer-monthly-income": {
                  "label": "Monthly income",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 6,
                  "maxlength": 10,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": true,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[0-9]+${'$'}"
                },
                "bank-iban": {
                  "label": "Bank Iban",
                  "req": false,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": 31,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": []
                },
                "language": {
                  "label": "Language",
                  "req": true,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": null,
                  "type": "select",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": false,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "values": {
                    "lv": "latvian",
                    "ru": "russian"
                  }
                },
                "customer-personcode": {
                  "label": "Person code",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 5,
                  "maxlength": 50,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[0-9]{6}-[0-9]{5}${'$'}"
                },
                "customer-email": {
                  "label": "Email",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 4,
                  "maxlength": 50,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": true,
                  "clientzone_check": "email",
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": true,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^(?:[A-z0-9_%+-]+\\.)*[A-z0-9_%+-]+@[A-z0-9][A-z0-9-]*(?:\\.[A-z]+)+${'$'}"
                },
                "customer-firstname": {
                  "label": "First name",
                  "req": true,
                  "group": "",
                  "visible": true,
                  "order": 1,
                  "maxlength": 50,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": true,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[a-zA-ZĀČĒĢĪĶĻŅŠŪŽāčēģīķļšūžņ\\-\\ ]*${'$'}"
                },
                "customer-gender": {
                  "label": "Gender",
                  "req": true,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": null,
                  "type": "select",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": false,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "values": [
                    "Male",
                    "Female",
                    "Unknown"
                  ]
                },
                "customer-birthday": {
                  "label": "Birthday",
                  "req": true,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": 10,
                  "type": "text",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": false,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": false,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": []
                },
                "pep-status": {
                  "label": "",
                  "req": false,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": null,
                  "type": "checkbox",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": false,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": true,
                  "cl_visible": false,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[10]${'$'}"
                },
                "aml-check": {
                  "label": "",
                  "req": false,
                  "group": "",
                  "visible": false,
                  "order": 1000,
                  "maxlength": null,
                  "type": "checkbox",
                  "newline": false,
                  "hidetitle": false,
                  "split": false,
                  "mapper": "Itdata\\Laravel\\CountrySpecific\\LatviaMapper",
                  "clientzone_visible": false,
                  "clientzone_editable": false,
                  "clientzone_check": null,
                  "clientzone_required": true,
                  "cl_visible": true,
                  "step": 0,
                  "auto_approve": false,
                  "condition_type": 0,
                  "condition": [],
                  "regex": "^[10]${'$'}"
                }
              }
            }
        """.trimIndent()
        val dynamicForm = json.fromJson<DynamicFormDTO>().data.entries.map { it.value.toDomain(it.key) }
        formInputFields = dynamicForm.map { FormInputField(detail = it) }
    }

    @Test
    fun `last name should accept valid characters`() {
        val lastNameField = formInputFields.find { it.detail.id == "customer-lastname" }
        lastNameField?.let {
            it.input = "Čēģī-Kļņ Šūž"
            assertTrue(lastNameField.isValid())
        }
    }

    @Test
    fun `last name should not accept numbers`() {
        val lastNameField = formInputFields.find { it.detail.id == "customer-lastname" }
        lastNameField?.let {
            it.input = "384394"
            assertFalse(lastNameField.isValid())
        }
    }

    @Test
    fun `phone should accept only 8 digits starting with 2`() {
        val phoneField = formInputFields.find { it.detail.id == "customer-phone" }
        phoneField?.let {
            it.input = "21234567"
            assertTrue(phoneField.isValid())
        }
    }

    @Test
    fun `monthly income should accept only numbers`() {
        val monthlyIncomeField = formInputFields.find { it.detail.id == "customer-monthly-income" }
        monthlyIncomeField?.let {
            it.input = "30000"
            assertTrue(monthlyIncomeField.isValid())
        }
    }
}