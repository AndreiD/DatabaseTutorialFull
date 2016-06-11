
package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.Realm;
import io.realm.RealmObject;

public class Headline extends RealmObject {

    @SerializedName("main")
    @Expose
    public String main;



}
