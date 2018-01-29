package com.paradoxie.autoupdatedemo;

import android.app.Application;
import android.os.Environment;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * Created by Administrator on 2018/1/29.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = false;
        /**
         * 如果你不想在通知栏显示下载进度，你可以将这个接口设置为false，默认值为true。
         */
//        Beta.enableNotification = true;
        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.upgradeCheckPeriod = 10 * 1000;
        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1* 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
//        Beta.canShowUpgradeActs.add(MainActivity.class);

        Bugly.init(getApplicationContext(), "7e9620470c", true);//bugly自动升级
    }



    public void customView(){
        /**
         * 设置自定义tip弹窗UI布局
         * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  提示信息：beta_tip_message 如： android:tag="beta_tip_message"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/tips_dialog.xml
         */
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;

        /**
         *  如果想监听升级对话框的生命周期事件，可以通过设置OnUILifecycleListener接口
         *  回调参数解释：
         *  context - 当前弹窗上下文对象
         *  view - 升级对话框的根布局视图，可通过这个对象查找指定view控件
         *  upgradeInfo - 升级信息
         */
    /*  Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onCreate");
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:

                // 通过id方式获取控件，并更改imageview图片
                ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
                imageView.setImageResource(R.mipmap.ic_launcher);

                // 通过tag方式获取控件，并更改布局内容
                TextView textView = (TextView) view.findViewWithTag("textview");
                textView.setText("my custom text");

                // 更多的操作：比如设置控件的点击事件
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onResume");
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onPause");
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStop");
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onDestory");
            }
        };*/

        /**
         * 自定义Activity参考，通过回调接口来跳转到你自定义的Actiivty中。
         */
        /*Beta.upgradeListener = new UpgradeListener() {

            @Override
            public void onUpgrade(int ret, UpgradeInfo strategy, boolean isManual, boolean isSilence) {
                if (strategy != null) {
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(), UpgradeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "没有更新", Toast.LENGTH_SHORT).show();
                }
            }
        };*/
    }
}
