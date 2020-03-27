package pa1pal.flickrimages.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import pa1pal.flickrimages.model.Photo
import pa1pal.flickrimages.repo.Repo

class MainViewModel : ViewModel() {
    val repo: Repo =
        Repo()
    var query: MutableLiveData<String> = MutableLiveData<String>().apply { value = "sky" }

    fun setQuery(queryString: String) {
        query.postValue(queryString)
    }

    val data: LiveData<PagedList<Photo>>
        get() = Transformations.switchMap(query) {
            repo.searchPhoto(it)
        }
}