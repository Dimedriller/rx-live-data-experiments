package com.dimedriller.utils.component

interface ComponentProvider {
    fun <T> get(componentClass: Class<T>): T
}