package com.airbnb.lottie.model;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LruCache;

import com.airbnb.lottie.LottieComposition;

import java.util.HashMap;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class LottieCompositionCache {

  private static final int CACHE_SIZE_MB = 1;
  private static final LottieCompositionCache INSTANCE = new LottieCompositionCache();
  HashMap<String,String> hashMap = new HashMap<>();
  public static LottieCompositionCache getInstance() {
    return INSTANCE;
  }

  private final LruCache<String, LottieComposition> cache = new LruCache<>(1024 * 512 * CACHE_SIZE_MB);

  @VisibleForTesting
  LottieCompositionCache() {
  }

  @Nullable
  public LottieComposition get(@Nullable String cacheKey) {
    if (cacheKey == null) {
      return null;
    }
    return cache.get(cacheKey);
  }

  public void put(@Nullable String cacheKey, LottieComposition composition) {
    if (cacheKey == null) {
      return;
    }
    if(!hashMap.containsKey(cacheKey)) {
      hashMap.put(cacheKey, cacheKey);
    }
    cache.put(cacheKey, composition);
  }

  public void clearCache() {
    if (hashMap.keySet() ==null){
       return;
    }
    for (String key : hashMap.keySet()) {
      cache.remove(key);
    }
  }
}
