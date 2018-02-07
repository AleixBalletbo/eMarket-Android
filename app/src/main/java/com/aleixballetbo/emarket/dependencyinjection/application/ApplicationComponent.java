package com.aleixballetbo.emarket.dependencyinjection.application;

import com.aleixballetbo.emarket.App;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityComponent;
import com.aleixballetbo.emarket.dependencyinjection.activity.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App application);

    ActivityComponent plus(ActivityModule activityModule, ViewModule viewModule);

}
