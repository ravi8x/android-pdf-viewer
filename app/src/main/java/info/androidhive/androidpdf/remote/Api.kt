package info.androidhive.androidpdf.remote

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import info.androidhive.androidpdf.util.NullOnEmptyConverterFactory
import info.androidhive.androidpdf.util.SingletonHolder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api(private val context: Context) {

    val apiService: ApiService by lazy {
        retrofit().create(ApiService::class.java)
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okhttpClient()).addConverterFactory(NullOnEmptyConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl("https://mydomain.com")
            .build()
    }

    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request().newBuilder()
                    .build()

                chain.proceed(request)
            }).build()
    }

    companion object : SingletonHolder<Api, Context>(::Api)
}