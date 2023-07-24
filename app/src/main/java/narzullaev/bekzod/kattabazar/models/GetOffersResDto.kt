package narzullaev.bekzod.kattabazar.models


data class GetOffersResDto(
    val offers: List<Offer>
) {
    data class Offer(
        val attributes: List<Attribute>,
        val brand: String,
        val category: String,
        val id: Int,
        val image: Image,
        val merchant: String,
        val name: String
    )

    data class Attribute(
        val name: String,
        val value: String
    )

    data class Image(
        val height: Int,
        val url: String,
        val width: Int
    )
}