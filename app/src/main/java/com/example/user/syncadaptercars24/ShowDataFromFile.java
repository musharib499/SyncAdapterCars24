package com.example.user.syncadaptercars24;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowDataFromFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_from_db);
        TextView textView=(TextView)findViewById(R.id.tvShow);

            String s=Utils.ReadFile(this);
            textView.setText("FROM FILE \n\n" + s);

    }
}
