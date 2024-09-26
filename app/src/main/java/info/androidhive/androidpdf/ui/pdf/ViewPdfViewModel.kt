package info.androidhive.androidpdf.ui.pdf

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import info.androidhive.androidpdf.remote.Api
import info.androidhive.androidpdf.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class ViewPdfViewModel(application: Application) : AndroidViewModel(application) {
    private val apiService: ApiService = Api.getInstance(application).apiService
    private val repository: ViewPdfRepository = ViewPdfRepository(apiService)
    private val _fileStream: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    val fileStream: LiveData<Response<ResponseBody>?>
        get() = _fileStream

    fun getFile(url: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _fileStream.postValue(repository.getFile(url))
        }
    }
}