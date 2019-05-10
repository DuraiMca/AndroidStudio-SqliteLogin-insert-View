package com.urban.admin.firstapp;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.urban.admin.firstapp.Fragments.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login login = new Login();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.MainLayout, login, login.getTag()).commit();
    }
}
