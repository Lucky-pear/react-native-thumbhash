package com.thumbhash;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.ThumbhashViewManagerDelegate;
import com.facebook.react.viewmanagers.ThumbhashViewManagerInterface;

public abstract class ThumbhashViewManagerSpec<T extends View> extends SimpleViewManager<T> implements ThumbhashViewManagerInterface<T> {
  private final ViewManagerDelegate<T> mDelegate;

  public ThumbhashViewManagerSpec() {
    mDelegate = new ThumbhashViewManagerDelegate(this);
  }

  @Nullable
  @Override
  protected ViewManagerDelegate<T> getDelegate() {
    return mDelegate;
  }
}
