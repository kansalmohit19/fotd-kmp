package com.indemand.fotd.facts

import com.indemand.fotd.data.model.FactDetailsDTO
import kotlinx.serialization.Serializable

@Serializable
data class FactsListResponse(val count: Int, val facts: List<FactDetailsDTO>)