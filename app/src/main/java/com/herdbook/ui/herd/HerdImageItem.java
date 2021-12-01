package com.herdbook.ui.herd;

import android.graphics.Bitmap;

public class HerdImageItem {
    private Bitmap image;
    private String title;

    public HerdImageItem(Bitmap image, String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
