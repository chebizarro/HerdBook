package com.herdbook.ui.di;

import android.app.Application;

import com.herdbook.domain.repository.HerdRepository;
import com.herdbook.data.source.HerdRepositoryModule;
import com.herdbook.ui.HerdBookApplication;
import com.herdbook.util.schedulers.SchedulerModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import javax.inject.Singleton;

/**
 * This is a Dagger component. Refer to {@link HerdBookApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * HerdBookApplication}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = {
        SchedulerModule.class,
        HerdRepositoryModule.class,
        ApplicationModule.class,
        ViewModelModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<HerdBookApplication> {

    HerdRepository getHerdRepository();

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
