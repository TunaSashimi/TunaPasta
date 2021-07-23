package com.tunaPasta19.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta19.R;
import com.tunaPasta19.tuna.TunaView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomLotteryTest extends Activity {

    //
    private static final int RED_BALL_TOTAL = 33, BLUE_BALL_TOTAL = 16, RED_BALL_NUMBER = 6, BLUE_BALL_NUMBER = 1;
    private static int redBallLength = 6, blueBallLength = 1;

    //
    private static List redBallList = new ArrayList(), blueBallList = new ArrayList(),
            redBallExcludeList = new ArrayList(), blueBallExcludeList = new ArrayList();

    private static int redBallRandom, buleBallRandom;

    private TunaView[] redBallArray = new TunaView[RED_BALL_TOTAL], blueBallArray = new TunaView[BLUE_BALL_TOTAL];
    private TunaView tunaPredictionResults;


    private TextView textRedBallResult, textBlueBallResult;
    private Spinner spinnerRedBall, spinnerBlueBall;

    public static final String SHAREPREFERENCES_REDBALL_LENGTH = "SHAREPREFERENCES_REDBALL_LENGTH";
    public static final String SHAREPREFERENCES_BLUEBALL_LENGTH = "SHAREPREFERENCES_BLUEBALL_LENGTH";

    public static final String SHAREPREFERENCES_REDBALL_LIST = "SHAREPREFERENCES_REDBALL_LIST";
    public static final String SHAREPREFERENCES_BLUEBALL_LIST = "SHAREPREFERENCES_BLUEBALL_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.customlotterytest);

        //
        textRedBallResult =  findViewById(R.id.textRedBallResult);
        textBlueBallResult =  findViewById(R.id.textBlueBallResult);
        spinnerRedBall =  findViewById(R.id.spinnerRedBall);
        spinnerBlueBall =  findViewById(R.id.spinnerBlueBall);

        List<String> redBallSpinnerlist = new ArrayList();
        for (int i = RED_BALL_NUMBER; i <= RED_BALL_TOTAL; i++) {
            redBallSpinnerlist.add("红球数: " + i);
        }
        List<String> blueBallSpinnerlist = new ArrayList();
        for (int j = BLUE_BALL_NUMBER; j <= BLUE_BALL_TOTAL; j++) {
            blueBallSpinnerlist.add("蓝球数: " + j);
        }

        spinnerRedBall.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, redBallSpinnerlist));
        spinnerRedBall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                redBallLength = position + RED_BALL_NUMBER;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinnerBlueBall.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, blueBallSpinnerlist));
        spinnerBlueBall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blueBallLength = position + BLUE_BALL_NUMBER;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        for (int i = 1; i <= RED_BALL_TOTAL; i++) {
            int redBallId;
            if (i < 10) {
                redBallId = this.getResources().getIdentifier("redBall0" + i, "id", getPackageName());
            } else {
                redBallId = this.getResources().getIdentifier("redBall" + i, "id", getPackageName());
            }
            redBallArray[i - 1] =  findViewById(redBallId);
        }

        for (int j = 1; j <= BLUE_BALL_TOTAL; j++) {
            int blueBallId;
            if (j < 10) {
                blueBallId = this.getResources().getIdentifier("blueBall0" + j, "id", getPackageName());
            } else {
                blueBallId = this.getResources().getIdentifier("blueBall" + j, "id", getPackageName());
            }
            blueBallArray[j - 1] =  findViewById(blueBallId);
        }

        //
        tunaPredictionResults =  findViewById(R.id.tunaPredictionResults);
        tunaPredictionResults.setTunaTouchUpListener(new TunaView.TunaTouchUpListener() {
            @Override
            public void tunaTouchUp(View v) {
                calculationResults();
            }
        });


        dataRevovery();
    }

    public void dataRevovery() {

        //
        redBallLength = getSharePreferencesIntegerLength(getApplication(), SHAREPREFERENCES_REDBALL_LENGTH);
        blueBallLength = getSharePreferencesIntegerLength(getApplication(), SHAREPREFERENCES_BLUEBALL_LENGTH);
        spinnerRedBall.setSelection(redBallLength - RED_BALL_NUMBER);
        spinnerBlueBall.setSelection(blueBallLength - BLUE_BALL_NUMBER);


        //
        List sharePreferencesRedBallList = getSharePreferencesIntegerList(getApplication(), SHAREPREFERENCES_REDBALL_LIST);
        List sharePreferencesBlueBallList = getSharePreferencesIntegerList(getApplication(), SHAREPREFERENCES_BLUEBALL_LIST);


        for (int i = 0; i < sharePreferencesRedBallList.size(); i++) {
            int j = (int) sharePreferencesRedBallList.get(i) - 1;
            redBallArray[j].setTunaSelect(true);

        }

        for (int i = 0; i < sharePreferencesBlueBallList.size(); i++) {
            int j = (int) sharePreferencesBlueBallList.get(i) - 1;
            blueBallArray[j].setTunaSelect(true);
        }

    }


    public void calculationResults() {
        //
        redBallExcludeList.clear();
        blueBallExcludeList.clear();
        redBallList.clear();
        blueBallList.clear();

        //
        for (int i = 1; i <= RED_BALL_TOTAL; i++) {
            if (redBallArray[i - 1].isTunaSelect()) {
                redBallExcludeList.add(i);
            }
        }
        for (int j = 1; j <= BLUE_BALL_TOTAL; j++) {
            if (blueBallArray[j - 1].isTunaSelect()) {
                blueBallExcludeList.add(j);
            }
        }

        if (RED_BALL_TOTAL - redBallExcludeList.size() < redBallLength) {
            Toast.makeText(CustomLotteryTest.this, "红球过滤号超出", Toast.LENGTH_SHORT).show();
            return;
        }

        if (BLUE_BALL_TOTAL - blueBallExcludeList.size() < blueBallLength) {
            Toast.makeText(CustomLotteryTest.this, "篮球过滤号超出", Toast.LENGTH_SHORT).show();
            return;
        }

        //
        while (true) {
            redBallRandom = (int) (Math.random() * RED_BALL_TOTAL + 1);
            if (!redBallExcludeList.contains(redBallRandom) && !redBallList.contains(redBallRandom)) {
                redBallList.add(redBallRandom);
                if (redBallList.size() == redBallLength) {
                    break;
                }
            }
        }

        //
        while (true) {
            buleBallRandom = (int) (Math.random() * BLUE_BALL_TOTAL + 1);
            if (!blueBallExcludeList.contains(buleBallRandom) && !blueBallList.contains(buleBallRandom)) {
                blueBallList.add(buleBallRandom);
                if (blueBallList.size() == blueBallLength) {
                    break;
                }
            }
        }

        //
        Collections.sort(redBallList);
        Collections.sort(blueBallList);

        textRedBallResult.setText("红球:  " + redBallList);
        textBlueBallResult.setText("篮球:  " + blueBallList);

        //
        saveSharePreferencesIntegerLength(getApplication(), SHAREPREFERENCES_REDBALL_LENGTH, redBallLength);
        saveSharePreferencesIntegerLength(getApplication(), SHAREPREFERENCES_BLUEBALL_LENGTH, blueBallLength);

        //
        saveSharePreferencesIntegerList(getApplication(), SHAREPREFERENCES_REDBALL_LIST, redBallExcludeList);
        saveSharePreferencesIntegerList(getApplication(), SHAREPREFERENCES_BLUEBALL_LIST, blueBallExcludeList);

    }

    public static void saveSharePreferencesIntegerLength(Context context, String key, int length) {
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, length);
        editor.commit();
    }

    public static int getSharePreferencesIntegerLength(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        int length = prefs.getInt(key, 0);
        return length;
    }

    public static void saveSharePreferencesIntegerList(Context context, String key, List integerList) {
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < integerList.size(); i++) {
            jsonArray.put(integerList.get(i));
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, jsonArray.toString());
        editor.commit();
    }

    public static List getSharePreferencesIntegerList(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        List integerList = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(prefs.getString(key, "[]"));
            for (int i = 0; i < jsonArray.length(); i++) {
                integerList.add(jsonArray.getInt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return integerList;
    }
}