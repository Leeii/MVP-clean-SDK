package com.leeiidesu.lib.base.imageloader;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * 图片加载属性
 * Created by liyi on 2018/4/18.
 */

public class ImageConfiguration {
    // 边框宽度
    private int mStrokeWidth;
    // 边框颜色
    private int mStrokeColor;

    // 圆角
    private int mRound;
    // 圆图
    private boolean circleCrop;

    // 占位图
    private int placeholderRes;
    // 错误占位图
    private int errorHolderRes;
    // 占位图
    private Drawable placeholderDrawable;
    // 错误占位图
    private Drawable errorHolderDrawable;

    public ImageConfiguration(Builder builder) {
        this.mStrokeColor = builder.mStrokeColor;
        this.mStrokeWidth = builder.mStrokeWidth;
        this.mRound = builder.mRound;
        this.circleCrop = builder.circleCrop;
        this.placeholderRes = builder.placeholderRes;
        this.errorHolderRes = builder.errorHolderRes;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.errorHolderDrawable = builder.errorHolderDrawable;
    }

    public static ImageConfiguration createSimple() {
        return new Builder().build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Drawable getPlaceholderDrawable(Resources resources) {
        if (placeholderRes != 0) return getDrawable(resources, placeholderRes);
        else return placeholderDrawable;
    }

    private Drawable getDrawable(Resources resources, int drawableRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return resources.getDrawable(drawableRes, null);
        } else {
            return resources.getDrawable(drawableRes);
        }
    }

    public Drawable getErrorHolderDrawable(Resources resources) {
        if (errorHolderRes != 0) return getDrawable(resources, errorHolderRes);
        else return errorHolderDrawable;
    }

    public boolean isCircleCrop() {
        return circleCrop;
    }

    public int getRound() {
        return mRound;
    }

    public boolean hasRound() {
        return mRound > 0;
    }

    public boolean hasStroke() {
        return mStrokeWidth > 0;
    }

    @ColorInt
    public int getStrokeColor() {
        return mStrokeColor;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }


    public static class Builder {
        // 边框宽度
        int mStrokeWidth = 0;
        // 边框颜色
        int mStrokeColor = 0;

        // 圆角
        int mRound = 0;
        // 圆图
        boolean circleCrop = false;

        // 占位图
        int placeholderRes = 0;
        // 错误占位图
        int errorHolderRes = 0;
        // 占位图
        Drawable placeholderDrawable = null;
        // 错误占位图
        Drawable errorHolderDrawable = null;

        public Builder setStrokeWidth(int mStrokeWidth) {
            this.mStrokeWidth = mStrokeWidth;
            return this;
        }

        public Builder setStrokeColor(int mStrokeColor) {
            this.mStrokeColor = mStrokeColor;
            return this;
        }

        public Builder round(int mRound) {
            this.mRound = mRound;
            return this;
        }

        public Builder circleCrop() {
            this.circleCrop = true;
            return this;
        }

        public Builder placeholder(@DrawableRes int placeholderRes) {
            this.placeholderRes = placeholderRes;
            return this;
        }

        public Builder errorholder(@DrawableRes int errorHolderRes) {
            this.errorHolderRes = errorHolderRes;
            return this;
        }

        public Builder placeholder(Drawable placeholderDrawable) {
            this.placeholderDrawable = placeholderDrawable;
            return this;
        }

        public Builder errorholder(Drawable errorHolderDrawable) {
            this.errorHolderDrawable = errorHolderDrawable;
            return this;
        }

        public Builder stroke(int width, @ColorInt int color) {
            setStrokeWidth(width);
            setStrokeColor(color);
            return this;
        }

        public ImageConfiguration build() {
            return new ImageConfiguration(this);
        }
    }
}
