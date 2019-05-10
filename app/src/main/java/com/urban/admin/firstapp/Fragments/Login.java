package com.urban.admin.firstapp.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.urban.admin.firstapp.R;


public class Login extends Fragment {
    private MyHelper mMyHelper;
    private SQLiteDatabase msqlitedb;
    EditText username, password;
    ImageButton login;
TextView register;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.loginform);
        register=view.findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register register = new Register();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.MainLayout, register, register.getTag()).commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyHelper = new MyHelper(getContext(), "employeedata", null, 1);
                msqlitedb = mMyHelper.getWritableDatabase();
                if (mMyHelper.validateUser(username.getText().toString(), password.getText().toString())) {
                    ViewData viewData = new ViewData();
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.MainLayout, viewData, viewData.getTag()).commit();

                } else
                    Toast.makeText(getContext(), "Wrong username/password", Toast.LENGTH_LONG).show();


            }
        });
        return view;
    }
}
