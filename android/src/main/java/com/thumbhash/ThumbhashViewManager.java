package com.thumbhash;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

@ReactModule(name = ThumbhashViewManager.NAME)
public class ThumbhashViewManager extends ThumbhashViewManagerSpec<ThumbhashView> {

  public static final String NAME = "ThumbhashView";

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public ThumbhashView createViewInstance(ThemedReactContext context) {
    return new ThumbhashView(context);
  }

  @Override
  @ReactProp(name = "color")
  public void setColor(ThumbhashView view, @Nullable String color) {
    view.setBackgroundColor(Color.parseColor(color));
  }
}
