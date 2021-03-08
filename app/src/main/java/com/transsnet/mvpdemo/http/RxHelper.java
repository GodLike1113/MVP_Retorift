package com.transsnet.mvpdemo.http;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * PackageName: com.smartretax.srt.utils <br/>
 * <br/>
 * ClassName: RxHelper <br/>
 * <br/>
 * Directions: <br/>
 * <br/>
 * Author: caiyangbin <br/>
 * <br/>
 * Email: yangbin5052@gmail.com <br/>
 * <br/>
 * Create at 2017/9/13-下午2:37
 */
public class RxHelper {
  static final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
    @Override
    public ObservableSource apply(Observable upstream) {
      return upstream.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread());
    }
  };
  /** 封装线程 */
  @SuppressWarnings("unchecked")
  public static <T> ObservableTransformer<T, T> io_main() {
      return (ObservableTransformer<T, T>) schedulersTransformer;
  }
}
