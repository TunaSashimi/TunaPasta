package com.tunaPasta08.tools;

import java.util.ArrayList;

import com.tunaPasta08.R;

public class WeatherCal {
    private java.util.ArrayList<String> list;

    public WeatherCal(ArrayList<String> list) {
        super();
        this.list = list;
    }

    public String calToday() {
        String s = "今日天气:" + list.get(15) + "		所在城市:" + list.get(1).split(",")[0] +    //只能取0,有些只有省名
                "\n温度 :  " + list.get(29) + "~" + list.get(31) + "℃ 		" + list.get(21) +
                "\n" + list.get(25);
        return s;
    }

    public String distributionComplete() {
        int a = Integer.parseInt(list.get(9).substring(5, 7));        //a为当前月份~

        int wearWhat = Integer.parseInt(list.get(29).toString().replaceAll("~.*℃", ""));

        String s1 = wearWhat >= 10 ? wearWhat >= 25 ? "单衣" : "风衣" : wearWhat >= 5 ? "夹衣" : "棉衣", s2 = null;
        switch (a) {
            case 1:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 20 ? "水瓶座" : "魔羯座";
                break;
            case 2:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 19 ? "双鱼座" : "水瓶座";
                break;
            case 3:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 20 ? "白羊座" : "双鱼座";
                break;
            case 4:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 20 ? "金牛座" : "白羊座";
                break;
            case 5:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 21 ? "双子座" : "金牛座";
                break;
            case 6:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 21 ? "巨蟹座" : "双子座";
                break;
            case 7:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 22 ? "狮子座" : "巨蟹座";
                break;
            case 8:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 23 ? "处女座" : "狮子座";
                break;
            case 9:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 23 ? "天秤座" : "处女座";
                break;
            case 10:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 23 ? "天蝎座" : "天秤座";
                break;
            case 11:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 22 ? "射手座" : "天蝎座";
                break;
            case 12:
                s2 = Integer.parseInt(list.get(9).toString().substring(8, 10)) > 21 ? "摩羯座" : "射手座";
                break;
        }
        return " 衣择建议\n " + s1 + "\n\n 出生星座" + "\n " + s2 + "\n\n 随机指数" + "\n " + (int) (Math.random() * 10) +
                "\n\n 明天天气" + "\n " + list.get(45) + "\n 温度:	" + list.get(39) + "~" + list.get(41) + "℃\n\n 后天天气" + "\n " + list.get(55) +
                "\n 温度:	" + list.get(49) + "~" + list.get(51) + "℃\n\n 未来天气" + "\n " + list.get(65) + "\n 温度:	" + list.get(59) + "~" + list.get(61) + "℃";
    }

    public int getWeatherImage() {

        int wearWhat = Integer.parseInt(list.get(29).toString().replaceAll("~.*℃", ""));
        int weatherImage = wearWhat >= 10 ? wearWhat >= 25 ? R.drawable.weather02 : R.drawable.weather01 : wearWhat >= 5 ? R.drawable.weather03 : R.drawable.weather04;
        return weatherImage;
    }

}
