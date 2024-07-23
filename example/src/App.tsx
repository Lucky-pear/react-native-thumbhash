import { Thumbhash } from '@luckypear/react-native-thumbhash';
import { useEffect, useState } from 'react';
import { Button, Image, StyleSheet, View } from 'react-native';

const resizeMode = 'cover';

export default function App() {
  const [uri, setUri] = useState<string>(`https://picsum.photos/id/46/300/200`);
  const [thumbhash, setThumbhash] = useState<string>(
    // 'pOcRPwx4h4iPiHiDiFd4h4eKhgdoeIAG' // 400x400
    'pecRPYx4h4iPiIeDiEd3eIeAhwh4' // 300x200
  );

  useEffect(() => {
    if (uri) {
      Thumbhash.encode(uri).then(setThumbhash);
    }
  }, [thumbhash, uri]);

  const setRandomImageUri = () => {
    setUri(`https://picsum.photos/seed/${Math.random()}/4000`);
  };

  return (
    <View style={styles.container}>
      <Thumbhash
        thumbhash={thumbhash}
        decodeAsync={true}
        style={styles.box}
        resizeMode={resizeMode}
      />
      <Image
        source={{
          uri,
        }}
        style={styles.box}
        resizeMode={resizeMode}
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
    width: 300,
    height: 300,
    marginVertical: 20,
  },
});
