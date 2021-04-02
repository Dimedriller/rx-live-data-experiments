package com.dimedriller.utils.component

import java.lang.IllegalArgumentException

class ComponentRegistry: ComponentProvider {
    private val pool = HashMap<Class<*>, Any>()

    override fun <T> get(componentClass: Class<T>): T {
        val component = pool.get(componentClass)
                ?: throw IllegalArgumentException("Component ${componentClass} has not been registered")
        @Suppress("UNCHECKED_CAST")
        return component as T
    }

    fun <T: Any> register(componentClass: Class<T>, component: T) {
        pool[componentClass] = component
    }
}