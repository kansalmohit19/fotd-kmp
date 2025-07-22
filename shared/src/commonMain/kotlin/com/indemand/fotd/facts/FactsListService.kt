package com.indemand.fotd.facts

import com.indemand.fotd.core.CommonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FactsListService(private val httpClient: HttpClient) {

    suspend fun getListOfFacts(): List<FactDetailsDTO>? {
        /*val response: FactsListResponse =
            httpClient.get("https://raw.githubusercontent.com/kansalmohit19/JSONResponses/refs/heads/main/fotd/getFactsList.txt")
                .body()
        return response.data?.facts*/
        return null
    }
}