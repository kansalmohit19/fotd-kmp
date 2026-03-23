package com.indemand.fotd.data.extensions

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun currentMillis(): Long = Clock.System.now().toEpochMilliseconds()
