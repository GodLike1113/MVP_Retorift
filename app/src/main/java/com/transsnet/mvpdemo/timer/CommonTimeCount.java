package com.transsnet.mvpdemo.timer;

import android.os.CountDownTimer;

/**
 * Author:  zengfeng
 * Time  :  2019/10/8 17:44
 * Des   :  封装一个通用的计时器
 */
public class CommonTimeCount extends CountDownTimer {
    private boolean isFinish = false;
    private OnTimeCountListener countListener;

    public CommonTimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        isFinish = false;
        if (countListener != null) {
            countListener.onTick(millisUntilFinished);
        }
    }

    @Override
    public void onFinish() {
        isFinish = true;
        if (countListener != null) {
            countListener.onFinish();
        }
    }

    public void setOnCountListener(OnTimeCountListener countListener) {
        this.countListener = countListener;
    }

    public void startCount() {
        isFinish = false;
        start();
    }

    public void stopCount() {
        isFinish = true;
        cancel();
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public interface OnTimeCountListener {
        void onTick(long millisUntilFinished);

        void onFinish();
    }
}
