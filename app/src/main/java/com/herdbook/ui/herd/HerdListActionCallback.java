package com.herdbook.ui.herd;

import com.herdbook.data.source.local.model.DBAnimal;

public interface HerdListActionCallback {
    void onAnimalSelected(DBAnimal animal);
}
