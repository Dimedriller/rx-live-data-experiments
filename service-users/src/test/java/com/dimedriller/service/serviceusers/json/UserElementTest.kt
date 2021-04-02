package com.dimedriller.service.serviceusers.json

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UserElementTest {
    @Test(expected = IllegalStateException::class)
    fun testNoId() {
        val element = UserElement("a@a.a",
                null,
                NameElement("a", "b", "c"),
                PictureElement(null, null, null))
        element.toModel()
    }

    @Test(expected = IllegalStateException::class)
    fun testNoName() {
        val element = UserElement("a@a.a",
                IdElement("id", "1"),
                null,
                PictureElement(null, null, null))
        element.toModel()
    }

    @Test(expected = IllegalStateException::class)
    fun testNoEmail() {
        val element = UserElement(null,
                IdElement("id", "1"),
                NameElement("a", "b", "c"),
                PictureElement(null, null, null))
        element.toModel()
    }

    @Test
    fun testNoPicture() {
        val element = UserElement("a@a.a",
                IdElement("id", "1"),
                NameElement("a", "b", "c"),
                null)
        val model = element.toModel()
        val picture = model.picture
        assertThat(picture.large).isEmpty()
        assertThat(picture.medium).isEmpty()
        assertThat(picture.thumbnail).isEmpty()
    }
}