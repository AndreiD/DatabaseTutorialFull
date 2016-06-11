package com.androidadvance.databaseone.data;

import android.util.Log;
import com.androidadvance.databaseone.event.DatabaseChangedEvent;
import com.androidadvance.databaseone.model.Doc;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import org.greenrobot.eventbus.EventBus;

public class DataManager {

  private Realm realm;
  private RealmResults<Doc> docRealmResults;

  public void mainActivityDatabaseChange() {

    realm = Realm.getDefaultInstance();
    docRealmResults = realm.where(Doc.class).findAllAsync();

    RealmChangeListener callback = new RealmChangeListener() {
      @Override public void onChange(Object element) {
        Log.i(" change detected !", ">>>>>>>>>>>>>>> ********************* <<<<<<<<<<<<");
        EventBus.getDefault().post(new DatabaseChangedEvent(docRealmResults));
      }
    };

    docRealmResults.addChangeListener(callback);
  }

  public void unregisterListener() {
    docRealmResults.removeChangeListeners();
    realm.close();
  }
}
