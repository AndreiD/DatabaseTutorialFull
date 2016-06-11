
package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("meta")
    @Expose
    public Meta meta;
    @SerializedName("docs")
    @Expose
    public List<Doc> docs = new ArrayList<>();

    @Override public String toString() {
        return "Response{" +
            "meta=" + meta +
            ", docs=" + docs +
            '}';
    }
}
