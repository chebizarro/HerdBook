package com.herdbook.util.schedulers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SchedulerModule {

     @Singleton
     @Provides
     static BaseSchedulerProvider provideSchedulerProvider() {
          return new ImmediateSchedulerProvider();
     }

}
