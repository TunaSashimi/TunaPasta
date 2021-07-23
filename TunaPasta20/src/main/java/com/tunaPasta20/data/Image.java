package com.tunaPasta20.data;


import androidx.databinding.ObservableField;

public class Image {

    private ObservableField<String> url;

    public Image(String url) {
        this.url = new ObservableField<>(url);
    }

    public ObservableField<String> getUrl() {
        return url;
    }

    public void setUrl(ObservableField<String> url) {
        this.url = url;
    }

}