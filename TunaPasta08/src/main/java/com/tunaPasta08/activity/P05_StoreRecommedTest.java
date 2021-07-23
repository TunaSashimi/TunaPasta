package com.tunaPasta08.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tunaPasta08.R;

public class P05_StoreRecommedTest extends Activity {
    private Spinner sp01, sp02, sp03, sp04;
    private List<String> type, season, target, skin;
    private ArrayAdapter<String> a01, a02, a03, a04;
    private Button button;
    private TextView textcloth, textsugg;
    private Button bt;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p05_storerecommedtest);
        textcloth = findViewById(R.id.appo_tx01);
        textsugg = findViewById(R.id.appo_tx02);

        bt = findViewById(R.id.appoiment_bt);
        bt.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
//						startActivity(new android.content.Intent(Appointment.this, Reloading.class));
            }
        });

        button = findViewById(R.id.appo_bu);
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                textcloth.setVisibility(0);
                textsugg.setVisibility(0);
                if ("0".equals("" + sp02.getSelectedItemPosition())) {
                    textcloth.setText("穿衬衫是很好的选择！素色或者直条纹都是最保险的，而且要是颜色跟材质很出色的话，穿衬衫对美女来说是很性感的！对于格子衬衫, 有些女孩会特别喜欢,下身的话衬衫搭配上卡其色的休闲裤也没什么不好的，只是多了些文质彬彬少了些野性感，还是牛仔裤最合适！");
                }
                if ("1".equals("" + sp02.getSelectedItemPosition())) {
                    textcloth.setText("鞋子很重要,夏天的话还是把Crocs和夹脚拖鞋留在家里比较好，不是说这些鞋子不好，其实男人有时候穿一双有型的拖鞋是还满性感的，但是约会绝不合适，原因跟没领子上衣一样，太随性休闲啦！牛仔裤搭配一双帆布鞋是绝对不会出错的好选择。");
                }
                if ("2".equals("" + sp02.getSelectedItemPosition())) {
                    textcloth.setText("到这个地步了,更不用穿正式的西装，琼斯里面有好多西服都挺适合年轻人穿的。。人瘦，穿个休闲，整个就空落落的。好看吗？修身的话，显的整个人有曲线。");
                }
                if ("3".equals("" + sp02.getSelectedItemPosition())) {
                    textcloth.setText("寒冷的冬天穿件夹克外套那是不可或缺的，但很多男人怎么都穿不好，夹克不用搞花稍，经典款式就很好看，颜色上也以素色为佳，布料有一点隐隐的纹路就很有质感，必须要特别注意的是，夹克的肩线一定要刚刚好一点，买的时候不要买袖子跟肩膀的接缝处垂到手臂上去了的！");
                }
                if ("0".equals("" + sp03.getSelectedItemPosition())) {
                    textsugg.setText("第一印象关系到是否还有下文,所以要充分尊重别人,包括:10分钟提前到达约会地点,衣着整洁大方,有别的美女目光也不乱漂,不点酒和尽量不带负面情绪去赴约,地点首选公园,步行街等热闹地点,表现要开朗,多准备几个好笑的笑话来避免冷场,还要准备一个别致的礼物,如果中意的话可以请吃饭+看电影,分开的时候把礼物递上并赞美对方,为下一次的约会做下铺垫.");
                }
                if ("1".equals("" + sp03.getSelectedItemPosition())) {
                    textsugg.setText("约会准点到就即可,但是不要改变已经定了的行程,可以一起散步,逛博物馆(如果博学),买衣服之类,感情要想发展的快,最好是两个人一起运动,如跳舞,游泳,游山玩水,尽兴了或累会增进感情,时间不要过21点,陪她等车等她上车走再离去, 如果她愿意让你送她回家，那表示她对你已有点心心相印了。此期间主要用自己对她的关心来俘获她,等她习惯了,她就离不开你了.");
                }
                if ("2".equals("" + sp03.getSelectedItemPosition())) {
                    textsugg.setText("都有平淡时期,自己反思原因,出来一起的时候态度要真诚,可以好好聊聊但不要谈及4点:询问她过去的恋爱经历, 自己在金钱上的困难, 教她人生是怎么回事儿, 以及跟他无谓的争论;重大节日或生日,要精挑细选礼物,最好是自己动手自制一个小礼物,让她经常能用到看到的,内容自己去想.");
                }
                if ("3".equals("" + sp03.getSelectedItemPosition())) {
                    textsugg.setText("女孩子会喜欢格调高雅而整洁的小餐厅、有异国情调的西餐厅,也可以到高楼大厦的顶楼餐厅去，可一边进餐一边饱览都市夜色。要在最有浪漫的条件下做事,基本上到这一步的话主要看前面的表现,表现好总能成功~");
                }
            }
        });
        sp01 = findViewById(R.id.appo_sp01);
        type = new ArrayList();
        type.add("成熟稳重");
        type.add("热情似火");
        a01 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, type);
        a01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp01.setAdapter(a01);

        sp02 = findViewById(R.id.appo_sp02);
        season = new ArrayList();
        season.add("春季");
        season.add("夏季");
        season.add("秋季");
        season.add("冬季");
        a02 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, season);
        a02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp02.setAdapter(a02);

        sp03 = findViewById(R.id.appo_sp03);
        target = new ArrayList();
        target.add("最初印象");
        target.add("慢慢发展");
        target.add("平淡时期");
        target.add("求婚行动");
        a03 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, target);
        a03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp03.setAdapter(a03);

        sp04 = findViewById(R.id.appo_sp04);
        skin = new ArrayList();
        skin.add("白皙");
        skin.add("偏白");
        skin.add("偏黑");
        skin.add("棕色");
        a04 = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, skin);
        a04.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp04.setAdapter(a04);
    }
}
