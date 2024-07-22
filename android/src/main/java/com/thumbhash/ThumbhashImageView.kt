package com.thumbhash

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64


internal class ThumbhashCache(private val _thumbhash: String?) {
  fun isDifferent(thumbhash: String?): Boolean {
    return !safeStringEquals(_thumbhash, thumbhash)
  }

  private fun safeStringEquals(left: String?, right: String?): Boolean {
    return when {
      left == null -> {
        // left: null and right: null
        right == null
      }
      right == null -> {
        // left: not null and right: null
        false
      }
      else -> {
        // left: not null and right: not null
        left == right
      }
    }
  }
}

class ThumbhashImageView(context: Context): androidx.appcompat.widget.AppCompatImageView(context) {
  var thumbhash: String? = null
  var onLoadStart: (() -> Unit)? = null
  var onLoadEnd: (() -> Unit)? = null
  var onLoadError: ((String?) -> Unit)? = null

  private var _cachedThumbhash: ThumbhashCache? = null
  private var _bitmap: Bitmap? = null

  private fun renderThumbhash() {
    try {
      onLoadStart?.invoke()
      if (thumbhash == null) {
        throw Exception("The provided thumbhash string must not be null!")
      }
      val byteArray = Base64.decode(thumbhash, Base64.DEFAULT) ?: throw Exception("The provided thumbhash string was invalid.")
      val image = ThumbHash.thumbHashToRGBA(byteArray)
      val bitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.ARGB_8888)
      bitmap.copyPixelsFromBuffer(java.nio.ByteBuffer.wrap(image.rgba))
      setImageBitmap(bitmap)
      onLoadEnd?.invoke()
    } catch (e: Exception) {
      setImageBitmap(null)
      onLoadError?.invoke(e.message)
    }
  }

  fun updateThumbhash() {
    val shouldReRender = this.shouldReRender()
    if (shouldReRender) {
      renderThumbhash()
    }
  }

  fun redraw() {
    setImageBitmap(_bitmap)
  }

  private fun shouldReRender(): Boolean {
    try {
      if (_cachedThumbhash == null) {
        return true
      }
      return _cachedThumbhash!!.isDifferent(this.thumbhash)
    } finally {
      _cachedThumbhash = ThumbhashCache(this.thumbhash)
    }
  }

  companion object {
    const val REACT_CLASS = "ThumbhashImageView"
  }
}

