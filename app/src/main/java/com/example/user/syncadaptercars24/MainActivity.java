package com.example.user.syncadaptercars24;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.syncadaptercars24.model.User;
import com.example.user.syncadaptercars24.provider.CarsDatabase;
import com.example.user.syncadaptercars24.syncadapter.SyncUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etDetails;
    public static String userName = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* SyncUtils.CreateSyncAccount(this);
        SyncUtils.periodicSync();*/
        SyncUtils.CreateSyncAccount(this);
        etName = (EditText) findViewById(R.id.etName);
        etDetails = (EditText) findViewById(R.id.etDetails);
        Button btDataBase = (Button) findViewById(R.id.btnDb);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Button btnFile = (Button) findViewById(R.id.btnFile);
        Button btnSync = (Button) findViewById(R.id.btnSync);

        btnSubmit.setOnClickListener(this);
        btDataBase.setOnClickListener(this);
        btnFile.setOnClickListener(this);
        btnSync.setOnClickListener(this);
    }

    public void setEditTextEmpty()
    {
       etName.setText("");
       etDetails.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
      /*  SyncUtils.CreateSyncAccount(this);
        SyncUtils.periodicSync();*/
    }

    @Override
    public void onClick(View v) {
        final CarsDatabase database = new CarsDatabase(this);
        switch (v.getId()) {
            case R.id.btnDb:
                Intent intent = new Intent(this, ShowDataFromDB.class);
                intent.putExtra(userName, etName.getText().toString());
                if (!TextUtils.isEmpty(etName.getText().toString()))
                    startActivity(intent);
                    setEditTextEmpty();


                break;
            case R.id.btnFile:
                startActivity(new Intent(this, ShowDataFromFile.class));
                setEditTextEmpty();
                break;
            case R.id.btnSync:
                SyncUtils.TriggerRefresh();
                setEditTextEmpty();
                break;
            case R.id.btnSubmit:
               /* if (==0) {*/
                String st = database.getUserName(etName.getText().toString()).getName();
                if (!etName.getText().toString().equals(st)) {
                    database.addUser(new User(etName.getText().toString(), etDetails.getText().toString()));
                    Toast.makeText(this, "Insert new entry ", Toast.LENGTH_SHORT).show();
                    setEditTextEmpty();
                } else {
                    database.updateUser(new User(etName.getText().toString(), etDetails.getText().toString()));
                    Toast.makeText(this, "modify user data ", Toast.LENGTH_SHORT).show();
                    setEditTextEmpty();
                }
                break;
        }

    }
}
