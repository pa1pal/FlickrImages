package pa1pal.flickrimages.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import pa1pal.flickrimages.model.Photo
import pa1pal.flickrimages.api.ApiService

class Repo {
    private val searchRemoteDataSource: ApiService = ApiService.instance
    fun searchPhoto(query: String): LiveData<PagedList<Photo>> {
        val dataSourceFactory = DataSourceFactory(searchRemoteDataSource, query)

        return LivePagedListBuilder(dataSourceFactory,
                DataSourceFactory.pagedListConfig())
                .build()
    }
}