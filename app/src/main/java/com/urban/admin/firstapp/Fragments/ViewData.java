package com.urban.admin.firstapp.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.urban.admin.firstapp.Adapter.myAdapter;
import com.urban.admin.firstapp.Model.UserData;
import com.urban.admin.firstapp.R;

import java.util.ArrayList;
import java.util.List;


public class ViewData extends Fragment {

    private MyHelper mMyHelper;
    private SQLiteDatabase msqlitedb;
    RecyclerView.Adapter adapter;
    List<UserData> list = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_data, container, false);
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recycler);
        mMyHelper = new MyHelper(getContext(), "employeedata", null, 1);
        msqlitedb = mMyHelper.getWritableDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Cursor res = mMyHelper.getdata();
        if (res.getCount() == 0) {
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }
        while (res.moveToNext()) {
            String id = res.getString(0);
            Log.i("id", "" + res.getString(0));
            String name = res.getString(1);
            try {

                Long mobile = Long.valueOf(res.getString(2));

                String email = res.getString(3);

                UserData userData = new UserData(id, name, mobile, email);

                list.add(userData);
            } catch (NumberFormatException e) {

            }
        }
        adapter = new myAdapter(list, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Login login = new Login();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.MainLayout, login, login.getTag()).commit();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
