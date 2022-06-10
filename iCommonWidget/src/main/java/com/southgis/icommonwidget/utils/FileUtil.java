package com.southgis.icommonwidget.utils;

import android.app.Activity;
import android.util.Base64;

import com.southgis.icommonwidget.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import me.rosuh.filepicker.config.FilePickerManager;

public final class FileUtil {
    /**
     *  文件转base64
     */
    public static String getBase64FromFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer,Base64.DEFAULT);
    }

    /**
     *   base64转文件
     */
    public static void getFileFromBase64(String base64Code,String savePath) throws Exception {
        byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = new FileOutputStream(savePath);
        out.write(buffer);
        out.close();
    }

    /**
     *  打开文件选择
     */
    public static int openFileChoose(Activity context){
        FilePickerManager.INSTANCE
                .from(context)
                .setTheme(R.style.FilePickerAppTheme)
                .forResult(FilePickerManager.REQUEST_CODE);

        return FilePickerManager.REQUEST_CODE;
    }
}
