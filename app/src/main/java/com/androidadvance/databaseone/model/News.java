
package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;

public class News {

    @SerializedName("response")
    @Expose
    public Response response;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;

    @Override public String toString() {
        return "News{" +
            "response=" + response +
            ", status='" + status + '\'' +
            ", copyright='" + copyright + '\'' +
            '}';
    }
}
