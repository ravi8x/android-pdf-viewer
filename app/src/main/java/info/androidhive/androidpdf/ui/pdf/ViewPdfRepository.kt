package info.androidhive.androidpdf.ui.pdf

import info.androidhive.androidpdf.remote.ApiService
import okhttp3.ResponseBody
import retrofit2.Response

class ViewPdfRepository(private val api: ApiService) {
    fun getFile(url: String?): Response<ResponseBody>? {
        return try {
            api.getFile(url).execute()
        } catch (e: Exception) {
            null
        }
    }
}