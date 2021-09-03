package com.tunaPasta14.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.tunaPasta14.R;

public class InflateTest extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inflatetest);

        LinearLayout linearLayout = findViewById(R.id.linear);
        LayoutInflater inflater = LayoutInflater.from(this);

        //当root不为null，attachToRoot为true时，表示将resource指定的布局添加到root中，
        //添加的过程中resource所指定的的布局的根节点的各个属性都是有效的。不需要addView。
        inflater.inflate(R.layout.inflatetestitem, linearLayout, true);

        //root不为null，attachToRoot为false
        //如果我想让linearlayout的根节点有效，又不想让其处于某一个容器中，那我就可以设置root不为null，
        // 而attachToRoot为false。这样，指定root的目的也就很明确了，即root会协助linearlayout的根节点生成布局参数

        //当root为null时，不论attachToRoot为true还是为false，显示效果都是一样的。
        // 当root为null表示我不需要将第一个参数所指定的布局添加到任何容器中，
        // 同时也表示没有任何容器来来协助第一个参数所指定布局的根节点生成布局参数。
    }
}