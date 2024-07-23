//
//  ThumbhashModule.m
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/23/24.
//
#import "ThumbhashModule.h"

#if __has_include("react_native_thumbhash/react_native_thumbhash-Swift.h")
#import "react_native_thumbhash/react_native_thumbhash-Swift.h"
#else
#import "react_native_thumbhash-Swift.h"
#endif

#ifdef RCT_NEW_ARCH_ENABLED
using namespace facebook::react;

@interface ThumbhashModule () <NativeThumbhashModuleSpec>
@end
#endif

@interface ThumbhashModule ()

@property (nonatomic, strong) ThumbhashModuleImpl *impl;

@end

@implementation ThumbhashModule 
RCT_EXPORT_MODULE()

@synthesize moduleRegistry = _moduleRegistry;

RCT_EXPORT_METHOD(encode
                  : (NSString *)imageUri
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject
                  )
{
    [self.impl encode:imageUri
                              resolver:resolve
                              rejecter:reject];
}

- (ThumbhashModuleImpl *)impl
{
  if (!_impl) {
    _impl = [[ThumbhashModuleImpl alloc] initWithModuleRegistry:_moduleRegistry];
  }
  return _impl;
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeTestSpecJSI>(params);
}
#endif

@end
