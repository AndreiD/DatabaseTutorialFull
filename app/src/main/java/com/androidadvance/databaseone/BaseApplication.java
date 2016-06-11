package com.androidadvance.databaseone;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.util.regex.Pattern;

public class BaseApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();

    initializeStetho(this);

    //------ init the db ----------------
    RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().name("TheDb.realm").build();
    Realm.setDefaultConfiguration(config);
  }

  private void initializeStetho(final Context context) {

    Stetho.initialize(Stetho.newInitializerBuilder(context)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
        .enableWebKitInspector(RealmInspectorModulesProvider.builder(context)
            .withDescendingOrder()
            .withLimit(1000)
            .databaseNamePattern(Pattern.compile(".+\\.realm"))
            .build())
        .build());
  }
}