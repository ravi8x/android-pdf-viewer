package info.androidhive.androidpdf.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getFile(@Url url: String?): Call<ResponseBody>
}