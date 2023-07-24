package narzullaev.bekzod.kattabazar.api

import narzullaev.bekzod.kattabazar.models.GetOffersResDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("hh/test/api/v1/offers")
    suspend fun getOffers(): Response<GetOffersResDto>
}