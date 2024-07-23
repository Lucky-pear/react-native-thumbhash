import type { NativeSyntheticEvent, ViewProps } from 'react-native';
import ThumbhashViewNativeComponent from './ThumbhashViewNativeComponent';
import React from 'react';
import NativeThumbhashModule from './NativeThumbhashModule';

export interface ThumbhashProps extends Omit<ViewProps, 'children'> {
  /**
   * The thumbhash string to use. Example: `3OcRJYB4d3h/iIeHeEh3eIhw+j2w`.
   */
  thumbhash: string;
  /**
   * Asynchronously decode the thumbhash on a background Thread instead of the UI-Thread.
   * @default false
   */
  decodeAsync?: boolean;

  /**
   * Emitted when the Thumbhash received new parameters and started to decode the given `thumbhash` string.
   */
  onLoadStart?: () => void;

  /**
   * Emitted when the Thumbhash successfully decoded the given `thumbhash` string and rendered the image to the `<Thumbhash>` view.
   */
  onLoadEnd?: () => void;

  /**
   * Emitted when the Thumbhash failed to decode/load.
   */
  onLoadError?: (message?: string) => void;
}

export class Thumbhash extends React.PureComponent<ThumbhashProps> {
  static displayName = 'Thumbhash';

  constructor(props: ThumbhashProps) {
    super(props);
    this._onLoadStart = this._onLoadStart.bind(this);
    this._onLoadEnd = this._onLoadEnd.bind(this);
    this._onLoadError = this._onLoadError.bind(this);
  }
  /**
   * Encodes the given image URI to a thumbhash string
   * @param imageUri An URI of Image, parsed by react-native image loader.
   * @example
   * const thumbhash = await Thumbhash.encode('https://picsum.photos/200')
   */
  static encode(imageUri: string): Promise<string> {
    if (typeof imageUri !== 'string') {
      throw new Error('imageUri must be a non-empty string!');
    }

    return NativeThumbhashModule.encode(imageUri);
  }

  _onLoadStart() {
    if (this.props.onLoadStart != null) this.props.onLoadStart();
  }
  _onLoadEnd() {
    if (this.props.onLoadEnd != null) this.props.onLoadEnd();
  }
  _onLoadError(event?: NativeSyntheticEvent<{ message?: string }>) {
    if (this.props.onLoadError != null)
      this.props.onLoadError(event?.nativeEvent?.message);
  }

  render() {
    return (
      <ThumbhashViewNativeComponent
        {...this.props}
        onLoadStart={this._onLoadStart}
        onLoadEnd={this._onLoadEnd}
        onLoadError={this._onLoadError}
      />
    );
  }
}
