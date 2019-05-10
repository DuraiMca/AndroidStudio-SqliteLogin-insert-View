package com.urban.admin.firstapp.Fragments;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.urban.admin.firstapp.R;

public class Register extends Fragment {
    EditText name, mobile, email;
    Button submit, viewdata;
    private MyHelper mMyHelper;
    private SQLiteDatabase msqlitedb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        name = view.findViewById(R.id.name);

        mobile = view.findViewById(R.id.mobile);
        email = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.register);

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("confirm");
                builder.setMessage("Are you sure want save?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isvalid()) {
                            SQliteDatabase_Insert();


                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        viewdata = view.findViewById(R.id.viewdata);
        viewdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.MainLayout, login, login.getTag()).commit();

            }
        });
        return view;
    }

    private void SQliteDatabase_Insert() {

        mMyHelper = new MyHelper(getContext(), "employeedata", null, 1);
        msqlitedb = mMyHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name.getText().toString());
        contentValues.put("mobile", mobile.getText().toString());
        contentValues.put("email", email.getText().toString());
        long id = msqlitedb.insert("employee", null, contentValues);
        Toast.makeText(getContext(), "Successfully Stored", Toast.LENGTH_SHORT).show();
        name.setText("");
        mobile.setText("");
        email.setText("");
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public boolean isvalid() {
        boolean isValid = true;

        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("");
            isValid = false;
        }
        if (TextUtils.isEmpty(mobile.getText().toString())) {
            isValid = false;
            mobile.setError("");
        }

        if (TextUtils.isEmpty(email.getText().toString())) {
            isValid = false;
            email.setError("");
        }

        if (isValidEmail(email.getText().toString())) {

        } else {
            isValid = false;
            email.setError("");
        }
        return isValid;
    }

}
