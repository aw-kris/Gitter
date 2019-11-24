package com.awkris.test.gitter;

import android.app.Application;

import com.awkris.test.gitter.di.component.AppComponent;
import com.awkris.test.gitter.di.component.DaggerAppComponent;
import com.awkris.test.gitter.di.module.ApiModule;
import com.awkris.test.gitter.di.module.AppModule;

public class Gitter extends Application {
    private AppComponent appComponent = createAppComponent();

    private static Gitter instance;

    public static Gitter get() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return  appComponent;
    }
}
