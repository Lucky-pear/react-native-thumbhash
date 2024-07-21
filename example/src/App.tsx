import { StyleSheet, View } from 'react-native';
import { ThumbhashView } from 'react-native-thumbhash';

export default function App() {
  return (
    <View style={styles.container}>
      <ThumbhashView
        thumbhash="3OcRJYB4d3h/iIeHeEh3eIhw+j2w"
        // thumbhash="3PcNNYSFeXh/d3eld0iHZoZgVwh2"
        style={styles.box}
      />
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
