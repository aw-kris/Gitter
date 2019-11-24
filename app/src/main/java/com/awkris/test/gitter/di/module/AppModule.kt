package com.awkris.test.gitter.di.module

import android.app.Application
import android.content.Context
import com.awkris.test.gitter.data.api.utils.ApiGenerator
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    internal fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideApiGenerator(): ApiGenerator {
        return ApiGenerator(Gson())
    }
}