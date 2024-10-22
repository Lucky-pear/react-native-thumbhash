package com.thumbhash

import android.widget.ImageView
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerHelper
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.ViewProps
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.events.Event

internal class LoadStartEvent(
  surfaceId: Int,
  viewTag: Int,
) : Event<LoadStartEvent>(surfaceId, viewTag) {
  override fun getEventName() = EVENT_NAME

  override fun getEventData(): WritableMap = Arguments.createMap()

  companion object {
    const val EVENT_NAME = "topThumbhashLoadStart"
  }
}

internal class LoadEndEvent(
  surfaceId: Int,
  viewTag: Int,
) : Event<LoadStartEvent>(surfaceId, viewTag) {
  override fun getEventName() = EVENT_NAME

  override fun getEventData(): WritableMap = Arguments.createMap()

  companion object {
    const val EVENT_NAME = "topThumbhashLoadEnd"
  }
}

internal class LoadErrorEvent(
  surfaceId: Int,
  viewTag: Int,
  var message: String?,
) : Event<LoadStartEvent>(surfaceId, viewTag) {
  override fun getEventName() = EVENT_NAME

  override fun getEventData(): WritableMap =
    Arguments.createMap().apply { putString("message", message) }

  companion object {
    const val EVENT_NAME = "topThumbhashLoadError"
  }
}

val DEFAULT_RESIZE_MODE = ImageView.ScaleType.CENTER_CROP

class ThumbhashViewManager : ThumbhashViewManagerSpec<ThumbhashImageView>() {
  @ReactProp(name = "thumbhash")
  override fun setThumbhash(view: ThumbhashImageView, thumbhash: String?) {
    view.thumbhash = thumbhash
  }

  @ReactProp(name = "decodeAsync", defaultBoolean = false)
  override fun setDecodeAsync(view: ThumbhashImageView, decodeAsync: Boolean) {
    view.decodeAsync = decodeAsync
  }

  @ReactProp(name = ViewProps.RESIZE_MODE)
  override fun setResizeMode(view: ThumbhashImageView, resizeMode: String?) {
    view.scaleType = parseResizeMode(resizeMode ?: "cover")
    view.redraw()
  }

  override fun onAfterUpdateTransaction(view: ThumbhashImageView) {
    super.onAfterUpdateTransaction(view)
    view.updateThumbhash()
  }

  public override fun createViewInstance(context: ThemedReactContext): ThumbhashImageView {
    val image = ThumbhashImageView(context)
    image.clipToOutline = true
    image.scaleType = DEFAULT_RESIZE_MODE
    return image
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any>? {
    return MapBuilder.builder<String, Any>()
      .put(LoadErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadError"))
      .put(LoadStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadStart"))
      .put(LoadEndEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadEnd"))
      .build()
  }

  override fun getName() = REACT_CLASS

  override fun addEventEmitters(reactContext: ThemedReactContext, view: ThumbhashImageView) {
    super.addEventEmitters(reactContext, view)

    val dispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, view.id)
    val surfaceId = UIManagerHelper.getSurfaceId(reactContext)

    if (dispatcher != null) {
      view.onLoadStart = { dispatcher.dispatchEvent(LoadStartEvent(surfaceId, view.id)) }
      view.onLoadEnd = { dispatcher.dispatchEvent(LoadEndEvent(surfaceId, view.id)) }
      view.onLoadError = { message ->
        dispatcher.dispatchEvent(LoadErrorEvent(surfaceId, view.id, message))
      }
    }
  }

  companion object {
    const val REACT_CLASS = "ThumbhashView"

    fun parseResizeMode(resizeMode: String): ImageView.ScaleType {
      return when (resizeMode) {
        "contain" -> ImageView.ScaleType.FIT_CENTER
        "cover" -> ImageView.ScaleType.CENTER_CROP
        "stretch" -> ImageView.ScaleType.FIT_XY
        else -> DEFAULT_RESIZE_MODE
      }
    }
  }
}
