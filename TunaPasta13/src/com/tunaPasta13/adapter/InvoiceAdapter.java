package com.tunaPasta13.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tunaPasta13.R;
import com.tunaPasta13.entity.Invoice;

import java.util.List;


public class InvoiceAdapter extends BaseAdapter {
    private Activity activity;
    private List<Invoice> invoiceList;
    private LayoutInflater mInflater;

    public InvoiceAdapter(Activity activity, List<Invoice> invoiceList) {
        this.activity = activity;
        this.invoiceList = invoiceList;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return invoiceList.size();
    }

    @Override
    public Object getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.invoicetestitem, null);
        }

        RelativeLayout relative_refueling_certificate = (RelativeLayout) convertView.findViewById(R.id.relative_refueling_certificate);

        ImageView image_click_upload = (ImageView) convertView.findViewById(R.id.image_click_upload);
        ImageView image_invoice = (ImageView) convertView.findViewById(R.id.image_invoice);

        TextView text_click_upload = (TextView) convertView.findViewById(R.id.text_click_upload);
        TextView text_amountmoney = (TextView) convertView.findViewById(R.id.text_amountmoney);

        Invoice invoice = invoiceList.get(position);
        boolean isAdd = invoice.isAdd;

        if (!isAdd) {
            relative_refueling_certificate.setBackgroundColor(Color.parseColor("#E7E7E7"));
            image_click_upload.setVisibility(View.GONE);
            text_click_upload.setVisibility(View.GONE);

            //
            text_amountmoney.setText("200");

            Bitmap bitmap= BitmapFactory.decodeFile(invoice.picturePath);
            image_invoice.setImageBitmap(bitmap);
        }

        image_click_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);//Pick an item fromthe data
                intent.setType("image/*");//从所有图片中进行选择
                activity.startActivityForResult(intent, 0);
            }
        });

        return convertView;
    }
}
