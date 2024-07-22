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

```js
import { Thumbhash } from '@luckypear/react-native-thumbhash';

// ...

<Thumbhash thumbhash="3OcRJYB4d3h/iIeHeEh3eIhw+j2w" />;
```

## Features

- [x] thumbhash rendering (decoding)
- [x] new arch/old arch support
- [x] async dencoding
- [ ] thumbhash decoding
- [ ] `resizeMode` support
- [ ] `borderRadius` support

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)

# Resources

- [react-native-blurhash](https://github.com/mrousavy/react-native-blurhash). referenced many setting. thx!
- [woltapp/blurhash](https://github.com/evanw/thumbhash). Thanks for great algorithm
