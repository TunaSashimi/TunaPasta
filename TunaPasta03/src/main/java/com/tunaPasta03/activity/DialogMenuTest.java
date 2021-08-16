package com.tunaPasta03.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta03.R;

public class DialogMenuTest extends Activity {
    private Button btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, btn10, btn11;
    private ProgressDialog progressDialog;
    private AnimationDrawable draw;

    private int[] images = {R.drawable.earth_01, R.drawable.earth_02,
            R.drawable.earth_03, R.drawable.earth_04};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.dialogmenutest);

        btn01 = findViewById(R.id.dialog_btn_01);
        btn02 = findViewById(R.id.dialog_btn_02);
        btn03 = findViewById(R.id.dialog_btn_03);
        btn04 = findViewById(R.id.dialog_btn_04);
        btn05 = findViewById(R.id.dialog_btn_05);
        btn06 = findViewById(R.id.dialog_btn_06);
        btn07 = findViewById(R.id.dialog_btn_07);
        btn08 = findViewById(R.id.dialog_btn_08);
        btn09 = findViewById(R.id.dialog_btn_09);
        btn10 = findViewById(R.id.dialog_btn_10);
        btn11 = findViewById(R.id.dialog_btn_11);

        btn01.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Builder(DialogMenuTest.this)
                        .setTitle("消息对话框")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    } // 将对话框设为不可取消（不能使用back键来取消~）
                                }).setMessage("一个消息对话框！\n不用做任何选择！")
                        .setCancelable(false).show();
            }
        });
        btn02.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Builder(DialogMenuTest.this)
                        .setTitle("选择对话框")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(DialogMenuTest.this, "确定被点击！", Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(DialogMenuTest.this, "取消被点击！", Toast.LENGTH_LONG).show();
                                    }
                                }).setMessage("这是一个选择对话框！\n您确认要做出这次修改吗？")
                        .show();
            }
        });
        btn03.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText et = new EditText(DialogMenuTest.this);
                new Builder(DialogMenuTest.this)
                        .setTitle("输入对话框")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogMenuTest.this, "用户输入的值是：" + et.getText(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(DialogMenuTest.this, "取消被点击！", Toast.LENGTH_LONG).show();
                            }
                        }).setView(et).setMessage("请输入参数：").show();
            }
        });
        btn04.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                // 第二个参数是默认被选中的选项位置，使用“-1”来表示默认情况下不选中任何选项。
                new AlertDialog.Builder(DialogMenuTest.this)
                        .setTitle("单选项对话框")
                        .setSingleChoiceItems(
                                new String[]{"张三", "李四", "王五", "赵六"},
                                1,
                                new android.content.DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                })
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).setNegativeButton("取消", null).show();
            }
        });
        btn05.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DialogMenuTest.this)
                        .setTitle("复选项对话框")
                        .setMultiChoiceItems(
                                new String[]{"张三", "李四", "王五", "赵六"},
                                new boolean[]{true, false, true, false},
                                new android.content.DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                    }
                                })
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
            }
        });
        btn06.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(DialogMenuTest.this);
                progressDialog.setTitle("环形进度条对话框");
                progressDialog.setIcon(R.drawable.dialog00);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("这是一个环形进度条！");
                progressDialog.setButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                progressDialog.show();
            }
        });
        btn07.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(DialogMenuTest.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("水平进度");
                progressDialog.setIcon(R.drawable.dialog00);
                progressDialog.setMessage("这是一个水平进度条！");
                progressDialog.show();
                progressDialog.setButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                    }
                });
                new Thread() {
                    @Override
                    public void run() {
                        // ++i显示,i++不显示,要注意了! i=0++,永远等于0!
                        for (int i = 0; ; i = i == 100 ? 0 : ++i)
                            try {
                                progressDialog.setProgress(i);
                                progressDialog.setSecondaryProgress(4 * i % 100);
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                            }
                    }
                }.start();
            }
        });
        btn08.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = getLayoutInflater().inflate(R.layout.mydialog, null);
                // 不用上面的实例化Builder ,而是用创建Dialog的方法~
                final AlertDialog alertDialog = new AlertDialog.Builder(DialogMenuTest.this).create();
                alertDialog.setTitle("自定义对话框");

                Button bu = (Button) layout.findViewById(R.id.mydialogbt);
                Button st = (Button) layout.findViewById(R.id.mydialogst);
                ImageView iv = (ImageView) layout.findViewById(R.id.mydialogiv);

                draw = new AnimationDrawable();
                for (int i = 1; i <= 4; i++) {
                    draw.addFrame(getResources().getDrawable(images[i - 1]),
                            200);
                }
                draw.setOneShot(false);
                iv.setImageDrawable(draw);

                st.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        draw.start();
                    }
                });
                bu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.setView(layout);
                alertDialog.show();
            }
        });
        btn09.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DialogMenuTest.this, R.style.NoTitleDialog);// 后面的一项为设置风格,使对话框无标题栏
                TextView textview = new TextView(DialogMenuTest.this);
                textview.setText("要想使得Dialog无标题,需要自己设置style文件");
                dialog.setContentView(textview);
                dialog.show();
            }
        });
        btn10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(DialogMenuTest.this);
                View view = layoutInflater.inflate(R.layout.progressdialog, null);
                final TextView oaprogresstitle02 = (TextView) view.findViewById(R.id.oaprogresstitle02);
                new Thread() {
                    public void run() {
                        for (int i = 0; i < 30; i++) {
                            final int j = i;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            DialogMenuTest.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    oaprogresstitle02.setText("" + j);
                                }
                            });

                        }
                    }

                    ;
                }.start();
                Dialog dialog = new Dialog(DialogMenuTest.this, R.style.NoTitleDialog);
                dialog.setContentView(view);
                dialog.show();
            }
        });
        btn11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(DialogMenuTest.this);
                View view = layoutInflater.inflate(R.layout.ordernotice, null);

                Dialog dialog = new Dialog(DialogMenuTest.this, R.style.NoTitleDialog);

                dialog.setContentView(view);
                dialog.show();
            }
        });

        ViewServer.get(this).addWindow(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "帮助");
        menu.add(Menu.NONE, 2, 2, "关于");
        menu.add(Menu.NONE, 3, 3, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                android.widget.Toast.makeText(this, "帮助菜单被点击！",
                        android.widget.Toast.LENGTH_LONG).show();
                break;
            case 2:
                android.widget.Toast.makeText(this, "关于菜单被点击！",
                        android.widget.Toast.LENGTH_LONG).show();
                break;
            case 3:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}
