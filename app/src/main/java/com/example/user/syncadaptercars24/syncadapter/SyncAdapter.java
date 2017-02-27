package com.example.user.syncadaptercars24.syncadapter;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;

import com.example.user.syncadaptercars24.Utils;
import com.example.user.syncadaptercars24.model.User;
import com.example.user.syncadaptercars24.provider.CarsDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

/**
 * Created by Mushareb Ali on 2/16/2017.
 * mushareba.ali@cars24.com
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
             CarsDatabase database=new CarsDatabase(getContext());

               /* Get  data from  database*/
        try {
            List<User> user = database.getAllDetails();
            ArrayList<String> log = new ArrayList<>();
            for (User cn : user) {
                log.add(" Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Details: " + cn.getDetials() + "\n\n");
                Log.i(TAG, "Streaming data from network: " + log);
            }
        /* Write data in txt file*/
            Utils.WriteFile(getContext(), log);
        } catch (Exception e) {
            handleException(e, syncResult);
        }
    }

    private void handleException(Exception e,
                                 SyncResult syncResult) {
        if (e instanceof AuthenticatorException) {
            syncResult.stats.numParseExceptions++;
            Log.e(TAG, "AuthenticatorException", e);
        } else if (e instanceof OperationCanceledException) {
            Log.e(TAG, "OperationCanceledExcepion", e);
        } else if (e instanceof IOException) {
            Log.e(TAG, "IOException", e);
            syncResult.stats.numIoExceptions++;

        } else if (e instanceof ParseException) {
            syncResult.stats.numParseExceptions++;
            Log.e(TAG, "ParseException", e);
        }
    }
}
