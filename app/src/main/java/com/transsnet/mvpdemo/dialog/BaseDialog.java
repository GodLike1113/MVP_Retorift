package com.transsnet.mvpdemo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.mars.xlog.Log;
import com.transsnet.mvpdemo.R;
import com.transsnet.mvpdemo.util.ScreenUtil;

public abstract class BaseDialog extends Dialog {
    protected final String TAG = getClass().getSimpleName();
    public Resources res;
    public Context context;
    public Activity parent;
    public LayoutInflater inflater;
    protected Toast toast;
    static final int DEFAULT_ANMI = R.style.DialogSlideAnimation;
    private int anmi = DEFAULT_ANMI;
    private int width, height;
    protected long enterTime;

    public abstract void initUI();

    public abstract void regUIEvent();

    public BaseDialog(Activity parent) {
        super(parent, R.style.my_dialog_style);
        this.parent = parent;
        this.context = parent.getApplicationContext();
        this.width = ScreenUtil.getScreenWidth(getContext());
        this.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public BaseDialog(Context parent) {
        super(parent, R.style.my_dialog_style);
        this.context = parent.getApplicationContext();
        this.width = ScreenUtil.getScreenWidth(getContext());
        this.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public void setStyle(int anmi) {
        this.anmi = anmi;
    }

    public void setStyle(int anmi, int width, int height) {
        this.anmi = anmi;
        this.width = width;
        this.height = height;
    }

    public Intent getIntent(Class<?> c) {
        return new Intent(context, c);
    }

    public void startActivityByClass(Class<?> cls) {
        try {
            context.startActivity(getIntent(cls));
        } catch (ActivityNotFoundException e) {
            // TODO Auto-generated catch block
            printErrorLogs(cls.getName() + " Not Found!");
        }
    }

    public void startActivityByIntent(Intent intent) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // TODO Auto-generated catch block
            printErrorLogs(intent.getClass().getName() + " Not Found!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        res = context.getResources();
        inflater = getLayoutInflater();
        setContentView(getContentView());
        initUI();
        setWindowStyle();// 必须放在视图初始化后
        regUIEvent();


    }

    // 子类实现该方法 返回布局文件的layoutId
    public abstract int getContentView();

    /**
     * 设置窗口样式
     */
    protected void setWindowStyle() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height = height;
        lp.width = width;
        getWindow().setWindowAnimations(anmi); // 设置窗口弹出动画
        getWindow().setAttributes(lp);
    }

    /**
     * 显示对话框 并设置方向
     */
    public void show(int gravity) {
        // TODO Auto-generated method stub
        super.show();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = gravity;
        getWindow().setAttributes(lp);
    }

    /**
     * 打印错误日志 返回类型：void
     */
    public void printErrorLogs(String message) {
        if (message != null) {
            Log.e(TAG, message);
        }
    }

    /**
     * 打印信息日志 返回类型：void
     */
    public void printInfoLogs(String message) {
        if (message != null) {
            Log.i(TAG, message);
        }
    }

    public void printWarnLogs(String message) {
        if (message != null) {
            Log.w(TAG, message);
        }
    }

    public void printDebugLogs(String message) {
        if (message != null) {
            Log.d(TAG, message);
        }
    }

    public String getResStr(int id) {
        String str = "";
        try {
            str = context.getResources().getString(id);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            str = "";
        }
        return str;
    }

    public int getResColor(int colorId) {
        int color = -1;
        try {
            color = context.getResources().getColor(colorId);
        } catch (NotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            color = -1;
        }
        return color;
    }


    public String getEditTextString(EditText e) {
        String str = "";
        str = (e == null ? "" : e.getText().toString().trim());
        return str;
    }

    public void showToastWithShort(String s) {
        createToast();
        toast.setDuration(Toast.LENGTH_SHORT);
        showToast(s);
    }

    public void showToastWithLong(String s) {
        createToast();
        toast.setDuration(Toast.LENGTH_LONG);
        showToast(s);
    }

    private void createToast() {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
    }

    private void showToast(String s) {
        toast.setText(s);
        toast.show();
    }

    /**
     * 打开软键盘
     *
     */
    public void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     */
    public void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

}
