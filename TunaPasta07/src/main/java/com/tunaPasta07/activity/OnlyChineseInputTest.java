package com.tunaPasta07.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.tunaPasta07.R;

public class OnlyChineseInputTest extends Activity {
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.onlychineseinputtest);

        name = findViewById(R.id.name);
        // 设置监听
        name.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int editStart;
            private int editEnd;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // name.setText(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = name.getSelectionStart();
                editEnd = name.getSelectionEnd();
                // 判断输入是不是中文字符 ^[\u4e00-\u9fa5]*$是中文字符串正则
                if (!temp.toString().matches("^[\u4e00-\u9fa5]*$")) {
                    Toast.makeText(OnlyChineseInputTest.this, "只能输入中文", Toast.LENGTH_SHORT).show();
                    if (s.length() != 0) {
                        s.delete(editStart - 1, editEnd);
                    }
                    int tempSelection = editStart;
                    name.setText(s);
                    name.setSelection(tempSelection);
                }
            }
        });
    }
}
