package com.billionss.weather.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.billionss.weather.MainActivity;
import com.billionss.weather.R;
import com.billionss.weather.base.BaseActivity;
import com.billionss.weather.helper.EventCenter;
import com.billionss.weather.mvp.presenter.Presenter;
import com.billionss.weather.mvp.presenter.impl.SplashPresenterImpl;
import com.billionss.weather.mvp.view.SplashView;

import butterknife.Bind;

public class SplashActivity extends BaseActivity implements SplashView {

    @Bind(R.id.splash_logodiscribe)
    TextView splash_logodiscribe;

    @Bind(R.id.splash_version_name)
    TextView splash_version_name;


    @Bind(R.id.splash_image)
    ImageView splash_image;

   /* @Bind(R.id.countdwonview)
    CountDownView countdwonview;

*/
    private Presenter mSplashPresenterImpl;
    private boolean hasRequested;


    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionType() {
        return TransitionMode.FADE;
    }

    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initViewsAndEvents() {
        mSplashPresenterImpl = new SplashPresenterImpl(this, this);
        mSplashPresenterImpl.initialzation();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void animateBackgroundImage(Animation animation) {
      /*  countdwonview.setCountdownTime(4 * 1000);
        countdwonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countdwonview.timer!=null){
                    if(countdwonview.animator!=null){
                        countdwonview.animator.cancel();
                        splash_image.clearAnimation();
                    }
                    countdwonview.timer.cancel();
                }
//                readyGoThenKill(MainActivity.class);
            }
        });
        countdwonview.startCountDownTime(new CountDownView.OnCountdownFinishListener() {
            @Override
            public void countdownFinished() {
                //动画结束后的操作
            }
        });
*/
        splash_image.startAnimation(animation);
    }

    @Override
    public void initialzationViews(String versionName, String copyright, int backgroundResId) {
        splash_version_name.setText(versionName);
        if (backgroundResId == R.mipmap.morning) {
            splash_logodiscribe.setText(getString(R.string.splash_logodiscribe_morning));
        } else if (backgroundResId == R.mipmap.afternoon) {
            splash_logodiscribe.setText(getString(R.string.splash_logodiscribe_afternoon));
        } else if (backgroundResId == R.mipmap.night) {
            splash_logodiscribe.setText(getString(R.string.splash_logodiscribe_night));
        }
        splash_image.setImageResource(backgroundResId);
    }

    @Override
    public void readToMain() {
        readyGoThenKill(MainActivity.class);
    }

    @Override
    public void requestQuanXian() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && !hasRequested) {
                hasRequested = true;
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION}, 111);
            } else {
                mSplashPresenterImpl.beaginTime();
            }
        } else {
            mSplashPresenterImpl.beaginTime();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT > 23) {
            mSplashPresenterImpl.beaginTime();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
