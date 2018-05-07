package com.leeiidesu.lib.base.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import io.reactivex.Observable;

/**
 * 图片加载
 * Created by liyi on 2018/4/26.
 */

public interface ImageEngine {
    /**
     * 清除内存缓存
     */
    void clearMemoryCache(Context context);

    /**
     * 清除磁盘缓存
     */
    void clearDiskCache(Context context);


    /**
     * 暂停加载
     */
    void pauseRequests(Context context);

    /**
     * 恢复加载
     */
    void resumeRequests(Context context);

    /**
     * 显示图片
     *
     * @param path   图片path
     * @param target 目标ImageView
     * @param config 选项
     */
    void display(Object path, ImageView target, ImageConfiguration config);


    /**
     * 加载图片
     *
     * @param path    图片uri
     * @param context 上下文
     * @param config  选项
     */
    Observable<Drawable> loadImage(Object path, Context context, ImageConfiguration config);
}
