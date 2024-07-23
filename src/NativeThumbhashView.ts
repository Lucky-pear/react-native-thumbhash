import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { ViewProps } from 'react-native';
import type {
  DirectEventHandler,
  WithDefault,
} from 'react-native/Libraries/Types/CodegenTypes';

interface NativeProps extends ViewProps {
  thumbhash: string;
  decodeAsync?: WithDefault<boolean, false>;
  resizeMode?: WithDefault<'cover' | 'contain' | 'stretch', 'cover'>;
  onLoadStart?: DirectEventHandler<null>;
  onLoadEnd?: DirectEventHandler<null>;
  onLoadError?: DirectEventHandler<{ message?: string }>;
}

export default codegenNativeComponent<NativeProps>('ThumbhashView');
