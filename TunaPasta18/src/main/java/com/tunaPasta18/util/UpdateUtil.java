package com.tunaPasta18.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.tunaPasta18.R;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateUtil extends AsyncTask<String, String, String> {
    private Context context;
    private boolean isUpdateOnRelease;
    public static final String updateUrl = "https://api.github.com/repos/geeeeeeeeek/WeChatLuckyMoney/releases/latest";

    public UpdateUtil(Context context, boolean needUpdate) {
        this.context = context;
        this.isUpdateOnRelease = needUpdate;
        if (this.isUpdateOnRelease) Toast.makeText(context, "正在检查新版本……", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == 200) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else {
                // Close the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (Exception e) {
            return null;
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject release = new JSONObject(result);

            // Get current VERSION
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;

            String latestVersion = release.getString("tag_name");
            boolean isPreRelease = release.getBoolean("prerelease");
            if (!isPreRelease && version.compareToIgnoreCase(latestVersion) >= 0) {
                // Your VERSION is ahead of or same as the latest.
                if (this.isUpdateOnRelease)
                    Toast.makeText(context, R.string.update_already_latest, Toast.LENGTH_SHORT).show();
            } else {
                if (!isUpdateOnRelease) {
                    Toast.makeText(context, context.getString(R.string.update_new_seg1) + latestVersion + context.getString(R.string.update_new_seg3), Toast.LENGTH_LONG).show();
                    return;
                }
                // Need update.
                String downloadUrl = release.getJSONArray("assets").getJSONObject(0).getString("browser_download_url");

                // Give up on the fucking DownloadManager. The downloaded apk got renamed and unable to install. Fuck.
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
                Toast.makeText(context, context.getString(R.string.update_new_seg1) + latestVersion + context.getString(R.string.update_new_seg2), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.isUpdateOnRelease) Toast.makeText(context, R.string.update_error, Toast.LENGTH_LONG).show();
        }
    }

    public void update() {
        super.execute(updateUrl);
    }
}