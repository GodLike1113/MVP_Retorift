package com.transsnet.mvpdemo.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 说明: 首先声明了泛型 V，V 对应负责展示的View。由于 V 一般比较大，这里采用了弱引用的写法，避免内存泄漏。 isViewAttached() 用于检测 V
 * 是否已关联 P，为真则让 getView() 返回对应的 V，否则返回 null。另外两个方法负责 V 和 P 的关联与解关联<br/> <br/>
 * 邮箱: yangbin5052@gmail.com <br/>
 * Create at 2017/12/21-下午10:43 by Luuren
 */
public abstract class BasePresenter<V> {

  protected WeakReference<V> mViewRef;
  protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  public void onAttach(V view) {
    mViewRef = new WeakReference<>(view);
  }

  protected V getView() {
    return isViewAttached() ? mViewRef.get() : null;
  }

  protected boolean isViewAttached() {
    return null != mViewRef && null != mViewRef.get();
  }

  public void onDetach() {
    if (null != mViewRef) {
      mViewRef.clear();
      mViewRef = null;
    }
    if (null != mCompositeDisposable) {
      clear();
    }
  }

  public void clear() {
    mCompositeDisposable.clear();
  }

  public void add(Disposable disposable) {
    mCompositeDisposable.add(disposable);
  }
}