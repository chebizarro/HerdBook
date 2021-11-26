package com.herdbook.util.schedulers;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Implementation of the {@link BaseSchedulerProvider} making all {@link Scheduler}s execute
 * synchronously so we can easily run assertions in our tests.
 * <p>
 * To achieve this, we are using the {@link io.reactivex.rxjava3.schedulers.Schedulers} from the {@link Schedulers} class.
 */
public class ImmediateSchedulerProvider implements BaseSchedulerProvider {

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
