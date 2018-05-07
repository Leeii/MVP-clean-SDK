package com.leeiidesu.lib.base.http;

/**
 * Created by dgg on 2017/11/7.
 */

enum MODE {
    DEFAULT(1),//此情况定义为静默状态
    REPLACE(2),//显示加载动画
    TOAST(3),
    ALERT(4),//弹出错误dialog
    LOGGER(5);//有日志输出

    private int mode;

    MODE(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}