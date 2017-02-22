package com.example.user.syncadaptercars24;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.syncadaptercars24.model.User;
import com.example.user.syncadaptercars24.provider.CarsDatabase;

public class ShowDataFromDB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_from_db);

        TextView textView=(TextView)findViewById(R.id.tvShow);
        CarsDatabase database=new CarsDatabase(this);
        Intent intent=getIntent();

        /*set data from user table by user name*/
            User user = database.getUserName(intent.getStringExtra(MainActivity.userName));
            if (user != null) {
                ///User user = database.getUserName(intent.getStringExtra(MainActivity.userName));
                textView.setText("User info from DataBase  \n\n" + user.getName() + "\n\n" + user.getDetials());

            }

         }
}
