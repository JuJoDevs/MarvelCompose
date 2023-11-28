package com.jujodevs.marvelcompose.data.network.remote

import com.jujodevs.marvelcompose.PrivateKey
import com.jujodevs.marvelcompose.PublicKey
import com.jujodevs.marvelcompose.data.generateHash
import java.util.Date
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor @Inject constructor(
    @PrivateKey private val privateKey: String,
    @PublicKey private val publicKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = Date().time
        val hash = generateHash(ts, privateKey, publicKey)

        val url = originalUrl.newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
