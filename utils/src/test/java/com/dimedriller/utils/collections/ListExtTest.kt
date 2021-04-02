package com.dimedriller.utils.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.lang.IllegalArgumentException

class ListExtTest {
    @Test
    fun testMapIgnoreErr() {
        val source = listOf(1, 2, 3, 4, 5)

        val onlyOdd = source.mapIgnoreErr { if (it % 2 == 0) throw IllegalArgumentException() else it }
        assertThat(onlyOdd).hasSize(3)
        assertThat(onlyOdd[0]).isEqualTo(1)
        assertThat(onlyOdd[1]).isEqualTo(3)
        assertThat(onlyOdd[2]).isEqualTo(5)

        val noElements = source.mapIgnoreErr { throw IllegalArgumentException() }
        assertThat(noElements).hasSize(0)

        val allElements = source.mapIgnoreErr { it }
        assertThat(allElements).hasSize(5)
        allElements.forEachIndexed { index, element -> assertThat(element).isEqualTo(index + 1) }
    }
}