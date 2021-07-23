package com.tunaPasta17.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tunaPasta17.R;
import com.tunaPasta17.util.PermissionUtil;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PermissionUtilTest extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    // 相机权限、多个权限
    private final String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    private final String[] PERMISSION_MORE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
        , Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CALENDAR};

    // 打开相机请求Code，多个权限请求Code
    private final int REQUEST_CODE_CAMERA = 1;
    private final int REQUEST_CODE_PERMISSION_MORE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.permissionutiltest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main_request_one_permission:
                requestPermission();
                break;
            case R.id.btn_main_request_permissions:
                requestMorePermissions();
                break;
            case R.id.btn_main_request_one_permission1:
                requestPermission1();
                break;
            case R.id.btn_main_request_permissions1:
                requestMorePermissions1();
                break;
        }
    }

    // 普通申请一个权限
    private void requestPermission() {
        PermissionUtil.checkAndRequestPermission(mContext, PERMISSION_CAMERA, REQUEST_CODE_CAMERA,
            new PermissionUtil.PermissionRequestSuccessCallBack() {
                @Override
                public void onHasPermission() {
                    // 权限已被授予
                    toCamera();
                }
            });
    }

    // 自定义申请一个权限
    private void requestPermission1() {
        PermissionUtil.checkPermission(mContext, PERMISSION_CAMERA,
            new PermissionUtil.PermissionCheckCallBack() {
                @Override
                public void onHasPermission() {
                    toCamera();
                }

                @Override
                public void onUserHasAlreadyTurnedDown(String... permission) {
                    showExplainDialog(permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PermissionUtil.requestPermission(mContext, PERMISSION_CAMERA, REQUEST_CODE_CAMERA);
                        }
                    });
                }

                @Override
                public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                    PermissionUtil.requestPermission(mContext, PERMISSION_CAMERA, REQUEST_CODE_CAMERA);
                }
            });
    }

    // 普通申请多个权限
    private void requestMorePermissions() {
        PermissionUtil.checkAndRequestMorePermissions(mContext, PERMISSION_MORE, REQUEST_CODE_PERMISSION_MORE,
            new PermissionUtil.PermissionRequestSuccessCallBack() {
                @Override
                public void onHasPermission() {
                    // 权限已被授予
                    toCamera();
                }
            });
    }

    // 自定义申请多个权限
    private void requestMorePermissions1() {
        PermissionUtil.checkMorePermissions(mContext, PERMISSION_MORE, new PermissionUtil.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                toCamera();
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                showExplainDialog(permission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtil.requestMorePermissions(mContext, PERMISSION_MORE, REQUEST_CODE_PERMISSION_MORE);
                    }
                });
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                PermissionUtil.requestMorePermissions(mContext, PERMISSION_MORE, REQUEST_CODE_PERMISSION_MORE);
            }
        });
    }

    private void toCamera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }

    /**
     * 解释权限的dialog
     */
    private void showExplainDialog(String[] permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(mContext)
            .setTitle("申请权限")
            .setMessage("我们需要" + Arrays.toString(permission) + "权限")
            .setPositiveButton("确定", onClickListener)
            .show();
    }

    /**
     * 显示前往应用设置Dialog
     */
    private void showToAppSettingDialog() {
        new AlertDialog.Builder(mContext)
            .setTitle("需要权限")
            .setMessage("我们需要相关权限，才能实现功能，点击前往，将转到应用的设置界面，请开启应用的相关权限。")
            .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionUtil.toAppSetting(mContext);
                }
            })
            .setNegativeButton("取消", null).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (PermissionUtil.isPermissionRequestSuccess(grantResults)) {
                    // 权限申请成功
                    toCamera();
                } else {
                    Toast.makeText(mContext, "打开相机失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_PERMISSION_MORE:
                PermissionUtil.onRequestMorePermissionsResult(mContext, PERMISSION_MORE, new PermissionUtil.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        toCamera();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        Toast.makeText(mContext, "我们需要" + Arrays.toString(permission) + "权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        Toast.makeText(mContext, "我们需要" + Arrays.toString(permission) + "权限", Toast.LENGTH_SHORT).show();
                        showToAppSettingDialog();
                    }
                });
        }
    }
}
