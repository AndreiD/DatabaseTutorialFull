package com.androidadvance.databaseone.event;

import com.androidadvance.databaseone.model.Doc;
import io.realm.RealmList;
import io.realm.RealmResults;

public class DatabaseChangedEvent {
  public RealmResults<Doc> new_things;

  public DatabaseChangedEvent(RealmResults<Doc> new_things) {
    this.new_things = new_things;
  }
}
