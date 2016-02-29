/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';
import React, {
  Platform,
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
  TouchableHighlight,
 TouchableNativeFeedback ,
 TouchableElement,
  NativeModules
} from 'react-native';

import cordova from 'react-native-cordova-plugin';

class AwesomeProject extends Component {

  constructor (props) {
    super(props);
    this.state = {
      result: ''
    };
  }

  getPlugins () {

    cordova.navigator.camera.getPicture(function(fileUri){console.log(fileUri)}, function(){console.log('fail')}, {
      sourceType : cordova.navigator.camera.PictureSourceType.CAMERA,
                  encodingType: cordova.navigator.camera.EncodingType.JPEG,  targetWidth: 1280,  targetHeight: 720,
      destinationType : cordova.navigator.camera.DestinationType.FILE_URI,
      // sourceType : Camera.PictureSourceType.CAMERA,
      // targetWidth: 1000,
      // targetHeight: 1000,
      // correctOrientation: true,

          quality: 20,
   //mediaType: cordova.navigator.camera.MediaType.CAMERA,

      saveToPhotoAlbum: true
      });

  }
  testDevice(){
      cordova.device.getInfo(function(){console.log(cordova.device);}, function(){});
  }
  render () {
    var TouchableElement = TouchableHighlight;
       if (Platform.OS === 'android') {
        TouchableElement = TouchableNativeFeedback;
       }
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>

       <TouchableElement
              style={styles.button}
              onPress={()=>this.getPlugins()}>
              <View>
                <Text >Button!</Text>
              </View>
            </TouchableElement>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Shake1 or press menu button for dev menu

        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF'
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5
  },
  button: {
    textAlign: 'center',
     color: '#ffffff',





  }
});

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);
