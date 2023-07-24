package narzullaev.bekzod.kattabazar.repository

import narzullaev.bekzod.kattabazar.api.ApiService
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getOffers() = apiService.getOffers()
}