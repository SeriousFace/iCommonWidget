package com.southgis.icommonwidget.main;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.southgis.icommonwidget.utils.FileChooseUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import me.rosuh.filepicker.config.FilePickerManager;

public class CommonSDK {
    public static final int ACTIVITY_RESULT_CODE = FilePickerManager.REQUEST_CODE;
    public static final int REQUEST_CODE_INFORMATION = 10000;//获取设备信息标志位
    public static final int REQUEST_CODE_LOCATION = 10001;//获取定位标志位
    public static final int REQUEST_CODE_PHOTO = 10002;//获取图片标志位
    public static final int REQUEST_CODE_FILE = 10003;//创建文件标志位
    public static final int REQUEST_CODE_DOWNLOAD = 10004;//下载标志位
    public static final int REQUEST_CODE_MEETING_WIDGET = 10005;//会议组件标志位
    public static final int REQUEST_CODE_SHARE_WIDGET = 10006;//分享组件标志位
    public static final int REQUEST_CODE_CA_WIDGET = REQUEST_CODE_FILE;//CA组件标志位

    public static boolean getPermissions(@NonNull Activity context, @NonNull int code){
        return CommonUtils.getInstance().getPermissions(context, code);
    }

    public static boolean getPermissions(@NonNull Activity context, @NonNull String[] permissions){
        return CommonUtils.getInstance().getPermissions(context, permissions);
    }

    public static String getBase64FromFile(@NonNull String path) throws Exception{
        return CommonUtils.getInstance().getBase64FromFile(path);
    }

    public static void getFileFromBase64(@NonNull String base64Code, @NonNull String savePath) throws Exception {
        CommonUtils.getInstance().getFileFromBase64(base64Code, savePath);
    }

    public static void openFileChoose(@NonNull Activity context){
        CommonUtils.getInstance().openFileChoose(context);
    }

    public static String getFileChooseResult(){
        try {
            return FilePickerManager.INSTANCE.obtainData().get(0);
        }catch (Exception e){
            return e.toString();
        }
    }

    public static String getCurrentDate() {
        return CommonUtils.getInstance().getCurrentDate();
    }
    public static String getCurrentDate(@NonNull String stringFormat) {
        return CommonUtils.getInstance().getCurrentDate(stringFormat);
    }
    public static String getCurrentDate(@NonNull SimpleDateFormat simpleDateFormat) {
        return CommonUtils.getInstance().getCurrentDate(simpleDateFormat);
    }

    public static String getMonthDayWeek(@NonNull Date date) {
        return CommonUtils.getInstance().getMonthDayWeek(date);
    }

    public static boolean isBefore(@NonNull String date1, @NonNull String date2, @NonNull String format) {
        return CommonUtils.getInstance().isBefore(date1, date2, format);
    }

    public static boolean isDateAfter(@NonNull String date1, @NonNull String date2, @NonNull String format) {
        return CommonUtils.getInstance().isDateAfter(date1, date2, format);
    }

    public static void openSaveProtect(@NonNull Context context){
        CommonUtils.getInstance().openSaveProtect(context);
    }

    public static void initCrashHandler(@NonNull Application application) {
        CommonUtils.getInstance().initCrashHandler(application);
    }
}
