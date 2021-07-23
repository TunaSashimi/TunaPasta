package com.tunaPasta18.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * <p>自助理赔材料。</p>
 * <p>
 * Created by TunaSashimi on 2017-11-13.
 */
public class IndentFocus implements Parcelable {
    private static final String TYPE_OTHER = "28";
    public String id;
    public String name;
    public String count;
    public String sort;
    public IndentFocus() {
    }
    public boolean isOther() {
        return TYPE_OTHER.equals(id);
    }

    public boolean hasValue() {
        if (isOther()) {
            if (!TextUtils.isEmpty(count)) {
                return true;
            }
        } else {
            if (!TextUtils.isEmpty(count) && !"0".equals(count)) {
                return true;
            }
        }
        return false;
    }

    protected IndentFocus(Parcel in) {
        id = in.readString();
        name = in.readString();
        count = in.readString();
        sort = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(count);
        dest.writeString(sort);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IndentFocus> CREATOR = new Creator<IndentFocus>() {
        @Override
        public IndentFocus createFromParcel(Parcel source) {
            return new IndentFocus(source);
        }

        @Override
        public IndentFocus[] newArray(int size) {
            return new IndentFocus[size];
        }
    };
}
