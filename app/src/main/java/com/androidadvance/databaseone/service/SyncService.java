package com.androidadvance.databaseone.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;
import com.androidadvance.databaseone.data.remote.TheAPI;
import com.androidadvance.databaseone.model.Doc;
import com.androidadvance.databaseone.model.News;
import com.androidadvance.databaseone.util.AndroidComponentUtil;
import com.androidadvance.databaseone.util.NetworkUtil;
import io.realm.Realm;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncService extends Service {

  private Realm realm;

  public static Intent getStartIntent(Context context) {
    return new Intent(context, SyncService.class);
  }

  public static boolean isRunning(Context context) {
    return AndroidComponentUtil.isServiceRunning(context, SyncService.class);
  }

  @Override public void onCreate() {
    super.onCreate();
    realm = Realm.getDefaultInstance();
  }

  @Override public int onStartCommand(Intent intent, int flags, final int startId) {
    Log.i("SyncService", "Starting sync...");

    if (!NetworkUtil.isNetworkConnected(this)) {
      Log.i("SyncService", "Sync canceled, connection not available");
      AndroidComponentUtil.toggleComponent(this, SyncOnConnectionAvailable.class, true);
      stopSelf(startId);
      return START_NOT_STICKY;
    }

    TheAPI theAPI = TheAPI.Factory.getIstance(this);
    theAPI.getNews("usa+elections").enqueue(new Callback<News>() {
      @Override public void onResponse(Call<News> call, Response<News> response) {

        List<Doc> docList = response.body().response.docs;

        for (Doc doc : docList) {
          //store in db
          realm.beginTransaction();
          realm.copyToRealmOrUpdate(doc);
          realm.commitTransaction();
        }

        Log.v(" ok ", "succesfully written to db!");

      }

      @Override public void onFailure(Call<News> call, Throwable t) {
        Log.e("api request failed", t.getMessage());
      }
    });

    return START_STICKY;
  }

  @Override public void onDestroy() {
    realm.close();
    super.onDestroy();
  }

  @Override public IBinder onBind(Intent intent) {
    return null;
  }

  public static class SyncOnConnectionAvailable extends BroadcastReceiver {

    @Override public void onReceive(Context context, Intent intent) {
      if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION) && NetworkUtil.isNetworkConnected(context)) {
        Log.i("Connection available", "triggering sync...");
        AndroidComponentUtil.toggleComponent(context, this.getClass(), false);
        context.startService(getStartIntent(context));
      }
    }
  }
}