package com.journey.myjourney.other.model

import com.journey.myjourney.other.type.sendOneParametreBlock


/**
 * Saidmurod Turdiyev writes this for All Project (SMT)
 */

open class Event<T>(private val content: T, val text: String = "", val block: sendOneParametreBlock<T>? = null) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
