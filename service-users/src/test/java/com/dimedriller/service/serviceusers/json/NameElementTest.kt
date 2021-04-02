package com.dimedriller.service.serviceusers.json

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NameElementTest {
    @Test(expected = IllegalStateException::class)
    fun testNoFirstName() {
        val element = NameElement(null, "last", "Mr")
        element.toModel()
    }

    @Test(expected = IllegalStateException::class)
    fun testNoLastName() {
        val element = NameElement("first", null, "Mr")
        element.toModel()
    }

    @Test
    fun testNoTitle() {
        val element = NameElement("first", "last", null)
        val model = element.toModel()
        assertThat(model.title).isEmpty()
    }
}