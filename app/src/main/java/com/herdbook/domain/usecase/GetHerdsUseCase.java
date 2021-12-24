package com.herdbook.domain.usecase;

import com.herdbook.domain.model.Herd;
import com.herdbook.domain.repository.HerdRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class GetHerdsUseCase {

    private GetAnimalsUseCase getAnimalsUseCase;

    private HerdRepository mHerdRepository;

    public GetHerdsUseCase(GetAnimalsUseCase getAnimalsUseCase, HerdRepository mHerdRepository) {
        this.getAnimalsUseCase = getAnimalsUseCase;
        this.mHerdRepository = mHerdRepository;
    }

    public Flowable<List<Herd>> getHerdWithAnimals() {
        return mHerdRepository.getHerds();
    }
}
