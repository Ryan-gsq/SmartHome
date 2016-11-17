package bbu.com.smartoffice;

/**
 * Created by G on 2016/11/15 0015.
 */

import android.app.Application;

import com.orhanobut.logger.Logger;

import bbu.com.smartoffice.utils.Utils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO 全局初始化
        //日志工具初始化
        Logger.init("GG");
        Utils.setApplication(this);
    }
}
