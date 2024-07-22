package com.thumbhash;

import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;

public abstract class ThumbhashViewManagerSpec<T extends View> extends SimpleViewManager<T> {
  public abstract void setThumbhash(T view, @Nullable String value);
  public abstract void setDecodeAsync(T view, boolean value);
}
