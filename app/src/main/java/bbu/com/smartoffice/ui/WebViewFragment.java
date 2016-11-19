package bbu.com.smartoffice.ui;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import bbu.com.smartoffice.Model.DeviceInfoModel;
import bbu.com.smartoffice.R;
import bbu.com.smartoffice.base.BaseFragment;
import bbu.com.smartoffice.net.HttpRequest;
import bbu.com.smartoffice.presenter.WebViewPresenter;
import bbu.com.smartoffice.utils.OneNetUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static bbu.com.smartoffice.ManageActivity.manageActivity;
import static bbu.com.smartoffice.R.id.webView;

/**
 * Created by G on 2016/11/15 0015.
 */

public class WebViewFragment extends BaseFragment<WebViewPresenter, DeviceInfoModel> {
    @Bind(R.id.toolbarBg)
    View toolbarBg;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tbNavigation)
    ImageView tbNavigation;
    @Bind(R.id.tbLogo)
    ImageView tbLogo;
    @Bind(R.id.tbAdd)
    ImageView tbAdd;
    @Bind(webView)
    WebView mWebView;


    private View rootView;
    private AnimatedVectorDrawable menuAnimated;
    private AnimatedVectorDrawable backAnimated;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, rootView);
        animatorIcon();
        setListener();
        initWebView();
        return rootView;
    }


    /**
     * 视图监听
     */
    private void setListener() {
        manageActivity.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                animNavButton(false);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                animNavButton(true);
            }
        });
        tbNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manageActivity.getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * NavButton 矢量动画
     */
    private void animatorIcon() {
        menuAnimated = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.ic_menu_animatable);
        backAnimated = (AnimatedVectorDrawable) getActivity().getDrawable(R.drawable.ic_back_animatable);
    }

    /**
     * NavButton 矢量动画 改变   back - true  menu>back
     */
    private void animNavButton(boolean back) {
        if (back) {
            tbNavigation.setImageDrawable(menuAnimated);
            menuAnimated.start();
        } else {
            tbNavigation.setImageDrawable(backAnimated);
            backAnimated.start();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/hack/hack1.html");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.addJavascriptInterface(this, "GetAndroid");

    }

    @Override
    public boolean onInterceptBackClick() {
        manageActivity.getFragmentManager().popBackStack();
        return true;
    }

    @JavascriptInterface
    public void queryDevices(String id) {

        try {

            HttpRequest.getInstance().queryDevice(id, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mWebView.loadUrl("javascript:appendDeviceItem('" + result + "')");
                            } catch (Exception e) {
                                Logger.d("javascript:appendDeviceItem('" + result + "')");
                            }

                        }
                    });
                }
            });
        } catch (IOException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //TODO
    @JavascriptInterface
    public void sendCMD(String json) {
        OneNetUtils.SendCmd("4069468", json);
    }

}

