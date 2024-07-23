import { Thumbhash } from '@luckypear/react-native-thumbhash';
import { Button, StyleSheet, View } from 'react-native';

export default function App() {
  const encode = async () => {
    console.log(
      await Thumbhash.encode(
        'https://fastly.picsum.photos/id/928/200/200.jpg?hmac=5MQxbf-ANcu87ZaOn5sOEObpZ9PpJfrOImdC7yOkBlg'
      )
    );
  };

  return (
    <View style={styles.container}>
      <Thumbhash
        thumbhash="JQgSBwACeZlqeIiIeId4eIeJBwAAAAAA"
        // thumbhash="3PcNNYSFeXh/d3eld0iHZoZgVwh2"
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
