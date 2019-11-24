package com.awkris.test.gitter.di.module

import com.awkris.test.gitter.data.api.SearchApi
import com.awkris.test.gitter.data.datastore.SearchDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule {
    @Singleton
    @Provides
    internal fun provideSearchDataStore(searchApi: SearchApi): SearchDataStore {
        return SearchDataStore(searchApi)
    }
}