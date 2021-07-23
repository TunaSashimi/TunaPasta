package com.tunaPasta06.tool;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import com.tunaPasta06.R;

/**
 * A class for annotating a CharSequence with spans to convert textual emoticons
 * to graphical ones.
 */
public class SmileyParser {
    private static SmileyParser sInstance;

    public static SmileyParser getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        sInstance = new SmileyParser(context);
    }

    private final Context mContext;
    private final String[] mSmileyTexts;
    private final Pattern mPattern;
    private final HashMap<String, Integer> mSmileyToRes;

    private SmileyParser(Context context) {
        mContext = context;
        mSmileyTexts = mContext.getResources().getStringArray(DEFAULT_SMILEY_TEXTS);
        mSmileyToRes = buildSmileyToRes();
        mPattern = buildPattern();
    }

    static class Smileys {
        //表情图片集合
        private static final int[] sIconIds = {
                R.drawable.smileparser_aini,
                R.drawable.smileparser_aoteman,
                R.drawable.smileparser_baibai,
                R.drawable.smileparser_baobao,
                R.drawable.smileparser_beiju,
                R.drawable.smileparser_beishang,
                R.drawable.smileparser_bianbian,
                R.drawable.smileparser_bishi,
                R.drawable.smileparser_bizui,
                R.drawable.smileparser_buyao,
                R.drawable.smileparser_chanzui,
        };
        //将图片映射为 文字
        public static int aini = 0;
        public static int aoteman = 1;
        public static int baibai = 2;
        public static int baobao = 3;
        public static int beiju = 4;
        public static int beishang = 5;
        public static int bianbian = 6;
        public static int bishi = 7;
        public static int bizui = 8;
        public static int buyao = 9;
        public static int chanzui = 10;

        //得到图片表情 根据id
        public static int getSmileyResource(int which) {
            return sIconIds[which];
        }
    }

    // NOTE: if you change anything about this array, you must make the corresponding change
    // to the string arrays: default_smiley_texts and default_smiley_names in res/values/arrays.xml
    public static final int[] DEFAULT_SMILEY_RES_IDS = {
            Smileys.getSmileyResource(Smileys.aini), // 0
            Smileys.getSmileyResource(Smileys.aoteman), // 1
            Smileys.getSmileyResource(Smileys.baibai), // 2
            Smileys.getSmileyResource(Smileys.baobao), // 3
            Smileys.getSmileyResource(Smileys.beiju), // 4
            Smileys.getSmileyResource(Smileys.beishang), // 5
            Smileys.getSmileyResource(Smileys.bianbian), // 6
            Smileys.getSmileyResource(Smileys.bishi), // 7
            Smileys.getSmileyResource(Smileys.bizui), // 8
            Smileys.getSmileyResource(Smileys.buyao), // 9
            Smileys.getSmileyResource(Smileys.chanzui), // 10
    };

    public static final int DEFAULT_SMILEY_TEXTS = R.array.default_smiley_texts;
    public static final int DEFAULT_SMILEY_NAMES = R.array.default_smiley_names;

    /**
     * Builds the hashtable we use for mapping the string version
     * of a smiley (e.g. ":-)") to a resource ID for the icon version.
     */
    private HashMap<String, Integer> buildSmileyToRes() {
        if (DEFAULT_SMILEY_RES_IDS.length != mSmileyTexts.length) {
            // Throw an exception if someone updated DEFAULT_SMILEY_RES_IDS

            // and failed to update arrays.xml

            throw new IllegalStateException("Smiley resource ID/text mismatch");
        }

        HashMap<String, Integer> smileyToRes = new HashMap(mSmileyTexts.length);
        for (int i = 0; i < mSmileyTexts.length; i++) {
            smileyToRes.put(mSmileyTexts[i], DEFAULT_SMILEY_RES_IDS[i]);
        }

        return smileyToRes;
    }


    /**
     * Builds the regular expression we use to find smileys in {@link #addSmileySpans}.
     */
    //构建正则表达式
    private Pattern buildPattern() {
        // Set the StringBuilder capacity with the assumption that the average

        // smiley is 3 characters long.

        StringBuilder patternString = new StringBuilder(mSmileyTexts.length * 3);

        // Build a regex that looks like (:-)|:-(|...), but escaping the smilies

        // properly so they will be interpreted literally by the regex matcher.

        patternString.append('(');
        for (String s : mSmileyTexts) {
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }
        patternString.replace(patternString.length() - 1, patternString.length(), ")");

        return Pattern.compile(patternString.toString());
    }


    /**
     * Adds ImageSpans to a CharSequence that replace textual emoticons such
     * as :-) with a graphical version.
     *
     * @param text A CharSequence possibly containing emoticons
     * @return A CharSequence annotated with ImageSpans covering any
     * recognized emoticons.
     */
    //根据文本替换成图片
    public CharSequence addSmileySpans(CharSequence text) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        Matcher matcher = mPattern.matcher(text);
        while (matcher.find()) {
            int resId = mSmileyToRes.get(matcher.group());
            builder.setSpan(new ImageSpan(mContext, resId),
                    matcher.start(), matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}



