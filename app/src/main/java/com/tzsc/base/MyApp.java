package com.tzsc.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.pm.PackageManager;

import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EasyUiApplication;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.Iterator;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by HDL on 2017/11/20.
 *
 * @author HDL
 */

public class MyApp extends Application {
    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化网络请求库
        HttpSend.getInstance().initContext(app);
        initHX();
    }

    private void initHX() {
        //环信相关
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
            ELog.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAutoLogin(true);//默认第一次登录成功之后会自动登录，这里暂时不用自动登录
        // 默认添加好友时，是不需要验证的，改成需要验证
        //options.setAcceptInvitationAlways(false);
        //初始化
//        EMClient.getInstance().init(getApplicationContext(), options);
        EaseUI.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        //EMClient.getInstance().setDebugMode(true);
        EasyUiApplication.setAppContext(this);

       //初始化loadingdialog
        StyleManager s = new StyleManager();
        //在这里调用方法设置s的属性
        //code here...
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);

        LoadingDialog.initStyle(s);

    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
