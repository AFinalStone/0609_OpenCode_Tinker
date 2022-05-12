//package com.afs.thinker.library.app;
//
//import android.annotation.TargetApi;
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//
//import androidx.multidex.MultiDex;
//
//import com.afs.thinker.library.util.SampleApplicationContext;
//import com.tencent.tinker.anno.DefaultLifeCycle;
//import com.tencent.tinker.entry.DefaultApplicationLike;
//import com.tencent.tinker.loader.shareutil.ShareConstants;
//
//@DefaultLifeCycle(application = "tinker.sample.android.app.SampleApplication",//自动帮助生成SampleApplication类这个类就是Application
//        flags = ShareConstants.TINKER_ENABLE_ALL,
//        loadVerifyFlag = false)
//public class SampleApplicationLike extends DefaultApplicationLike {
//    private static final String TAG = "Tinker.SampleApplicationLike";
//
//    public SampleApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
//                                 long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
//        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
//    }
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    @Override
//    public void onBaseContextAttached(Context base) {
//        super.onBaseContextAttached(base);
//        //you must install multiDex whatever tinker is installed!
//        MultiDex.install(base);
//
//        SampleApplicationContext.application = getApplication();
//        SampleApplicationContext.context = getApplication();
//    }
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
//        getApplication().registerActivityLifecycleCallbacks(callback);
//    }
//}
