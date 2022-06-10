package com.southgis.icommonwidget.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Debug;
import android.os.Process;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;

import me.rosuh.filepicker.BuildConfig;

public class DebuggerUtils {
    /**
     *  判断当前应用是否为Debug状态
     */
    public static boolean isDebuggable(Context context){
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }catch (Exception e){
            return false;
        }
    }

    /**
     *  检测是否在非Debug编译模式下，进行了调试操作，以防动态调试
     */
    public static void checkDebuggableInNotDebugModel(Context context){
        //非Debug编译，反调试检测
        if (!BuildConfig.DEBUG){
            if (isDebuggable(context)) {
                Toast.makeText(context, "正在被动态调试", Toast.LENGTH_SHORT).show();
                //退出程序
                Process.killProcess(Process.myPid());
                System.exit(1);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                   while (true){
                       try{
                           Thread.sleep(300);
                           if (Debug.isDebuggerConnected()){
                               Toast.makeText(context, "正在被动态调试", Toast.LENGTH_SHORT).show();
                               //退出程序
                               Process.killProcess(Process.myPid());
                               System.exit(1);
                           }

                           if (isUnderTraced()){
                               Toast.makeText(context, "正在被恶意进程跟踪", Toast.LENGTH_SHORT).show();
                               //退出程序
                               Process.killProcess(Process.myPid());
                               System.exit(1);
                           }
                       }catch (InterruptedException e){
                           e.printStackTrace();
                       }
                   }
                }
            }, "SafeGuardThread");
            thread.start();
        }

        if (isUnderTraced()){
            Toast.makeText(context, "正在被恶意进程跟踪", Toast.LENGTH_SHORT).show();
            //退出程序
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    private static boolean isUnderTraced(){
        String processStatusFilePath = String.format(Locale.US, "/proc/%d/status", Process.myPid());
        File procInfoFile = new File(processStatusFilePath);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(procInfoFile));
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null){
                if (readLine.contains("TracerPid")){
                    String[] arrays = readLine.split(":");
                    if (arrays.length == 2){
                        int tracePid = Integer.parseInt(arrays[1].trim());
                        if (tracePid != 0){
                            return true;
                        }
                    }
                }
            }
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
