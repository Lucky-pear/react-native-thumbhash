#ifdef RCT_NEW_ARCH_ENABLED
#import "ThumbhashView.h"

#import <react/renderer/components/RNThumbhashViewSpec/ComponentDescriptors.h>
#import <react/renderer/components/RNThumbhashViewSpec/EventEmitters.h>
#import <react/renderer/components/RNThumbhashViewSpec/Props.h>
#import <react/renderer/components/RNThumbhashViewSpec/RCTComponentViewHelpers.h>

#import "RCTFabricComponentsPlugins.h"
#import "Utils.h"

using namespace facebook::react;

@interface ThumbhashView () <RCTThumbhashViewViewProtocol>

@end

@implementation ThumbhashView {
    UIView * _view;
}

+ (ComponentDescriptorProvider)componentDescriptorProvider
{
    return concreteComponentDescriptorProvider<ThumbhashViewComponentDescriptor>();
}

- (instancetype)initWithFrame:(CGRect)frame
{
  if (self = [super initWithFrame:frame]) {
    static const auto defaultProps = std::make_shared<const ThumbhashViewProps>();
    _props = defaultProps;

    _view = [[UIView alloc] init];

    self.contentView = _view;
  }

  return self;
}

- (void)updateProps:(Props::Shared const &)props oldProps:(Props::Shared const &)oldProps
{
    const auto &oldViewProps = *std::static_pointer_cast<ThumbhashViewProps const>(_props);
    const auto &newViewProps = *std::static_pointer_cast<ThumbhashViewProps const>(props);

    if (oldViewProps.color != newViewProps.color) {
        NSString * colorToConvert = [[NSString alloc] initWithUTF8String: newViewProps.color.c_str()];
        [_view setBackgroundColor: [Utils hexStringToColor:colorToConvert]];
    }

    [super updateProps:props oldProps:oldProps];
}

Class<RCTComponentViewProtocol> ThumbhashViewCls(void)
{
    return ThumbhashView.class;
}

@end
#endif
