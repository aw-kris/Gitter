package com.awkris.test.gitter.di.component

import com.awkris.test.gitter.di.module.SearchUserModule
import com.awkris.test.gitter.di.scope.ActivityScope
import com.awkris.test.gitter.view.searchuser.SearchUserFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [SearchUserModule::class])
interface SearchUserComponent {
    fun inject(searchUserFragment: SearchUserFragment): SearchUserFragment
}