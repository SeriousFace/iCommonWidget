package com.southgis.iandroidwidget.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.southgis.iandroidwidget.adapter.AppModuleAdapter;
import com.southgis.iandroidwidget.bean.ModuleBean;
import com.southgis.leqibike.R;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private GridView gv;

    private AppModuleAdapter mAdapter;
    private List<ModuleBean> list = new ArrayList<>();
    private String[] moduleIdGroup = {"hc", "ca", "rl",
            "jm", "hy", "wl",
            "tp", "yl", "kj"};
    private String[] moduleNameGroup = {"缓存", "CA", "人脸",
            "加密", "会议", "网络",
            "图片", "预览", "控件"};
    private int[] picIdGroup = {R.drawable.icon_cache_module, R.drawable.icon_ca_module, R.drawable.icon_face_module,
            R.drawable.icon_encrypt_module, R.drawable.icon_meeting_module, R.drawable.icon_network_module,
            R.drawable.icon_picture_module, R.drawable.icon_preview_module, R.drawable.icon_view_module};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.gv_module);

        for (int i=0; i<moduleNameGroup.length; i++){
            ModuleBean bean = new ModuleBean();
            bean.setName(moduleNameGroup[i]);
            bean.setPicId(picIdGroup[i]);
            bean.setModuleId(moduleIdGroup[i]);
            list.add(bean);
        }
        mAdapter = new AppModuleAdapter(MainActivity.this, list, new AppModuleAdapter.ItemAction() {
            @Override
            public void setItemAction(int position) {
                switch (list.get(position).getModuleId()){
                    case "hc":
//                        startActivity(new Intent(MainActivity.this, CacheWidgetActivity.class));
                        String appId = "wxacd913534b39a73e"; // 填移动应用(App)的 AppId，非小程序的 AppID
                        IWXAPI api = WXAPIFactory.createWXAPI(MainActivity.this, appId);

                        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                        req.userName = "gh_507b5575507b"; // 填小程序原始id
                        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 开发版，体验版和正式版
                        api.sendReq(req);
                        break;

                    case "ca":
//                        startActivity(new Intent(MainActivity.this, CAWidgetActivity.class));
                        break;

                    case "rl":
//                        startActivity(getPackageManager().getLaunchIntentForPackage("com.southgis.cs_realestate"));
                        break;

                    case "jm":
//                        startActivity(new Intent(MainActivity.this, EncryptWidgetActivity.class));
                        break;

                    case "hy":
//                        startActivity(new Intent(MainActivity.this, MeetingWidgetActivity.class));
                        break;

                    case "wl":
//                        startActivity(new Intent(MainActivity.this, NetworkWidgetActivity.class));
                        break;

                    case "tp":
//                        startActivity(new Intent(MainActivity.this, PictureWidgetActivity.class));
                        break;

                    case "yl":
//                        startActivity(new Intent(MainActivity.this, PreviewWidgetActivity.class));
                        break;

                    case "kj":
//                        startActivity(new Intent(MainActivity.this, ViewWidgetActivity.class));
                        break;
                }
            }
        });
        gv.setAdapter(mAdapter);
    }
}