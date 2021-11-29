package com.herdbook.data.source;

import com.herdbook.data.FakeHerdRemoteDataSource;
import com.herdbook.data.source.local.HerdLocalDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HerdRepositoryModule {

    @Singleton
    @Binds
    @Local
    abstract HerdDataSource provideHerdLocalDataSource(HerdLocalDataSource dataSource);

    @Singleton
    @Binds
    @Remote
    abstract HerdDataSource provideHerdRemoteDataSource(FakeHerdRemoteDataSource dataSource);
}
