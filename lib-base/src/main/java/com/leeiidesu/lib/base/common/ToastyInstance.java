package com.leeiidesu.lib.base.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liyi on 2018/1/15.
 */

public class ToastyInstance {

    private Toast toasty;

    public static ToastyInstance getInstance() {
        return Holder.INSTANCE;
    }

    public void showToast(Context context, CharSequence message) {
        if (toasty != null)
            toasty.cancel();
        toasty = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toasty.show();
    }


    private static class Holder {
        private static ToastyInstance INSTANCE = new ToastyInstance();
    }
}
