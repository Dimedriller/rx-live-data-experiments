package com.dimedriller.utils.collections

fun <S,D> List<S>.mapIgnoreErr(lambda: (source: S) -> D): List<D> {
    val newCollection = ArrayList<D>()
    for(src in this) {
        try {
            val dest = lambda(src)
            newCollection.add(dest)
        } catch (e: Throwable) {
            // No action
        }
    }

    return newCollection
}