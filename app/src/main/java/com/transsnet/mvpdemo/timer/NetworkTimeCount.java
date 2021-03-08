package com.transsnet.mvpdemo.timer;

/**
 * Created by cks on 18-8-6.
 */

public class NetworkTimeCount extends CustomCountDownTimer {

    private TimeCountFinishListener mTimeCountFinishListener;

    public NetworkTimeCount(int millisInFuture, TimeCountFinishListener timeCountFinishListener) {
        super(millisInFuture, 1000);
        this.mTimeCountFinishListener = timeCountFinishListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        mTimeCountFinishListener.onFinish();
    }

    public interface TimeCountFinishListener{
        void onFinish();
    }
}
