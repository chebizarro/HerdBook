package com.herdbook.domain.usecase;

import com.herdbook.domain.model.Herd;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetHerdsWithAnimalsUseCase {

    private GetAnimalsUseCase getAnimalsUseCase;

    public GetHerdsWithAnimalsUseCase(GetAnimalsUseCase getAnimalsUseCase) {
        this.getAnimalsUseCase = getAnimalsUseCase;
    }

    public Flowable<List<Herd>> getHerdWithAnimals() {
        return Flowable.never();
    }
}
