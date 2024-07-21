#import "ThumbhashBridge.h"
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_REMAP_MODULE (ThumbhashView, ThumbhashViewManager,
                                    RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(thumbhash, NSString);

@end
