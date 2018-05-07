package com.leeiidesu.lib.base.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;


import io.reactivex.Observable;

public class ImageLoader {
    private ImageConfiguration defaultConfiguration;
    private ImageEngine mImageEngine;

    private ImageLoader() {
        defaultConfiguration = new ImageConfiguration.Builder().build();
    }

    public static ImageLoader getInstance() {
        return Holder.INSTANCE;
    }

    public final void init(@NonNull ImageEngine engine, @NonNull ImageConfiguration defaultConfiguration) {
        this.mImageEngine = engine;
        this.defaultConfiguration = defaultConfiguration;
    }

    public void display(Object path,
                        @NonNull ImageView target) {
        display(path, target, defaultConfiguration);
    }

    public final void display(@NonNull Object path, @NonNull ImageView target, @Nullable ImageConfiguration config) {
        assertImageEngineNotNull();
        mImageEngine.display(path, target, config);
    }

    private void assertImageEngineNotNull() {
        if (mImageEngine == null) {
            throw new IllegalStateException("this ImageLoader must be init. ex: ImageLoader.getInstance().init(ImageEngine,ImageConfiguration)");
        }
    }

    public final Observable loadImage(@NonNull Object path, @NonNull Context context) {
        return loadImage(path, context, defaultConfiguration);
    }

    public final Observable loadImage(@NonNull Object path, @NonNull Context context, @Nullable ImageConfiguration config) {
        assertImageEngineNotNull();
        return mImageEngine.loadImage(path, context, config);
    }


    public final void clearMemoryCache(@NonNull Context context) {
        assertImageEngineNotNull();
        mImageEngine.clearMemoryCache(context);
    }

    public final void clearDiskCache(@NonNull Context context) {
        assertImageEngineNotNull();
        mImageEngine.clearDiskCache(context);
    }

    public void pauseRequests(Context context) {
        assertImageEngineNotNull();
        mImageEngine.pauseRequests(context);

    }

    public void resumeRequests(Context context) {
        assertImageEngineNotNull();
        mImageEngine.resumeRequests(context);
    }


    private static final class Holder {
        private static ImageLoader INSTANCE = new ImageLoader();
    }
}
