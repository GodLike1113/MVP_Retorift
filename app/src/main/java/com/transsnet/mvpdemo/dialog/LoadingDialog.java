package com.transsnet.mvpdemo.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;

import com.airbnb.lottie.LottieAnimationView;
import com.transsnet.mvpdemo.R;


/**
 * Created by cks on 18-4-10.
 *
 * 加载过程 Dialog
 */

public class LoadingDialog extends BaseDialog {

    private LottieAnimationView animationView;

    public LoadingDialog(Activity activity) {
        super(activity);
        setStyle(android.R.style.Animation_Dialog);
        setCancelable(false);
    }

    public LoadingDialog(Context context) {
        super(context);
        setStyle(android.R.style.Animation_Dialog);
        setCancelable(false);
    }

    @Override
    public void initUI() {
        animationView = findViewById(R.id.animation_view);
        animationView.setAnimation("animator_loading.json");
        animationView.loop(true);
        animationView.playAnimation();
    }

    @Override
    public void regUIEvent() {

    }

    @Override
    public int getContentView() {
        return R.layout.dialog_loading;
    }

    public void showLoading() {
        show(Gravity.CENTER);
    }

    @Override
    public void dismiss() {
        if(animationView!=null) {
            animationView.destroyDrawingCache();
            animationView.clearAnimation();
            animationView.cancelAnimation();
            animationView.removeAllAnimatorListeners();
            animationView.removeAllLottieOnCompositionLoadedListener();
            animationView.removeAllUpdateListeners();
            animationView = null;
        }
        super.dismiss();
    }
}
