package com.tunaPasta14.framgent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tunaPasta14.R;

import androidx.fragment.app.Fragment;

public class Setting_Fragment extends Fragment {

    private AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        builder = new AlertDialog.Builder(getActivity()).setTitle("提示")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                }).setNegativeButton("确定", null);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        TextView textView = (TextView) view.findViewById(R.id.title_text);
        textView.setText("设置");
        RelativeLayout re = (RelativeLayout) view.findViewById(R.id.clear_map);
        re.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                builder.setMessage("确定要清楚地图缓存吗？").show();
            }
        });

        ((RelativeLayout) view.findViewById(R.id.clear_search))
                .setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        builder.setMessage("确定要清楚所有搜索吗？").show();
                    }
                });
        ((RelativeLayout) view.findViewById(R.id.setting_hotel))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View hell = inflater.inflate(R.layout.seek_bar, null);
                        SeekBar seekBar = (SeekBar) hell
                                .findViewById(R.id.seekBar1);
                        // 设置变化范围,有个问题没有解决，就是seekbar的背景和前景不能正常出来
                        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                            int currentProgress;

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                seekBar.setProgress(currentProgress);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onProgressChanged(SeekBar seekBar,
                                                          int progress, boolean fromUser) {
                                if (progress > 0
                                        && progress < seekBar.getMax() / 6) {
                                    progress = 0;
                                } else if (progress < seekBar.getMax() / 2) {
                                    progress = seekBar.getMax() / 3;
                                } else if (progress < seekBar.getMax() * 5 / 6) {
                                    progress = seekBar.getMax() * 2 / 3;
                                } else {
                                    progress = seekBar.getMax();
                                }
                                currentProgress = progress;
                            }
                        });

                        new AlertDialog.Builder(getActivity())
                                .setTitle("选择身边酒店的距离")
                                .setNegativeButton("确定", null).setView(hell)
                                .show();
                    }
                });

        return view;
    }

}
