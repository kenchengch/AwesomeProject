/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 https://github.com/axemclion/react-native-cordova-plugin/issues/2
  testing getCurrentBSSID
 https://github.com/parsonsmatt/WifiWizard/tree/master/www
 html sample
 https://gist.github.com/axemclion/b30bdfe991e509851705
 */
'use strict';
import React, {
  AppRegistry,
  Component,
  StyleSheet,
  Text,
  View,
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
    cordova.device.getInfo(function(){}, function(){});
    cordova.navigator.camera.getPicture(function(){}, function(){}, {
      quality : 50,
      destinationType : cordova.navigator.camera.DestinationType.FILE_URI,
      // sourceType : Camera.PictureSourceType.CAMERA,
      // targetWidth: 1000,
      // targetHeight: 1000,
      // correctOrientation: true,
      saveToPhotoAlbum: false

                  });
    //cordova.device.model;
  }
  render () {
    this.getPlugins();
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
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
  }
});

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);
