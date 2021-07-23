package com.tunaPasta04.activity;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tunaPasta04.R;
import com.tunaPasta04.adapter.FileAdapter;
import com.tunaPasta04.log.Logger;
import com.tunaPasta04.util.FileComparator;
import com.tunaPasta04.util.FileSearchFilter;
import com.tunaPasta04.util.Util;

public class LocalFileManagerTest extends ListActivity {
    private TextView path;
    private ListView fileList;
    private String root = "/sdcard";
    private ArrayList<String> paths = null;
    private ArrayList<String> items = null;
    private View view;
    private EditText renameText;
    private EditText filenameText;
    private EditText searchText;
    private Logger logger = new Logger(LocalFileManagerTest.class);
    private String oPath = "";
    private String fileName = "";
    private String method = "";
    private ImageButton home_button;
    private ImageButton back_button;
    private ImageButton search_button;
    private ImageButton shutdown_button;
    private ProgressDialog search_dialog;
    ArrayList<File> search_files;
    private Context context;
    private int fileCounts = 0;
    private int m_count = 0;
    private static final String SEARCH_RESULT = "/search_results";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        context = this;

        setContentView(R.layout.localfilemanagertest);

        path = findViewById(R.id.file_textview01);
        fileList = findViewById(android.R.id.list);

        home_button = findViewById(R.id.file_home_button);
        back_button = findViewById(R.id.file_back_button);
        search_button = findViewById(R.id.file_search_button);
        shutdown_button = findViewById(R.id.file_shutdown_button);

        home_button.setImageBitmap(Util.resizeBitmap(Util.drawableToBitmap(getResources().getDrawable(R.drawable.home)), 32, 32));
        back_button.setImageBitmap(Util.resizeBitmap(Util.drawableToBitmap(getResources().getDrawable(R.drawable.back)), 32, 32));
        search_button.setImageBitmap(Util.resizeBitmap(Util.drawableToBitmap(getResources().getDrawable(R.drawable.search)), 32, 32));
        shutdown_button.setImageBitmap(Util.resizeBitmap(Util.drawableToBitmap(getResources().getDrawable(R.drawable.shutdown)), 32, 32));

        getFileDir(root);

        fileList.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                final File file = new File(paths.get(position));

                new AlertDialog.Builder(LocalFileManagerTest.this)
                    .setTitle(R.string.operate)
                    .setItems(R.array.file_oper_menu, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                if (file.isDirectory()) {
                                    getFileDir(file.getPath());
                                } else {
                                    openFile(file);
                                }
                            } else if (which == 1) {
                                rename(file);
                            } else if (which == 2) {
                                deleteFile(file);
                            } else if (which == 3) {
                                copy(file);
                            } else if (which == 4) {
                                cut(file);
                            } else if (which == 5) {
                                viewDetails(file);
                            }
                        }
                    }).setPositiveButton(R.string.cancel, null).show();

                return true;
            }
        });

        home_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                getFileDir(root);
            }
        });

        back_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                String nowPath = path.getText().toString();
                if (!nowPath.equals(root))
                    nowPath = nowPath.substring(0, nowPath.lastIndexOf("/"));
                getFileDir(nowPath);
            }
        });

        search_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                search();
            }
        });

        shutdown_button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                shutDown();
            }
        });
    }

    public void getFileDir(String filePath) {
        File file = new File(filePath);
        paths = new ArrayList();
        items = new ArrayList();
        path.setText(filePath);
        ArrayList dirList = new ArrayList();
        ArrayList fileList = new ArrayList();

        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                dirList.add(f);
            } else {
                fileList.add(f);
            }
        }

        Comparator comp = new FileComparator();
        Collections.sort(dirList, comp);
        Collections.sort(fileList, comp);
        ArrayList list = Util.combineArrayList(dirList, fileList);

        for (int i = 0; i < list.size(); i++) {
            File f = (File) list.get(i);
            items.add(f.getName());
            paths.add(f.getPath());
        }
        setListAdapter(new FileAdapter(this, items, paths));
    }

    public void setFileList(ArrayList<File> files, String filePath) {
        path.setText(filePath);
        ArrayList dirList = new ArrayList<String>();
        ArrayList fileList = new ArrayList<String>();
        paths = new ArrayList();
        items = new ArrayList();

        for (File f : files) {
            if (f.isDirectory()) {
                dirList.add(f);
            } else {
                fileList.add(f);
            }
        }

        Comparator comp = new FileComparator();

        Collections.sort(dirList, comp);
        Collections.sort(fileList, comp);

        ArrayList list = Util.combineArrayList(dirList, fileList);

        for (int i = 0; i < list.size(); i++) {
            File f = (File) list.get(i);
            items.add(f.getName());
            paths.add(f.getPath());
        }

        setListAdapter(new FileAdapter(context, items, paths));
    }

    protected void onListItemClick(ListView lv, View v, int position, long id) {
        File file = new File(paths.get(position));
        if (!file.canRead()) {
            new AlertDialog.Builder(this)
                .setTitle(R.string.warn)
                .setMessage(R.string.file_is_cannot_read)
                .setPositiveButton("OK", null).show();
            return;
        }
        if (file.isDirectory()) {
            getFileDir(paths.get(position));
        } else {
            openFile(file);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, Menu.FIRST, 0, R.string.create_new_foler);
        menu.add(Menu.NONE, Menu.FIRST + 1, 0, R.string.create_new_file);
        menu.add(Menu.NONE, Menu.FIRST + 2, 0, R.string.paste);
        menu.add(Menu.NONE, Menu.FIRST + 3, 0, R.string.refresh);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                createNewFolder();
                break;
            case Menu.FIRST + 1:
                createNewFile();
            case Menu.FIRST + 2:
                paste();
                break;
            case Menu.FIRST + 3:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFile(File f) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = Util.getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        startActivity(intent);
    }

    public void rename(final File file) {
        LayoutInflater factory = LayoutInflater.from(LocalFileManagerTest.this);
        view = factory.inflate(R.layout.localfilemanagertestrename, null);
        renameText = view.findViewById(R.id.rename_edittext);
        renameText.setText(file.getName());
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LocalFileManagerTest.this).setTitle(R.string.rename).setView(view);
        alertDialog.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String modName = renameText.getText().toString();
                final String pFile = file.getParentFile().getPath() + "/";
                final String newPath = pFile + modName;

                if (!modName.equals(file.getName())) {
                    if (new File(newPath).exists()) {
                        new AlertDialog.Builder(LocalFileManagerTest.this).setTitle(R.string.warn)
                            .setMessage(R.string.filename_is_exists).setPositiveButton(R.string.sure,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    file.renameTo(new File(newPath));
                                    getFileDir(pFile);
                                }
                            }).setNegativeButton(R.string.cancel, null).show();
                    } else {
                        file.renameTo(new File(newPath));
                        getFileDir(pFile);
                    }
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, null);
        alertDialog.show();
    }

    public void deleteFile(final File file) {
        if (file.isDirectory() && file.listFiles().length > 0) {
            Toast.makeText(LocalFileManagerTest.this, getResources().getString(R.string.please_del_child_files), Toast.LENGTH_LONG).show();
            return;
        }
        new AlertDialog.Builder(LocalFileManagerTest.this)
            .setTitle(getResources().getString(R.string.delete) + " " + file.getName())
            .setMessage(R.string.confirm_del_file)
            .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    file.delete();
                    getFileDir(file.getParent());
                    Toast.makeText(LocalFileManagerTest.this, file.getName() + " " + getResources().getString(R.string.delete_success), Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton(R.string.cancel, null).show();
    }

    public void copy(File file) {
        oPath = file.getPath();
        fileName = file.getName();
        method = "copy";
    }

    public void cut(File file) {
        oPath = file.getPath();
        fileName = file.getName();
        method = "cut";
    }

    public void paste() {
        if (!oPath.equals("")) {
            String newPath = path.getText().toString() + "/" + fileName;
            try {
                if (method.equals("copy"))
                    Runtime.getRuntime().exec("cp " + oPath + " " + newPath);
                else if (method.equals("cut"))
                    Runtime.getRuntime().exec("mv " + oPath + " " + newPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            oPath = "";
            fileName = "";
            method = "";
            refresh();
            refresh();
        }
    }

    public void createNewFolder() {
        LayoutInflater factory = LayoutInflater.from(LocalFileManagerTest.this);
        view = factory.inflate(R.layout.create_new_file, null);
        filenameText = (EditText) view.findViewById(R.id.create_filename);
        filenameText.setText(R.string.create_new_foler);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LocalFileManagerTest.this)
            .setTitle(R.string.input_folder_name)
            .setView(view);
        alertDialog.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,
                                int which) {
                String folderName = filenameText.getText().toString();
                final String pFile = path.getText().toString() + "/";
                final String newPath = pFile + folderName;

                if (new File(newPath).exists()) {
                    Toast.makeText(LocalFileManagerTest.this,
                        getResources().getString(R.string.foldername_is_exists),
                        Toast.LENGTH_LONG).show();
                } else {
                    File file = new File(newPath);
                    file.mkdirs();
                    getFileDir(pFile);
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
            }
        });
        alertDialog.show();
    }

    public void createNewFile() {
        LayoutInflater factory = LayoutInflater.from(LocalFileManagerTest.this);
        view = factory.inflate(R.layout.create_new_file, null);
        filenameText = view.findViewById(R.id.create_filename);
        filenameText.setText(R.string.create_new_file);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LocalFileManagerTest.this)
            .setTitle(R.string.input_file_name)
            .setView(view);
        alertDialog.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,
                                int which) {
                String folderName = filenameText.getText().toString();
                final String pFile = path.getText().toString() + "/";
                final String newPath = pFile + folderName;

                if (new File(newPath).exists()) {
                    Toast.makeText(LocalFileManagerTest.this, getResources().getString(R.string.filename_is_exists_toast), Toast.LENGTH_LONG).show();
                } else {
                    File file = new File(newPath);
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getFileDir(pFile);
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
            }
        });
        alertDialog.show();
    }

    public void refresh() {
        String nPath = path.getText().toString();
        getFileDir(nPath);
    }

    public void viewDetails(File file) {
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.file_details_layout, null);
        ImageView file_icon_view = view.findViewById(R.id.attr_file_icon);
        TextView file_name_text = view.findViewById(R.id.attr_file_name);
        TextView file_type_text = view.findViewById(R.id.file_type);
        TextView file_location_text = view.findViewById(R.id.file_location);
        TextView file_size_text = view.findViewById(R.id.file_size);
        TextView file_modtime_text = view.findViewById(R.id.file_mod_time);
        TextView file_isread_text = view.findViewById(R.id.file_is_read);
        TextView file_iswrite_text = view.findViewById(R.id.file_is_write);
        TextView file_ishidden_text = view.findViewById(R.id.file_is_hidden);
        file_icon_view.setImageBitmap(Util.getFileIcon(this, file));
        file_name_text.setText(file.getName());
        String file_type = file.isDirectory() ? getResources().getString(R.string.folder) : getResources().getString(R.string.file);
        file_type_text.setText(file_type);
        file_location_text.setText(file.getParentFile().getPath());
        file_size_text.setText(Util.convertFileSize(file.length()));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(file.lastModified());
        file_modtime_text.setText(Util.getDateString(cal.getTime()));
        file_isread_text.setText(file.canRead() ? getResources().getString(R.string.yes) : getResources().getString(R.string.no));
        file_iswrite_text.setText(file.canWrite() ? getResources().getString(R.string.yes) : getResources().getString(R.string.no));
        file_ishidden_text.setText(file.isHidden() ? getResources().getString(R.string.yes) : getResources().getString(R.string.no));

        new AlertDialog.Builder(LocalFileManagerTest.this).setTitle(R.string.file_attr).setView(view).setPositiveButton(R.string.sure, null).show();
    }

    public void shutDown() {
        new AlertDialog.Builder(LocalFileManagerTest.this).setTitle(R.string.warn).setMessage(R.string.confirm_exit)
            .setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setNegativeButton(R.string.cancel, null).show();
    }

    public void search() {
        LayoutInflater factory = LayoutInflater.from(LocalFileManagerTest.this);
        view = factory.inflate(R.layout.localfilemanagertestsearch, null);
        searchText = view.findViewById(R.id.file_search_text);
        TextView search_path = view.findViewById(R.id.file_search_path);
        String temp = path.getText().toString();
        if (temp.endsWith(SEARCH_RESULT))
            temp = temp.substring(0, temp.lastIndexOf("/"));
        final String pPath = temp;
        search_path.setText(pPath);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LocalFileManagerTest.this).setTitle(R.string.search).setView(view);
        alertDialog.setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                final String text = searchText.getText().toString();
                if (!text.equals("")) {
                    search_files = new ArrayList();
                    m_count = 0;
                    search_dialog = new ProgressDialog(LocalFileManagerTest.this);
                    search_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    search_dialog.setTitle(R.string.search);
                    search_dialog.setIcon(R.drawable.search);
                    search_dialog.setMessage(getResources().getString(R.string.searching_file));
                    search_dialog.setIndeterminate(false);
                    search_dialog.setCancelable(true);
                    search_dialog.setProgress(100);
                    search_dialog.setButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    search_dialog.show();

                    new Thread(new Runnable() {
                        public void run() {
                            fileCounts = 0;
                            getFileCounts(pPath);
                            search_files = searchDir(pPath, text, search_files);
                            Message msg = new Message();
                            msg.what = 108;
                            LocalFileManagerTest.this.msgHandler.sendMessage(msg);
                            search_dialog.cancel();
                        }
                    }).start();
                } else {
                    toast(R.string.please_input_keyword);
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, null);
        alertDialog.show();
    }

    Handler msgHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 108) {
                if (search_files.size() > 0) {
                    String pPath = path.getText().toString();
                    if (!pPath.endsWith(SEARCH_RESULT))
                        pPath += SEARCH_RESULT;
                    setFileList(search_files, pPath);
                    toast(R.string.search_finished);
                } else {
                    toast(R.string.search_not_found);
                }
            }
        }
    };

    public ArrayList searchDir(String pPath, String text, ArrayList<File> fileList) {
        File file = new File(pPath);
        File[] files = file.listFiles(new FileSearchFilter(text));
        if (files != null && files.length > 0)
            fileList = Util.combineArrayList(fileList, Util.arrayToList(files));
        File[] allFiles = file.listFiles();

        m_count += allFiles == null ? 0 : allFiles.length;//allFiles有可能为空,所以做三目修改!
//		m_count += allFiles.length;

        DecimalFormat format = new DecimalFormat("0.00");
        String result = format.format((float) m_count / fileCounts);
        int per = (int) (Float.parseFloat(result) * 100);
        logger.debug(per + "  " + m_count + "  " + fileCounts + "  " + (float) m_count / fileCounts + "   " + per);
        search_dialog.setProgress(per);
        if (allFiles != null && allFiles.length > 0) {
            for (File f : allFiles) {
                if (f.isDirectory())
                    fileList = searchDir(f.getPath(), text, fileList);
            }
        }
        return fileList;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (path.getText().toString().equals(root)) {
                shutDown();
            } else {
                String tPath = path.getText().toString();
                String pPath = tPath.substring(0, tPath.lastIndexOf("/"));
                getFileDir(pPath);
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void toast(int resId) {
        Toast.makeText(LocalFileManagerTest.this, resId, Toast.LENGTH_LONG).show();
    }

    public void toast(String mess) {
        Toast.makeText(LocalFileManagerTest.this, mess, Toast.LENGTH_LONG).show();
    }

    public int getFileCounts(String path) {

        File file = new File(path);
        File[] files = file.listFiles();

        fileCounts += files == null ? 0 : files.length;//files有可能为空,所以做三目修改!

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    getFileCounts(f.getPath());
                }
            }
        }
        return 0;
    }
}