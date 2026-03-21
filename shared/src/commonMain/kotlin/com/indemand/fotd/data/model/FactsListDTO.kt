package com.indemand.fotd.data.model

import com.indemand.fotd.core.CommonResponse
import kotlinx.serialization.Serializable

@Serializable
data class FactsListDTO(
    override val status: Int?, override val message: String?, val featured: List<FactDetailsDTO>?,
) : CommonResponse