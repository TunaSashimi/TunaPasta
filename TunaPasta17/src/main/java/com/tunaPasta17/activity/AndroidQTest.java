package com.tunaPasta17.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tunaPasta17.BuildConfig;
import com.tunaPasta17.R;
import com.tunaPasta17.model.AudioInfo;
import com.tunaPasta17.model.ImageInfo;
import com.tunaPasta17.model.MediaInfo;
import com.tunaPasta17.model.VideoInfo;
import com.tunaPasta17.util.PermissionUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import static androidx.core.content.FileProvider.getUriForFile;


public class AndroidQTest extends Activity {
    private static final int REQUEST_SHARE_FILE = 37;

    private static final int READ_REQUEST_CODE = 42;
    private static final int WRITE_REQUEST_CODE = 43;
    private static final int EDIT_REQUEST_CODE = 44;
    private static final int REQUEST_TAKE_PICTURE = 45;
    private static final int REQUEST_PICK = 46;

    private ImageView image01;
    private Bitmap bitmap;

    //
    private final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final int REQUEST_READ_EXTERNAL_STORAGE = 1;

    //
    private final String[] PERMISSION_MORE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
        , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private final int REQUEST_PERMISSION_MORE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.androidqtest);

        image01 = findViewById(R.id.image01);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tunasashimi);
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.button01:
                accessAppDirectory(this);
                break;
            case R.id.button02:
                performFileSearch();
                break;
            case R.id.button03:
                //MediaStore
                PermissionUtil.checkAndRequestPermission(this, READ_EXTERNAL_STORAGE, REQUEST_READ_EXTERNAL_STORAGE,
                    new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            List videoList = getVideoList();
                            if (videoList != null && videoList.size() > 0) {
                                Toast.makeText(AndroidQTest.this, "videoList??????==>" + videoList.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AndroidQTest.this, "videoList??????", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                break;
            case R.id.button04:
                //MediaStore
                PermissionUtil.checkAndRequestPermission(this, READ_EXTERNAL_STORAGE, REQUEST_READ_EXTERNAL_STORAGE,
                    new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            List imageList = getImageList();
                            if (imageList != null && imageList.size() > 0) {
                                Toast.makeText(AndroidQTest.this, "imageList??????==>" + imageList.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AndroidQTest.this, "imageList??????", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                break;
            case R.id.button05:
                //MediaStore
                PermissionUtil.checkAndRequestPermission(this, READ_EXTERNAL_STORAGE, REQUEST_READ_EXTERNAL_STORAGE,
                    new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            List audioList = getAudioList();
                            if (audioList != null && audioList.size() > 0) {
                                Toast.makeText(AndroidQTest.this, "audioList==>" + audioList.size(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AndroidQTest.this, "audioList??????", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                break;
            case R.id.button06:
                //????????????
                createFile("image/png", "picture.png");
                break;
            case R.id.button07:
                //????????????
                editDocument();
                break;
            case R.id.button08:
                //????????????
                Uri uriDelete = null;
                deleteDocument(uriDelete);
                break;
            case R.id.button09:
                //????????????
                shareFile();
                break;
            case R.id.button10:
                //????????????
                installFile();
                break;
            case R.id.button11:
                //????????????
                PermissionUtil.checkAndRequestMorePermissions(this, PERMISSION_MORE, REQUEST_PERMISSION_MORE,
                    new PermissionUtil.PermissionRequestSuccessCallBack() {
                        @Override
                        public void onHasPermission() {
                            openCamera();
                        }
                    });
                break;
            case R.id.button12:
                //????????????
                openCrop();
                break;
            case R.id.button13:
                //????????????ID
                getDeviceID();
                break;
            default:
                break;
        }
    }

    //??????????????????????????????,???????????? ??????????????????,getExternalFilesDir()
    public void accessAppDirectory(Context context) {
        Toast.makeText(AndroidQTest.this, "accessAppDirectory", Toast.LENGTH_LONG).show();
        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//        File file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File file = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//        File file = context.getExternalFilesDir(Environment.DIRECTORY_SCREENSHOTS);
    }

    //?????????????????????????????????????????????,???????????? ???,?????????????????? (SAF)
    public void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //????????????????????????????????????????????????????????????????????????????????????????????????????????????:
    // <uses-permission android:name="android.permission.READ_MEDIA_AUDIO" />
    // <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
    // <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />

    //????????????
    public List<MediaInfo> getVideoList() {
        List<MediaInfo> list = new ArrayList();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        //MediaStore.Video.Media.MIME_TYPE + "=? or " + MediaStore.Video.Media.MIME_TYPE + "=?";
        String[] selectionArgs = null;
        //new String[]{"video/mp4", "video/avi"};
        String sortOrder = null;
        //MediaStore.Video.Media.DEFAULT_SORT_ORDER;
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        Cursor thumbCursor = null;
        while (cursor.moveToNext()) {
            MediaInfo videoInfo = new VideoInfo();
            videoInfo.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
            videoInfo.filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
            videoInfo.mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
            videoInfo.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
            videoInfo.addTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED));
            ((VideoInfo) videoInfo).duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
            thumbCursor = contentResolver.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                new String[]{
                    MediaStore.Video.Thumbnails.DATA,
                    MediaStore.Video.Thumbnails.VIDEO_ID
                },
                MediaStore.Video.Thumbnails.VIDEO_ID + "=?",
                new String[]{String.valueOf(videoInfo.id)}, null);

            if (thumbCursor.moveToFirst()) {
                ((VideoInfo) videoInfo).thumbnail = thumbCursor.getString(
                    thumbCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
            }
            list.add(videoInfo);
        }
        thumbCursor.close();
        cursor.close();
        return list;
    }

    //????????????
    public List<MediaInfo> getImageList() {
        List<MediaInfo> list = new ArrayList();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        Cursor thumbCursor = null;
        while (cursor.moveToNext()) {
            MediaInfo imageInfo = new ImageInfo();
            imageInfo.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
            imageInfo.filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            imageInfo.mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE));
            imageInfo.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE));
            imageInfo.addTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
            thumbCursor = contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                new String[]{
                    MediaStore.Images.Thumbnails.DATA,
                    MediaStore.Images.Thumbnails.IMAGE_ID
                },
                MediaStore.Images.Thumbnails.IMAGE_ID + "=?",
                new String[]{String.valueOf(imageInfo.id)}, null);

            if (thumbCursor.moveToFirst()) {
                ((ImageInfo) imageInfo).thumbnail = thumbCursor.getString(
                    thumbCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
            }
            list.add(imageInfo);
        }
        thumbCursor.close();
        cursor.close();
        return list;
    }

    //????????????
    public List<MediaInfo> getAudioList() {
        List<MediaInfo> list = new ArrayList();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
        while (cursor.moveToNext()) {
            MediaInfo audioInfo = new AudioInfo();
            audioInfo.id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            audioInfo.filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            audioInfo.mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE));
            audioInfo.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            audioInfo.addTime = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED));
            list.add(audioInfo);
        }
        cursor.close();
        return list;
    }

    private void createFile(String mimeType, String fileName) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, WRITE_REQUEST_CODE);
    }

    private void editDocument() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }

    private void deleteDocument(Uri uri) {
        try {
            DocumentsContract.deleteDocument(getContentResolver(), uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    // ???Q???????????????(TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId()
    // ????????????ID??????????????????(targetSDK<=P)????????????(targetSDK==Q)?????????????????????READ_PRIVILEGED_PHONE_STATE????????????????????????app????????????????????????????????????
    private void getDeviceID() {
        //?????????????????????????????????ID???????????????????????????????????????ID????????????????????????????????????????????????
//        String uniqueID = UUID.randomUUID().toString();
//        Toast.makeText(this, uniqueID, Toast.LENGTH_SHORT).show();

        //???????????????
        String deviceIDShort = "35" + Build.BOARD.length() % 10
            + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10
            + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10
            + Build.HOST.length() % 10 + Build.ID.length() % 10
            + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
            + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10
            + Build.TYPE.length() % 10 + Build.USER.length() % 10;// 13 ???

//        System.out.println("deviceIDShort==>" + deviceIDShort);

        String serial = "serial";// ??????serial???????????????
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(AndroidQTest.this,
                    Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    // ?????? Android Q ???????????????????????????????????????
                    // android.os.Build.getSerial() ?????? unknown,
                    // ?????? deviceIDShort ??????????????????????????????????????????????????????UUID ???????????????????????????
                    serial = android.os.Build.getSerial();// Android Q ????????? unknown
                }
            } else {
                serial = Build.SERIAL;
            }
        } catch (Exception ignored) {
        }
        String uniqueID = new UUID(deviceIDShort.hashCode(), serial.hashCode()).toString();
//        System.out.println("uniqueID==>" + uniqueID);
    }

    private void shareFile() {
        File filePath = new File(getFilesDir(), "images");
        File file = new File(filePath, "share.jpg");
        Uri contentUri = getUriForFile(this, BuildConfig.APPLICATION_ID + ".FileProvider", file);

        if (contentUri != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra("xxx", contentUri);
            startActivityForResult(intent, REQUEST_SHARE_FILE);
        }
    }

    private void installFile() {
        File apkFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "app_sample.apk");
        Uri apkUri = getUriForFile(this, BuildConfig.APPLICATION_ID + ".FileProvider", apkFile);
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        startActivity(installIntent);
    }

    private void openCamera() {
        File filePath = new File(getFilesDir(), "images");
        File file = new File(filePath, "image.jpg");
        // /data/data/com.tunaPasta19/files/myimages/image.jpg

        Uri contentUri = getUriForFile(this, BuildConfig.APPLICATION_ID + ".FileProvider", file);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent, REQUEST_TAKE_PICTURE);
    }

    private void openCrop() {
        String string = null;
        Uri inputUri = getImageContentUri(string);
        File outputFile = null;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(inputUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
        startActivityForResult(intent, REQUEST_PICK);
    }

    private Uri getImageContentUri(String path) {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=? ", new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, path);
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);

        System.out.println("resultCode==>" + resultCode);
        System.out.println("requestCode==>" + requestCode);

        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case READ_REQUEST_CODE:
                    Uri uriRead;
                    if (resultData != null) {
                        uriRead = resultData.getData();
                        image01.setImageBitmap(getBitmapFromUri(uriRead));
                    }
                    break;
                case REQUEST_TAKE_PICTURE:
                    Uri uriTake;
                    if (resultData != null) {
                        uriTake = resultData.getData();
                        image01.setImageBitmap(getBitmapFromUri(uriTake));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * ???????????????dialog
     */
    private void showExplainDialog(String[] permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(AndroidQTest.this)
            .setTitle("????????????")
            .setMessage("????????????" + Arrays.toString(permission) + "??????")
            .setPositiveButton("??????", onClickListener)
            .show();
    }

    /**
     * ????????????????????????Dialog
     */
    private void showToAppSettingDialog() {
        new AlertDialog.Builder(AndroidQTest.this)
            .setTitle("????????????")
            .setMessage("?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
            .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionUtil.toAppSetting(AndroidQTest.this);
                }
            })
            .setNegativeButton("??????", null).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:
                if (PermissionUtil.isPermissionRequestSuccess(grantResults)) {
                    Toast.makeText(AndroidQTest.this, "?????????", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AndroidQTest.this, "????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_PERMISSION_MORE:
                PermissionUtil.onRequestMorePermissionsResult(AndroidQTest.this, PERMISSION_MORE, new PermissionUtil.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        openCamera();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        Toast.makeText(AndroidQTest.this, "????????????" + Arrays.toString(permission) + "??????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        Toast.makeText(AndroidQTest.this, "????????????" + Arrays.toString(permission) + "??????", Toast.LENGTH_SHORT).show();
                        showToAppSettingDialog();
                    }
                });
                break;
        }
    }
}
