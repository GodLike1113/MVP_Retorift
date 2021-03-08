package com.transsnet.mvpdemo.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.transsnet.mvpdemo.util.ReflectUtil;


/**
 * 说明: Fragment基类 <br/>
 * 邮箱: yangbin5052@gmail.com <br/>
 * Create at 2017/12/21-下午10:47 by Luuren
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment {

  protected P mPresenter;
  protected View mRootView;
  protected Activity mActivity;
  protected Context mContext;

  @SuppressLint("NewApi")
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    //初始化activity对象
    mActivity = getActivity();
    mContext = mActivity;


    if (isMvp()) {
      mPresenter = ReflectUtil.getT(this, 0);
      if (null == mPresenter) {
        throw new RuntimeException("请在实现类添加Presenter泛型....");
      }
      mPresenter.onAttach(this);
    }

    mRootView = inflater.inflate(getLayoutResource(), container, false);


    initView();
    initData();

    return mRootView;
  }

  protected abstract int getLayoutResource();

  protected abstract void initView();

  /**
   * 初始化数据,默认在onCreateView中调用
   *
   * @author caiyangbin
   * create at 2017/7/24 下午2:54
   */
  protected abstract void initData();

  /**
   * 是否打开mvp模式
   *
   * @return true --打开 ，false --关闭
   * @author caiyangbin
   * create at 2017/7/23 上午11:06
   */
  protected abstract boolean isMvp();

  @Override
  public void onDestroy() {
    super.onDestroy();

    if (null != mPresenter) {
      mPresenter.onDetach();
    }
  }

}
