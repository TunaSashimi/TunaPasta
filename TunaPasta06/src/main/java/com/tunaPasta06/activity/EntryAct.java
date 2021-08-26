package com.tunaPasta06.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tunaPasta06.R;

public class EntryAct extends Activity {

    private String[] s = {"GalleryAutoChangeTest", "BootTest", "ImproveListViewTest", "Rotata3DEffectTest",
            "OutOfMemoryErrorTest", "AnimatedGalleryTest", "DragListViewTest", "SmileyParserTest",
            "PageTurningTest", "ViewPagerTest", "ActivityGroupTest01", "ActivityGroupTest02",
            "ActivityGroupTest03", "MeasureSizeTest", "IncludeTest", "MergeTest",
            "SlidePageViewTest", "TopNavigationBarTest", "NavigationBarTest", "NavigationBarRelevanceTest",
            "GridViewTest", "QueryActivityTest", "GetPhoneSpecificationsTest", "PieChartTest",
            "AppWidgetProviderIntroduce", "GetPhoneLanguageTest", "Offect3DAnimationTest", "PinProgressButtonTest",};

    private Class<?>[] c = {GalleryAutoChangeTest.class, BootTest.class, ImproveListViewTest.class, Rotate3DEffectTest.class,
            OutOfMemoryErrorTest.class, AnimatedGalleryTest.class, DragListViewTest.class, SmileyParserTest.class,
            PageTurningTest.class, ViewPagerTest.class, ActivityGroupTest01.class, ActivityGroup02.class,
            ActivityGroup03.class, MeasureSizeTest.class, IncludeTest.class, MergeTest.class,
            SlidePageViewTest.class, TopNavigationBarTest.class, NavigationBarTest.class, NavigationBarRelevanceTest.class,
            GridViewTest.class, QueryActivityTest.class, GetPhoneSpecificationsTest.class, PieChartTest.class,
            AppWidgetProviderIntroduceTest.class, GetPhoneLanguageTest.class, Offect3DAnimationTest.class, PinProgressButtonTest.class,};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryact); // 语句排列顺序~
        ListView lv = findViewById(R.id.listView);
        List<String> list = new ArrayList();
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        lv.setAdapter(new ArrayAdapter(this, R.layout.entryactitem, list));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, android.view.View arg1, int arg2, long arg3) {
                startActivity(new Intent(EntryAct.this, c[arg2]));
            }
        });

        Builder builder = new Builder(this);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addShortcut();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setMessage("需要在桌面上创建本程序的快捷方式么");
        builder.show();

    }

    // 创建快捷方式到桌面的代码!
    private void addShortcut() {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
        shortcut.putExtra("duplicate", false); // 不允许重复创建

        // 指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        // 注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
        ComponentName comp = new ComponentName(this.getPackageName(), "." + this.getLocalClassName());
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));

        // 快捷方式的图标
        ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(this, R.drawable.tunasashimi);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        sendBroadcast(shortcut);
    }
}