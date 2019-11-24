package com.awkris.test.gitter.di.module

import com.awkris.test.gitter.data.api.SearchApi
import com.awkris.test.gitter.data.api.utils.ApiGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    internal fun provideSearchUserApi(apiGenerator: ApiGenerator): SearchApi {
        return apiGenerator.createApi(SearchApi::class.java)
    }
}