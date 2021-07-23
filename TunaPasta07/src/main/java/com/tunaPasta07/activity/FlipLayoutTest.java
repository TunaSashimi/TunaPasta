package com.tunaPasta07.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tunaPasta07.R;
import com.tunaPasta07.adapter.FlipViewAdapter;
import com.tunaPasta07.tools.AphidLog;
import com.tunaPasta07.tools.IOUtils;

public class FlipLayoutTest extends Activity {
    private FlipViewAdapter flipViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flipViewAdapter = new FlipViewAdapter(this);

        flipViewAdapter.setAdapter(new FlipLayoutTestAdapter(this));

        setContentView(flipViewAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        flipViewAdapter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flipViewAdapter.onPause();
    }


    private static class FlipLayoutTestAdapter extends BaseAdapter {
        private static List<Data> dataList = new ArrayList<Data>();
        private LayoutInflater inflater;

        private FlipLayoutTestAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        //注意,这里的图片资源来自于assets!
        static {
            dataList.add(new Data("Potala Palace", "potala_palace.jpg", "http://en.wikipedia.org/wiki/Potala_Palace", "The <b>Potala Palace</b> is located in Lhasa, Tibet Autonomous Region, China. It is named after Mount Potalaka, the mythical abode of Chenresig or Avalokitesvara."));
            dataList.add(new Data("Drepung Monastery", "drepung_monastery.jpg", "http://en.wikipedia.org/wiki/Drepung", "<b>Drepung Monastery</b>, located at the foot of Mount Gephel, is one of the \"great three\" Gelukpa university monasteries of Tibet."));
            dataList.add(new Data("Sera Monastery", "sera_monastery.jpg", "http://en.wikipedia.org/wiki/Sera_Monastery", "<b>Sera Monastery</b> is one of the 'great three' Gelukpa university monasteries of Tibet, located 1.25 miles (2.01 km) north of Lhasa."));
            dataList.add(new Data("Samye Monastery", "samye_monastery.jpg", "http://en.wikipedia.org/wiki/Samye", "<b>Samye Monastery</b> is the first Buddhist monastery built in Tibet, was most probably first constructed between 775 and 779 CE."));
            dataList.add(new Data("Tashilunpo Monastery", "tashilunpo_monastery.jpg", "http://en.wikipedia.org/wiki/Tashilhunpo_Monastery", "<b>Tashilhunpo Monastery</b>, founded in 1447 by Gendun Drup, the First Dalai Lama, is a historic and culturally important monastery next to Shigatse, the second-largest city in Tibet."));
            dataList.add(new Data("Zhangmu Port", "zhangmu_port.jpg", "http://en.wikipedia.org/wiki/Zhangmu", "<b>Zhangmu/Dram</b> is a customs town and port of entry located in Nyalam County on the Nepal-China border, just uphill and across the Bhote Koshi River from the Nepalese town of Kodari."));
            dataList.add(new Data("Kathmandu", "kathmandu.jpg", "http://en.wikipedia.org/wiki/Kathmandu", "<b>Kathmandu</b> is the capital and, with more than one million inhabitants, the largest metropolitan city of Nepal."));
            dataList.add(new Data("Pokhara", "pokhara.jpg", "http://en.wikipedia.org/wiki/Pokhara", "<b>Pokhara Sub-Metropolitan City</b> is the second largest city of Nepal with approximately 250,000 inhabitants and is situated about 200 km west of the capital Kathmandu."));
            dataList.add(new Data("Patan", "patan.jpg", "http://en.wikipedia.org/wiki/Patan,_Nepal", "<b>Patan</b>, officially Lalitpur Sub-Metropolitan City, is one of the major cities of Nepal located in the south-central part of Kathmandu Valley."));
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = convertView;

            if (convertView == null)
                layout = inflater.inflate(R.layout.fliplayout, null);

            final Data data = dataList.get(position);

            TextView titleView = layout.findViewById(R.id.title);
            titleView.setText(AphidLog.format("%d. %s", position, data.title));

            ImageView photoView = layout.findViewById(R.id.photo);
            photoView.setImageBitmap(IOUtils.readBitmap(inflater.getContext().getAssets(), data.imageFilename));

            TextView textView = layout.findViewById(R.id.description);
            textView.setText(Html.fromHtml(data.description));

            Button wikipedia = layout.findViewById(R.id.wikipedia);
            wikipedia.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.link)
                    );
                    inflater.getContext().startActivity(intent);
                }
            });
            return layout;
        }

        private static class Data {
            public String title;
            public String imageFilename;
            public String link;
            public String description;

            private Data(String title, String imageFilename, String link, String description) {
                this.title = title;
                this.imageFilename = imageFilename;
                this.link = link;
                this.description = description;
            }
        }
    }

}
