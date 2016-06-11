
package com.androidadvance.databaseone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Doc extends RealmObject {

    @SerializedName("web_url")
    @Expose
    @PrimaryKey
    public String webUrl;
    @SerializedName("snippet")
    @Expose
    public String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    public String leadParagraph;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("print_page")
    @Expose
    public String printPage;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("headline")
    @Expose
    public Headline headline;
    @SerializedName("keywords")
    @Expose
    public RealmList<Keyword> keywords = new RealmList<>();
    @SerializedName("pub_date")
    @Expose
    public String pubDate;
    @SerializedName("document_type")
    @Expose
    public String documentType;
    @SerializedName("type_of_material")
    @Expose
    public String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    public String id;
    @SerializedName("word_count")
    @Expose
    public Integer wordCount;


    @Override public String toString() {
        return "Doc{" +
            "webUrl='" + webUrl + '\'' +
            ", snippet='" + snippet + '\'' +
            ", leadParagraph='" + leadParagraph + '\'' +
            ", _abstract='" + _abstract + '\'' +
            ", printPage='" + printPage + '\'' +
            ", source='" + source + '\'' +
            ", headline=" + headline +
            ", keywords=" + keywords +
            ", pubDate='" + pubDate + '\'' +
            ", documentType='" + documentType + '\'' +
            ", typeOfMaterial='" + typeOfMaterial + '\'' +
            ", id='" + id + '\'' +
            ", wordCount=" + wordCount +
            '}';
    }
}
