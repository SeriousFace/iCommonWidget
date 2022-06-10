package com.southgis.iandroidwidget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.southgis.iandroidwidget.bean.ModuleBean;
import com.southgis.leqibike.R;

import java.util.List;

/**
 *  GridView 适配器
 */
public class AppModuleAdapter extends BaseAdapter {
    private Context context;
    private List<ModuleBean> list ;
    private ItemAction mItemAction;

    public AppModuleAdapter(Context context , List<ModuleBean> list, ItemAction itemAction){
        this.context = context;
        this.list = list;
        mItemAction = itemAction;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        convertView = LayoutInflater.from(context).inflate(R.layout.gridview_module,parent,false);
        vh = new ViewHolder();
        vh.ivItemIcon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
        vh.ivItemName = (TextView) convertView.findViewById(R.id.tv_item_name);
        vh.lyItem = (RelativeLayout) convertView.findViewById(R.id.ly_item);

        ModuleBean bean = list.get(position);

        vh.ivItemIcon.setImageDrawable(context.getResources().getDrawable(bean.getPicId()));
        if (4 < bean.getName().length()){
            vh.ivItemName.setText(bean.getName().substring(0,4) + "\n" + bean.getName().substring(4,bean.getName().length()));
        }else {
            vh.ivItemName.setText(bean.getName());
        }

        vh.lyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mItemAction){
                    mItemAction.setItemAction(position);
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        ImageView ivItemIcon;
        TextView ivItemName;
        RelativeLayout lyItem;
    }

    public interface ItemAction{
        public void setItemAction(int position);
    }
}


