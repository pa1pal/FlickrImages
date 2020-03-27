package pa1pal.flickrimages.model

data class Photo(
    val farm: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String
) {
    fun getUrl(): String {
        return "https://farm$farm.staticflickr.com/$server/$id"+"_"+"$secret"+"_"+"m.jpg"
    }
}