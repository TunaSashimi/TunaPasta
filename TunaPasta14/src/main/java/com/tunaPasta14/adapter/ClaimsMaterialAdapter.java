package com.tunaPasta14.adapter;

import static com.tunaPasta14.activity.IndentFocusListTest.listExpend;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tunaPasta14.R;
import com.tunaPasta14.entity.IndentFocus;
import java.util.ArrayList;

/**
 * @author Tunasashimi
 * @date 09/11/2017 18:44
 * @Copyright 2017 TunaSashimi. All rights reserved.
 * @Description
 */
public class ClaimsMaterialAdapter extends BaseAdapter {

    //定义成员变量mTouchItemPosition,用来记录手指触摸的EditText的位置
    private int mTouchItemPosition = -1;

    private ArrayList<IndentFocus> indentFocusItemsArrary;
    private LayoutInflater inflater;

    public ClaimsMaterialAdapter(Context context, ArrayList<IndentFocus> indentFocusItemsArrary) {
        inflater = LayoutInflater.from(context);
        this.indentFocusItemsArrary = indentFocusItemsArrary;
    }

    @Override
    public int getCount() {
        if (indentFocusItemsArrary.size() == 0 || listExpend) {
            return indentFocusItemsArrary.size();
        } else {
            return 7;
        }
    }

    @Override
    public Object getItem(int position) {
        return indentFocusItemsArrary.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.indentfocuslisttestitem, null);

        final TextView textview_name =  convertView.findViewById(R.id.textview_name);
        final TextView textview_count =  convertView.findViewById(R.id.textview_count);
        final Button button_add =  convertView.findViewById(R.id.button_add);
        final Button button_subt =  convertView.findViewById(R.id.button_subt);
        final EditText edittext_other =  convertView.findViewById(R.id.edittext_other);

        //
        final IndentFocus indentFocusItems = indentFocusItemsArrary.get(position);
        if (indentFocusItems.isOther()) {

            //
            textview_count.setVisibility(View.GONE);
            button_add.setVisibility(View.GONE);
            button_subt.setVisibility(View.GONE);

            //
            edittext_other.setVisibility(View.VISIBLE);
            edittext_other.setText(indentFocusItems.count);

            //edittext焦点问题
            edittext_other.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    mTouchItemPosition = position;
                    return false;
                }
            });

            if (mTouchItemPosition == position) {
                edittext_other.requestFocus();
                edittext_other.setSelection(edittext_other.getText().length());
            } else {
                edittext_other.clearFocus();
            }

            //edittexy存储问题
            edittext_other.addTextChangedListener(
                    new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            indentFocusItems.count = charSequence.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                        }
                    }
            );
        } else {
            //
            textview_count.setVisibility(View.VISIBLE);
            textview_count.setText(indentFocusItems.count);

            button_add.setVisibility(View.VISIBLE);
            button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(indentFocusItems.count);
                    count++;
                    indentFocusItems.count = String.valueOf(count);
                    textview_count.setText(indentFocusItems.count);
                }
            });

            button_subt.setVisibility(View.VISIBLE);
            button_subt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(indentFocusItems.count);
                    if (count > 0) {
                        count--;
                        indentFocusItems.count = String.valueOf(count);
                        textview_count.setText(indentFocusItems.count);
                    }
                }
            });
            //
            edittext_other.setVisibility(View.GONE);
        }
        textview_name.setText(indentFocusItems.name);
        return convertView;
    }
}
