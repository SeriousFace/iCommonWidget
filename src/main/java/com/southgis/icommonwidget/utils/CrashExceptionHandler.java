package com.southgis.icommonwidget.utils;

import android.app.Application;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * uncaughtException处理类,当程序发生Uncaught异常的时候,
 * 由该类来接管程序,并记录发送错误报告.
 */

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashExceptionHandler.class.getSimpleName();

    private static final CrashExceptionHandler INSTANCE = new CrashExceptionHandler();

    private Application mContext;

    //捕获崩溃的handler
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashExceptionHandler getInstance() {
        return INSTANCE;
    }

    public void init(Application application) {
        this.mContext = application;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && (mDefaultHandler != null)) {
            //如果自定义的没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, throwable);
            return;
        }

        try {
            //如果处理了，让程序继续运行1秒再退出，保证文件保存
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        //退出程序
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    private boolean handleException(final Throwable exception) {
        if (exception == null) {
            return false;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                Toast.makeText(mContext, "系统错误，即将退出...", Toast.LENGTH_LONG)
                        .show();
                // 退出所有的activity
                ActivityManager.getInstance().finishAllActivity();
                //处理崩溃日志信息记录,将崩溃日志记录到手机上

                Looper.loop();
            }
        }).start();

        return true;

    }

    /**
     * 把堆栈中的错误信息保存到String对象
     *
     * @param throwable 异常信息
     * @return 异常信息
     */
    private String getExceptionMsg(Throwable throwable) {

        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        throwable.printStackTrace(printWriter);
        printWriter.close();

        return strWriter.toString();
    }

}
