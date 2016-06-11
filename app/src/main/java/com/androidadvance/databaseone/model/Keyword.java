package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Keyword extends RealmObject {

  @SerializedName("name") @Expose public String name;
  @SerializedName("value") @Expose public String value;
}
