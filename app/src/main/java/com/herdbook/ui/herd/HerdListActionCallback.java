package com.herdbook.ui.herd;

import com.herdbook.data.source.local.model.Animal;

public interface HerdListActionCallback {
    void onAnimalSelected(Animal animal);
}
