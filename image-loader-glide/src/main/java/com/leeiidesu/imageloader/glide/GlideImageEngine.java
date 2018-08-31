package com.leeiidesu.imageloader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.leeiidesu.lib.base.imageloader.ImageConfiguration;
import com.leeiidesu.lib.base.imageloader.ImageEngine;

import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 图片加载Glide实现类
 * Created by liyi on 2018/4/18.
 */

public class GlideImageEngine implements ImageEngine {
    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context)
                .clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context)
                .clearDiskCache();
    }

    @Override
    public void pauseRequests(Context context) {
        Glide.with(context)
                .pauseRequests();
    }

    @Override
    public void resumeRequests(Context context) {
        Glide.with(context)
                .resumeRequests();
    }

    @Override
    public void display(Object o, ImageView imageView, ImageConfiguration imageConfiguration) {
        RequestBuilder<Drawable> load = Glide.with(imageView.getContext()).load(o);

        RequestOptions requestOptions = obtainOption(imageView.getContext(), imageConfiguration);
        if (requestOptions != null) {

            load = load.apply(requestOptions);
        }
        load.into(imageView);
    }

    @Override
    public Observable<Drawable> loadImage(Object path, Context context, ImageConfiguration config) {

        RequestBuilder<Drawable> load = Glide.with(context).load(path);

        RequestOptions obtainOption = obtainOption(context, config);
        if (obtainOption != null) {
            load = load.apply(obtainOption);
        }
        return Observable.just(load)
                .map(new Function<RequestBuilder<Drawable>, Drawable>() {
                    @Override
                    public Drawable apply(RequestBuilder<Drawable> drawableRequestBuilder) throws Exception {
                        return drawableRequestBuilder.submit().get();
                    }
                });
    }

    @Override
    public Bitmap getBitMap(Object path, Context context, ImageConfiguration config) {
        RequestBuilder<Bitmap> load = Glide.with(context)
                .asBitmap().load(path);
        RequestOptions obtainOption = obtainOption(context, config);
        if (obtainOption != null) {
            load = load.apply(obtainOption);
        }
        try {
            return load.submit().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Drawable getDrawable(Object path, Context context, ImageConfiguration config) {
        RequestBuilder<Drawable> load = Glide.with(context)
                .load(path);
        RequestOptions obtainOption = obtainOption(context, config);
        if (obtainOption != null) {
            load = load.apply(obtainOption);
        }
        try {
            return load.submit().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }


    private RequestOptions obtainOption(Context context, ImageConfiguration config) {
        if (config == null) return null;

        RequestOptions options = new RequestOptions();

        //设置占位图
        Drawable placeholderDrawable = config.getPlaceholderDrawable(context.getResources());
        if (placeholderDrawable != null) {
            options = options.placeholder(placeholderDrawable);
        }

        //设置错误占位图
        Drawable errorDrawable = config.getErrorHolderDrawable(context.getResources());
        if (errorDrawable != null) {
            options = options.error(errorDrawable);
        }

        if (config.isCenterCrop()) {
            //设置取中心图
            options = options.centerCrop();
        }

        if (config.isCircleCrop()) {
            //圆图
            options = options.transform(new CircleCropTransformation(config.getStrokeWidth(), config.getStrokeColor()));
        } else if (config.hasRound() || config.hasStroke()) {
            //圆角
            options = options.transform(
                    new RoundedTransformation(config.getRound(),
                            config.getStrokeWidth(),
                            config.getStrokeColor()));
        }

        return options;
    }
}
