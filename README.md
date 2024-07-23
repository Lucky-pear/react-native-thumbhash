# react-native-thumbhash

> A very compact representation of an image placeholder for react native

## What is thumbhash?

`thumbhash` is a A very compact representation of a placeholder for an image. Store it inline with your data and show it while the real image is loading for a smoother loading experience.

To know more about thumbhash click [here](https://github.com/evanw/thumbhash).

I recommend you to have a pre-encoded thumbhash string and store it in your server. And when your server delivers an image, you can add thumbhash in your payload. You can now immediately show `<Thumbhash>` component before your `Image` component finishes loading.

## This is BETA release

> Thumbhash is still on development...🚧
> you can check which features are supported at the moment

## Installation

```sh
yarn add @luckypear/react-native-thumbhash
```

or

```sh
npm install @luckypear/react-native-thumbhash
```

### ios

```sh
npx pod-install
```

you will need pod install

### android

no extra steps are needed

## Usage

The `<Blurhash>` component has the following properties:

<table>
  <tr>
    <th>Name</th>
    <th>Type</th>
    <th>Explanation</th>
    <th>Default Value</th>
  </td>
  <tr>
    <td><code>blurhash</code></td>
    <td><code>string</code></td>
    <td>The thumbhash string to use. Example: <code>3OcRJYB4d3h/iIeHeEh3eIhw+j2w</code></td>
    <td>*Required</td>
  </tr>
  <tr>
    <td><code>decodeAsync</code></td>
    <td><code>boolean</code></td>
    <td>Asynchronously decode the thumbhash on a background Thread instead of the UI-Thread.</td>
    <td><code>false</code></td>
  </tr>
  <tr>
    <td><code>resizeMode</code></td>
    <td><code>'cover' | 'contain' | 'stretch'</code></td>
    <td>Sets the resize mode of the image. (<code>'repeat'</code>and <code>'center'</code> is not supported.)
    <blockquote>See: <a href="https://reactnative.dev/docs/image#resizemode">Image::resizeMode</a></blockquote>
    </td>
    <td><code>'cover'</code></td>
  </tr>
  <tr>
    <td><code>onLoadStart</code></td>
    <td><code>() => void</code></td>
    <td>A callback to call when the Blurhash started to decode the given <code>blurhash</code> string.</td>
    <td><code>undefined</code></td>
  </tr>
  <tr>
    <td><code>onLoadEnd</code></td>
    <td><code>() => void</code></td>
    <td>A callback to call when the Blurhash successfully decoded the given <code>blurhash</code> string and rendered the image to the <code>&lt;Blurhash&gt;</code> view.</td>
    <td><code>undefined</code></td>
  </tr>
  <tr>
    <td><code>onLoadError</code></td>
    <td><code>(message?: string) => void</code></td>
    <td>A callback to call when the Blurhash failed to load. Use the <code>message</code> parameter to get the error message.</td>
    <td><code>undefined</code></td>
  </tr>
  <tr>
    <td>All <code>View</code> props</td>
    <td><code>ViewProps</code></td>
    <td>All properties from the React Native <code>View</code></td>
    <td><code>undefined</code></td>
  </tr>
</table>

Example

```tsx
import { Thumbhash } from '@luckypear/react-native-thumbhash';

// ...

<Thumbhash thumbhash="3OcRJYB4d3h/iIeHeEh3eIhw+j2w" />;
```

### Encoding

You can encode with this library and generate your thumbhash in your app.

```ts
const thumbhash = await Thumbhash.encode('https://picsum.photos/200');
```

encode method `loads image via react-native image loader`, and `scales it` and `encodes it` in to a thumbhash string. And this means it is a time taking task so you should be aware of using it.

If you want to encode it in your react-native app, I recommend you a tip that you can use thumbhash string to file name that you will set to server (presigned url or cdn or whatever). then you can just make your own `Image` component to extract the file name(which is a thumbhash string) and display it before you load your image. this will save some bytes for your playload :)

## Features

- [x] thumbhash rendering (decoding)
- [x] new arch/old arch support
- [x] async dencoding
- [x] thumbhash encoding
- [x] `resizeMode` support

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)

# Resources

- [react-native-blurhash](https://github.com/mrousavy/react-native-blurhash). referenced many setting. thx!
- [woltapp/blurhash](https://github.com/evanw/thumbhash). Thanks for great algorithm
