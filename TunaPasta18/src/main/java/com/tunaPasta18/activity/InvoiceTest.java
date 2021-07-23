package com.tunaPasta18.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ListView;

import com.tunaPasta18.R;
import com.tunaPasta18.adapter.InvoiceAdapter;
import com.tunaPasta18.entity.Invoice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tunasashimi
 * @date 18/07/2017 15:33
 * @Copyright 2017 TunaSashimi. All rights reserved.
 * @Description
 */
public class InvoiceTest extends Activity {

    private ListView listview;
    private InvoiceAdapter invoiceAdapter;
    private List<Invoice> InvoiceList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.invoicetest);

        listview = findViewById(R.id.listview);

        InvoiceList.add(new Invoice(false, ""));
        InvoiceList.add(new Invoice(true, ""));

        invoiceAdapter = new InvoiceAdapter(InvoiceTest.this, InvoiceList);

        listview.setAdapter(invoiceAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//        System.out.println("requestCode===>"+requestCode);
//        System.out.println("RESULT_OK===>"+resultCode);

        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {//从相册选择照片不裁切
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();

                        System.out.println("picturePath===>" + picturePath);

                        InvoiceList.add(0, new Invoice(false, picturePath));
                        invoiceAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
