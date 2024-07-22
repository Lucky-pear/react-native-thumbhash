#import "ThumbhashBridge.h"
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_REMAP_MODULE (ThumbhashView, ThumbhashViewManager,
                                    RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(thumbhash, NSString);
RCT_EXPORT_VIEW_PROPERTY(decodeAsync, BOOL);

RCT_EXPORT_VIEW_PROPERTY(onLoadStart, RCTDirectEventBlock);
RCT_EXPORT_VIEW_PROPERTY(onLoadEnd, RCTDirectEventBlock);
RCT_EXPORT_VIEW_PROPERTY(onLoadError, RCTDirectEventBlock);

@end
