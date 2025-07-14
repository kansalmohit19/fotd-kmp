package com.indemand.fotd.facts

import kotlinx.serialization.Serializable

@Serializable
data class FactsListResponse(val count: Int, val facts: List<FactDetailsDTO>)