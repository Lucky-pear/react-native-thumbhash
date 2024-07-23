import { Thumbhash } from '@luckypear/react-native-thumbhash';
import { useEffect, useState } from 'react';
import { Button, Image, StyleSheet, View } from 'react-native';

export default function App() {
  const [uri, setUri] = useState<string>(`https://picsum.photos/id/46/400`);
  const [thumbhash, setThumbhash] = useState<string>(
    'pOcRPwx4h4iPiHiDiFd4h4eKhgdoeIAG'
  );

  useEffect(() => {
    if (uri) {
      Thumbhash.encode(uri).then(setThumbhash);
    }
  }, [thumbhash, uri]);

  const setRandomImageUri = () => {
    setUri(`https://picsum.photos/seed/${Math.random()}/400`);
  };

  return (
    <View style={styles.container}>
      <Thumbhash thumbhash={thumbhash} decodeAsync={true} style={styles.box} />
      <Image
        source={{
          uri,
        }}
        style={styles.box}
      />
      <Button title="set random image" onPress={setRandomImageUri} />
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
