import { Thumbhash } from '@luckypear/react-native-thumbhash';
import { Button, StyleSheet, View } from 'react-native';

export default function App() {
  const encode = async () => {
    console.log(
      await Thumbhash.encode(
        'https://fastly.picsum.photos/id/335/200/200.jpg?hmac=CS4kiSEelfhSQQtW7j6SFUV2ZlTmUV1vaX2iZKnbx7c'
      )
    );
  };

  return (
    <View style={styles.container}>
      <Thumbhash
        thumbhash="YQgKDwJKZnbDaHePdZhneXh3mT84d4AL"
        decodeAsync={true}
        style={styles.box}
      />
      <Button title="encode" onPress={encode} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 200,
    height: 200,
    marginVertical: 20,
  },
});
