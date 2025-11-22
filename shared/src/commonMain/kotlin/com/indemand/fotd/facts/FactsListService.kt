package com.indemand.fotd.facts

import com.indemand.fotd.data.model.FactDetailsDTO
import io.ktor.client.HttpClient

class FactsListService(private val httpClient: HttpClient) {

    suspend fun getListOfFacts(): List<FactDetailsDTO>? {
        /*val response: FactsListResponse =
            httpClient.get("https://raw.githubusercontent.com/kansalmohit19/JSONResponses/refs/heads/main/fotd/getFactsList.txt")
                .body()
        return response.data?.facts*/
        return null
    }
}