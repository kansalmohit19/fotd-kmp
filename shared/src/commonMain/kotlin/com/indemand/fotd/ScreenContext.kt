package com.indemand.fotd

interface ScreenContext {
    val screenName: String
    val parentContext: ScreenContext?
}

data class DefaultScreenContext(
    override val screenName: String,
    override val parentContext: ScreenContext?,
) : ScreenContext
