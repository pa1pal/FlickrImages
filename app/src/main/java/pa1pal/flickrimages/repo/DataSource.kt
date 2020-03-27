package pa1pal.flickrimages.repo

import androidx.paging.PageKeyedDataSource
import pa1pal.flickrimages.model.Images
import pa1pal.flickrimages.model.Photo
import pa1pal.flickrimages.api.ApiService
import retrofit2.Call
import retrofit2.Response

class DataSource constructor(
    private val apiService: ApiService,
    private val query: String
) : PageKeyedDataSource<Int, Photo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        fetchData(query, 1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        fetchData(query, page = page + 1) {
            callback.onResult(it, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        TODO("Not yet implemented")
    }

    private fun fetchData(
        text: String,
        page: Int,
        callback: (List<Photo>) -> Unit
    ) {
        apiService.searchImages(text = text, page = page)
            .enqueue(object : retrofit2.Callback<Images> {
                override fun onFailure(call: Call<Images>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<Images>, response: Response<Images>) {
                    response.body()?.let { callback.invoke(it.photos.photo) }
                }
            })
    }
}