package pa1pal.flickrimages.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import pa1pal.flickrimages.model.Photo
import pa1pal.flickrimages.api.ApiService

class DataSourceFactory(
    private val apiService: ApiService,
    private val query: String
) : DataSource.Factory<Int, Photo>() {
    private val liveData = MutableLiveData<pa1pal.flickrimages.repo.DataSource>()

    override fun create(): DataSource<Int, Photo> {
        val source = DataSource(apiService, query)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 15

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(2 * PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}