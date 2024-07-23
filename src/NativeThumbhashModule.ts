import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  encode(imageUri: string): Promise<string>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('ThumbhashModule');
