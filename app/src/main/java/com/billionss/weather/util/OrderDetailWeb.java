package com.billionss.weather.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.billionss.weather.R;


@SuppressLint("SetJavaScriptEnabled")
public class OrderDetailWeb extends AppCompatActivity implements OnClickListener {
	private RelativeLayout.LayoutParams rl_params;
	private float mScale;
	private WebView order_webview;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderdetail_web);
		initWebView();//初始化webView控件及其设置
		setTransparentBar(Color.TRANSPARENT, 0);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		Log.v("generateOrder", url);
		order_webview.loadUrl(url);
	}


	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void setTransparentBar(@ColorInt int color, int alpha) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			View decorView = window.getDecorView();
			int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			decorView.setSystemUiVisibility(option);

			int finalColor = alpha == 0 ? Color.TRANSPARENT :
					Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));

			window.setNavigationBarColor(finalColor);
			window.setStatusBarColor(finalColor);

		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			ViewGroup decorView = (ViewGroup) window.getDecorView();
			int finalColor = alpha == 0 ? Color.TRANSPARENT :
					Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
			decorView.addView(createStatusBarView(this, finalColor));
			if (navigationBarExist(this)) {
				window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
				decorView.addView(createNavBarView(this, finalColor));
			}
		}

	}

	private View createStatusBarView(Context context, @ColorInt int color) {
		View mStatusBarTintView = new View(context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
				(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
		params.gravity = Gravity.TOP;
		mStatusBarTintView.setLayoutParams(params);
		mStatusBarTintView.setBackgroundColor(color);
		return mStatusBarTintView;
	}

	private View createNavBarView(Context context, @ColorInt int color) {
		View mNavBarTintView = new View(context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
				(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight(context));
		params.gravity = Gravity.BOTTOM;
		mNavBarTintView.setLayoutParams(params);
		mNavBarTintView.setBackgroundColor(color);
		return mNavBarTintView;
	}

	private boolean navigationBarExist(Activity activity) {
		WindowManager windowManager = activity.getWindowManager();
		Display d = windowManager.getDefaultDisplay();

		DisplayMetrics realDisplayMetrics = new DisplayMetrics();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			d.getRealMetrics(realDisplayMetrics);
		}

		int realHeight = realDisplayMetrics.heightPixels;
		int realWidth = realDisplayMetrics.widthPixels;

		DisplayMetrics displayMetrics = new DisplayMetrics();
		d.getMetrics(displayMetrics);

		int displayHeight = displayMetrics.heightPixels;
		int displayWidth = displayMetrics.widthPixels;

		return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
	}


	private int getStatusBarHeight(Context context) {
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		return context.getResources().getDimensionPixelSize(resourceId);
	}



	public int getNavigationHeight(Context context) {

		int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
		return context.getResources().getDimensionPixelSize(resourceId);
	}


	private void initWebView() {
		order_webview = (WebView) findViewById(R.id.order_webview);
		textView = (TextView) findViewById(R.id.txv_title);
		order_webview.getSettings().setJavaScriptEnabled(true);
		order_webview.getSettings().setDomStorageEnabled(true);
		order_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(
				true);
		order_webview.setWebViewClient(new MyWebViewClient());
		WebChromeClient wvcc = new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				super.onReceivedTitle(view, title);
				textView.setText(title);
				//title 就是网页的title
			}
		};
		// 设置setWebChromeClient对象
		order_webview.setWebChromeClient(wvcc);
	}

	/**
	 * 自定义的webViewClient
	 * @author my
	 *
	 */
	private class MyWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {

			super.onPageFinished(view, url);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			handler.proceed();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.back_btn) {
			onBackPressed();
		}
	}

	@Override
	public void onBackPressed() {
		if (order_webview.canGoBack()) {
			order_webview.goBack();
		}else {
			super.onBackPressed();
		}
	}

}
