package com.awesomeproject;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.common.ReactConstants;
import com.facebook.common.logging.FLog;
import java.util.Arrays;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Settings;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.LOG;

import io.cordova.reactnative.*;

import javax.annotation.Nullable;


//************ https://github.com/axemclion/react-native-cordova-plugin/issues/10
public class MainActivity extends Activity  implements DefaultHardwareBackBtnHandler{
  private boolean mDoRefresh = false;
   private LifecycleState mLifecycleState = LifecycleState.BEFORE_RESUME;
    private @Nullable ReactInstanceManager mReactInstanceManager;
   private ReactRootView mReactRootView;
   private static final String TAG = "CORDOVA_ACTIVITY";
  public CordovaPluginPackage cordovaPluginPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);

      mReactRootView = new ReactRootView(this);

      mReactInstanceManager = ReactInstanceManager.builder()
              .setApplication(getApplication())
              .setBundleAssetName("index.android.bundle")
              .setJSMainModuleName("index.android")
              .addPackage(new MainReactPackage())
              .addPackage(new CordovaPluginPackage(this, savedInstanceState))
              .setUseDeveloperSupport(true)
              .setInitialLifecycleState(mLifecycleState)
              .build();
              LOG.e(TAG, "onCreate");
      mReactRootView.startReactApplication(mReactInstanceManager, getMainComponentName(), null);
      setContentView(mReactRootView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
     LOG.e(TAG, "Incoming Result. Request code = " + requestCode);

         CordovaInterfaceImpl cordovaInterface = cordovaPluginPackage.cordovaPluginAdapter.cordovaInterface;

           super.onActivityResult(requestCode, resultCode, intent);
        cordovaInterface.onActivityResult(requestCode, resultCode, intent);

    }
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */


    protected String getMainComponentName() {
        return "AwesomeProject";
    }

    /**
     * Returns whether dev mode should be enabled.
     * This enables e.g. the dev menu.
     */

    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    @Override
      protected void onPause() {
        super.onPause();

        mLifecycleState = LifecycleState.BEFORE_RESUME;

        if (mReactInstanceManager != null) {
          LOG.e(TAG, "onPause");
          mReactInstanceManager.onPause();
        }
      }

      @Override
      protected void onResume() {
        super.onResume();

        mLifecycleState = LifecycleState.RESUMED;

        if (mReactInstanceManager != null) {
                LOG.e(TAG, "onResume");
          mReactInstanceManager.onResume(this, this);
        }
      }

      @Override
      protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            LOG.e(TAG, "onDestroy");
          mReactInstanceManager.onDestroy();
        }
      }



      @Override
      public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mReactInstanceManager != null &&
            mReactInstanceManager.getDevSupportManager().getDevSupportEnabled()) {
          if (keyCode == KeyEvent.KEYCODE_MENU) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
          }
          if (keyCode == KeyEvent.KEYCODE_R && !(getCurrentFocus() instanceof EditText)) {
            // Enable double-tap-R-to-reload
            if (mDoRefresh) {
              mReactInstanceManager.getDevSupportManager().handleReloadJS();
              mDoRefresh = false;
            } else {
              mDoRefresh = true;
              new Handler().postDelayed(
                  new Runnable() {
                    @Override
                    public void run() {
                      mDoRefresh = false;
                    }
                  },
                  200);
            }
          }
        }
        return super.onKeyUp(keyCode, event);
      }

      @Override
      public void onBackPressed() {
        if (mReactInstanceManager != null) {
          mReactInstanceManager.onBackPressed();
        } else {
          super.onBackPressed();
        }
      }

      @Override
      public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
      }

}
