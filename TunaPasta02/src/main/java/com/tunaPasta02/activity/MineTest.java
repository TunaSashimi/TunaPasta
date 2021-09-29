package com.tunaPasta02.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta02.R;
import com.tunaPasta02.entity.MineHelper;

public class MineTest extends Activity {
    private GridView gv;
    private String[] mines;
    private Button button;
    private RadioButton rabus, rabuc;
    private TextView tv;
    private boolean flag = true, flagrun;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            tv.setText("消耗时间" + i);
        }
    };
    private int i;
    private Thread t;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minetest);
        gv = findViewById(R.id.mine_gridview);
        rabus = findViewById(R.id.mine_s);
        rabuc = findViewById(R.id.mine_c);
        button = findViewById(R.id.minebutton);
        tv = findViewById(R.id.mine_textview);
        rabus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                flag = true;
            }
        });
        rabuc.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                flag = false;
            }
        });

        button.setOnClickListener(v -> {
            button.setText("再来一次");
            Builder builder = new Builder(MineTest.this);
            final EditText et = new EditText(MineTest.this);
            builder.setTitle(" 输入雷数(布置密度不超过75%)");
            builder.setPositiveButton("确定", (dialog, which) -> {
                try {
                    if (Integer.parseInt(et.getText().toString()) >= 0 && Integer.parseInt(et.getText().toString()) < 75) {
                        mines = new MineHelper(Integer.parseInt(et.getText().toString())).layoutMines();

                        Cearance();

                        flagrun = false;
                        Thread.sleep(1000);
                        flagrun = true;
                        t = new Thread() {
                            @Override
                            public void run() {
                                for (i = 0; i < 999 && flagrun; i++) {
                                    try {
                                        Thread.sleep(1000);
                                        handler.sendEmptyMessage(0);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        };

                        t.start();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast.makeText(MineTest.this, "输入错误请重新输入", Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setView(et);
            builder.create().show();
        });
        gv.setOnItemClickListener((parent, view, position, id) -> {
            if (flag) {
                if (mines[position].endsWith(" ")) {
                    mines[position] = mines[position].substring(0, mines[position].length() - 1);
                }
                if (mines[position].startsWith("-1")) {
                    for (int i = 0; i < mines.length; i++) {
                        mines[i] = " " + mines[i];
                    }
                    Toast.makeText(getApplicationContext(), "你输了", Toast.LENGTH_SHORT).show();
                } else {
                    if ((mines[position]).startsWith("0")) {
                        Circle(position);
                    }
                    mines[position] = " " + mines[position];
                    // 判断是否获胜
                    int total = 0;
                    for (int i = 0; i < mines.length; i++) {
                        if (mines[i].startsWith("-1") || mines[i].startsWith(" "))
                            total++;
                        if (total == 100) {
                            Toast.makeText(getApplicationContext(), "你赢了", Toast.LENGTH_SHORT).show();
                        }
                    }
                    // System.out.println(total); //计算进度~
                }
                Cearance();
            } else { // 不以空格开头的话~
                if (!mines[position].startsWith(" ")) {
                    mines[position] = mines[position] + " ";
                    Cearance();
                }
            }
        });
    }

    private void Cearance() {
        List<Map<String, Object>> data = new ArrayList();
        for (int i = 0; i < mines.length; i++) {
            Map<String, Object> map = new HashMap();
            map.put("mine", mines[i]);
            data.add(map);
        }
        gv.setAdapter(new SimpleAdapter(this, data, R.layout.minetestitem, new String[]{"mine"}, new int[]{R.id.mineitem_button}));
    }

    private void Circle(int position) {
        if (position + 1 < mines.length && position % 10 != 9 && !mines[position + 1].endsWith(" ")) {
            mines[position + 1] = " " + mines[position + 1];
        }
        if (position + 9 < mines.length && position % 10 != 0 && !mines[position + 9].endsWith(" ")) {
            mines[position + 9] = " " + mines[position + 9];
        }
        if (position + 10 < mines.length && !mines[position + 10].endsWith(" ")) {
            mines[position + 10] = " " + mines[position + 10];
        }
        if (position + 11 < mines.length && position % 10 != 9 && !mines[position + 11].endsWith(" ")) {
            mines[position + 11] = " " + mines[position + 11];
        }
        if (position - 1 >= 0 && position % 10 != 0 && !mines[position - 1].endsWith(" ")) {
            mines[position - 1] = " " + mines[position - 1];
        }
        if (position - 9 >= 0 && position % 10 != 9 && !mines[position - 9].endsWith(" ")) {
            mines[position - 9] = " " + mines[position - 9];
        }
        if (position - 10 >= 0 && !mines[position - 10].endsWith(" ")) {
            mines[position - 10] = " " + mines[position - 10];
        }
        if (position - 11 >= 0 && position % 10 != 0 && !mines[position - 11].endsWith(" ")) {
            mines[position - 11] = " " + mines[position - 11];
        }
    }
}