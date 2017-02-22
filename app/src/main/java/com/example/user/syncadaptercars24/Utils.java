package com.example.user.syncadaptercars24;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Mushareb Ali on 2/20/2017.
 * mushareba.ali@cars24.com
 */

public class Utils {

    public static void WriteFile(Context context, ArrayList<String> arrayList) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("cars.txt", Context.MODE_PRIVATE));
            for (int i = 0; i <arrayList.size() ; i++) {
                outputStreamWriter.write(arrayList.get(i)+"\n\n");
            }
            //outputStreamWriter.write(string);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    static String ReadFile(Context context) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("cars.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
