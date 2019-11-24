package com.awkris.test.gitter.di.module

import com.awkris.test.gitter.data.datastore.SearchDataStore
import com.awkris.test.gitter.di.scope.ActivityScope
import com.awkris.test.gitter.view.searchuser.SearchUserFragment
import com.awkris.test.gitter.view.searchuser.SearchUserPresenter
import dagger.Module
import dagger.Provides

@Module
class SearchUserModule(private val searchUserFragment: SearchUserFragment) {

    @Provides
    @ActivityScope
    internal fun provideSearchUserFragment(): SearchUserFragment {
        return searchUserFragment
    }

    @Provides
    @ActivityScope
    internal fun provideSearchUserPresenter(dataStore: SearchDataStore): SearchUserPresenter {
        return SearchUserPresenter(dataStore)
    }
}