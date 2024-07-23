package com.thumbhash

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import java.io.ByteArrayOutputStream
import kotlin.concurrent.thread

class ThumbhashModule internal constructor(context: ReactApplicationContext) :
  NativeThumbhashModuleSpec(context), LifecycleEventListener {

  override fun getName(): String {
    return NAME
  }

  fun getBitmapByteArray(bitmap: Bitmap, compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG, quality: Int = 100): ByteArray {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(compressFormat, quality, outputStream)
    return outputStream.toByteArray()
  }

  @ReactMethod
  override fun encode(
    imageUri: String,
    promise: Promise
  ) {
    thread(true) {
      try {
        val formattedUri = imageUri.trim()

        val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(formattedUri)).build()
        val imagePipeline = Fresco.getImagePipeline()
        val dataSource = imagePipeline.fetchDecodedImage(imageRequest, reactApplicationContext)
        dataSource.subscribe(
          object : BaseBitmapDataSubscriber() {
            override fun onNewResultImpl(bitmap: Bitmap?) {
              try {
                if (dataSource.isFinished && bitmap != null) {
                  val thumbHashData = ThumbHash.bitmapToThumbHash(bitmap)
                  val thumbHash = Base64.encodeToString(thumbHashData, Base64.DEFAULT)
                  promise.resolve(thumbHash)

                } else {
                  if (dataSource.failureCause != null) {
                    promise.reject("LOAD_ERROR", dataSource.failureCause)
                  } else {
                    promise.reject("LOAD_ERROR", Exception("Failed to load URI!"))
                  }
                }
              } finally {
                dataSource.close()
              }
            }

            override fun onFailureImpl(
              dataSource: DataSource<CloseableReference<CloseableImage>>
            ) {
              try {
                if (dataSource.failureCause != null) {
                  promise.reject("LOAD_ERROR", dataSource.failureCause)
                } else {
                  promise.reject("LOAD_ERROR", Exception("Failed to load URI!"))
                }
              } finally {
                dataSource.close()
              }
            }
          },
          CallerThreadExecutor.getInstance())
      } catch (e: Exception) {
        promise.reject("INTERNAL", e)
      }
    }
  }

  override fun onHostResume() {}

  override fun onHostPause() {}

  override fun onHostDestroy() {
//    BlurHashDecoder.clearCache()
  }

  companion object {
    const val NAME = "ThumbhashModule"
  }
}
