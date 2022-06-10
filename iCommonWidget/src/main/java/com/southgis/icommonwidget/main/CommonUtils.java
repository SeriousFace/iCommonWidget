package com.southgis.icommonwidget.main;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.southgis.icommonwidget.utils.CrashExceptionHandler;
import com.southgis.icommonwidget.utils.DateUtils;
import com.southgis.icommonwidget.utils.DebuggerUtils;
import com.southgis.icommonwidget.utils.FileUtil;
import com.southgis.icommonwidget.utils.PermissionsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;

public class CommonUtils {
    private static CommonUtils instance;
    private CommonUtils(){  }

    /**
     * 单一实例(双检锁/双重校验锁)
     * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
     */
    public static CommonUtils getInstance() {
        if (instance == null) {
            synchronized (CommonUtils.class){
                if (instance == null) {
                    instance = new CommonUtils();
                }
            }

        }
        return instance;
    }

    //---------------  权限管理  ---------------
    public boolean getPermissions(@NonNull Activity context, @NonNull int code){
       return PermissionsUtils.getPermissions(context, code);
    }
    public boolean getPermissions(Activity context, String[] permissions){
        return PermissionsUtils.getPermissions(context, permissions);
    }

    //---------------  文件管理  ---------------
    /**
     *  文件转base64
     */
    public String getBase64FromFile(String path) throws Exception{
        return FileUtil.getBase64FromFile(path);
    }

    /**
     *   base64转文件
     */
    public void getFileFromBase64(String base64Code,String savePath) throws Exception {
        FileUtil.getFileFromBase64(base64Code, savePath);
    }

    /**
     *  打开文件选择
     */
    public int openFileChoose(Activity context){
        return FileUtil.openFileChoose(context);
    }


    //---------------  日期管理  ---------------
    /**
     * 获取当前时间---格式yyyy-MM-dd
     */
    public String getCurrentDate() {
        return DateUtils.getCurrentDate();
    }
    public String getCurrentDate(String stringFormat) {
        return DateUtils.getCurrentDate(stringFormat);
    }
    public String getCurrentDate(SimpleDateFormat simpleDateFormat) {
        return DateUtils.getCurrentDate(simpleDateFormat);
    }

    /**
     * 获取某天是星期几
     */
    public String getMonthDayWeek(Date date) {
        return DateUtils.getMonthDayWeek(date);
    }

    /**
     * 根据--指定时间格式--比较时间大小
     */
    public boolean isBefore(String date1, String date2, String format) {
        return DateUtils.isBefore(date1, date2, format);
    }

    /**
     * 根据--指定时间格式--比较时间大小
     */
    public boolean isDateAfter(String date1, String date2, String format) {
        return DateUtils.isDateAfter(date1, date2, format);
    }


    //---------------  安全管理  ---------------
    /**
     *  防动态调试 和 恶意跟踪
     */
    public void openSaveProtect(Context context){
        DebuggerUtils.checkDebuggableInNotDebugModel(context);
    }

    /**
     *   异常崩溃捕捉
     */
    public void initCrashHandler(Application application) {
        CrashExceptionHandler.getInstance().init(application);
    }
}
