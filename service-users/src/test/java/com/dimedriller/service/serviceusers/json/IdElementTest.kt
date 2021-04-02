package com.dimedriller.service.serviceusers.json

import org.junit.Test

class IdElementTest {
    @Test(expected = IllegalStateException::class)
    fun testNoName() {
        val element = IdElement(null, "789")
        element.toModel()
    }

    @Test(expected = IllegalStateException::class)
    fun testNoValue() {
        val element = IdElement("id", null)
        element.toModel()
    }
}