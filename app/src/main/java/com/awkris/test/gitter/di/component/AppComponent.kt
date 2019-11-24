package com.awkris.test.gitter.di.component

import com.awkris.test.gitter.di.module.ApiModule
import com.awkris.test.gitter.di.module.AppModule
import com.awkris.test.gitter.di.module.SearchUserModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun plus(searchUserModule: SearchUserModule): SearchUserComponent
}