package com.dimedriller.service.serviceusers.json

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PictureElementTest {
    @Test
    fun testNoFields() {
        val element = PictureElement(null, null, null)
        val model = element.toModel()
        assertThat(model.large).isEmpty()
        assertThat(model.medium).isEmpty()
        assertThat(model.thumbnail).isEmpty()
    }
}