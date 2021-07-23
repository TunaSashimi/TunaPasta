package com.tunaPasta17.activity;

import android.os.Bundle;

import com.tunaPasta17.R;
import com.tunaPasta17.adapter.UserAdapter;
import com.tunaPasta17.model.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DBRecyclerTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_recycler);
        RecyclerView rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        List<UserInfo> userList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            UserInfo user = new UserInfo("user_" + i, String.valueOf(new Random().nextInt() * 4));
            userList.add(user);
        }
        UserAdapter userAdapter = new UserAdapter(userList);
        rvList.setAdapter(userAdapter);
    }
}
