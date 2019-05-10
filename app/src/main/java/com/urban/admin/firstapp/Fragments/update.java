package com.urban.admin.firstapp.Fragments;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.urban.admin.firstapp.R;


public class update extends Fragment {


    EditText name, mobile, email;
    Button update, logout;
    private MyHelper mMyHelper;
    private SQLiteDatabase msqlitedb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update, container, false);
        Bundle bundle = getArguments();

        name = (EditText) view.findViewById(R.id.ename);
        mobile = (EditText) view.findViewById(R.id.emobile);
        email = (EditText) view.findViewById(R.id.eemail);
        update = (Button) view.findViewById(R.id.eupdate);
        final String id = bundle.getString("id");
        name.setText(bundle.getString("Name"));
        logout = view.findViewById(R.id.back);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewData viewData = new ViewData();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.MainLayout, viewData, viewData.getTag()).commit();
            }
        });
        mobile.setText(bundle.getString("Mobile"));
        email.setText(bundle.getString("Email"));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyHelper = new MyHelper(getContext(), "employeedata", null, 1);
                msqlitedb = mMyHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("name", name.getText().toString());
                cv.put("mobile", mobile.getText().toString());
                cv.put("email", email.getText().toString());
                long id1 = msqlitedb.update("employee", cv, "id=" + id, null);

                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}