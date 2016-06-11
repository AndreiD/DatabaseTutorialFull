
package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class Meta {

    @SerializedName("hits")
    @Expose
    public Integer hits;
    @SerializedName("time")
    @Expose
    public Integer time;
    @SerializedName("offset")
    @Expose
    public Integer offset;



}
