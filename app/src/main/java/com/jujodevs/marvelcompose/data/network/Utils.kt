package com.jujodevs.marvelcompose.data.network

import retrofit2.Retrofit

inline fun <reified T> Retrofit.create(): T = this.create(T::class.java)
