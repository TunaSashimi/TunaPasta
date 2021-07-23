package com.tunaPasta04.adapter;

import java.io.File;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta04.R;
import com.tunaPasta04.util.Util;

public class FileAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Bitmap move_icon;
    private List<String> items;
    private List<String> paths;
    private Context file_context;

    public FileAdapter(Context context, List<String> it, List<String> pa) {
        file_context = context;
        inflater = LayoutInflater.from(context);
        items = it;
        paths = pa;
        move_icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.move_to);
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("StringFormatMatches")
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.localfilemanagertestitem, null);
            holder = new ViewHolder();
            holder.text = view.findViewById(R.id.small);
            holder.icon = view.findViewById(R.id.file_icon);
            holder.file_size = view.findViewById(R.id.file_size);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        File f = new File(paths.get(position));
        if (items.get(position).equals("b1")) {
            holder.text.setText(R.string.back_to_root);
            holder.icon.setImageBitmap(move_icon);
            holder.file_size.setVisibility(View.INVISIBLE);
        } else if (items.get(position).equals("b2")) {
            holder.text.setText(R.string.back_to_last);
            holder.icon.setImageBitmap(move_icon);
            holder.file_size.setVisibility(View.INVISIBLE);
        } else {
            holder.text.setText(f.getName());
            holder.file_size.setVisibility(View.VISIBLE);
            if (f.isDirectory()) {

                String mess = file_context.getResources().getString(R.string.child_items_info);

                int length = f.listFiles() == null ? 0 : f.listFiles().length;//f.listFiles有可能为空,所以做三目修改!

                holder.file_size.setText(String.format(mess, length));//共有子文件4个

            } else {
                holder.file_size.setText(Util.convertFileSize(f.length()));
            }
            holder.icon.setImageBitmap(Util.getFileIcon(file_context, f));
        }
        return view;

    }

    private class ViewHolder {
        TextView text;
        ImageView icon;
        TextView file_size;
    }

    public boolean delete(int position) {
        Object obj = getItem(position);
        System.out.println(obj);

        return false;
    }
}
