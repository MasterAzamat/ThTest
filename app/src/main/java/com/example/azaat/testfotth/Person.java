package com.example.azaat.testfotth;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Person implements Serializable{
    @SerializedName("id")
    int id;
    @SerializedName("first_name")
    String name;
    @SerializedName("last_name")
    String surname;
    @SerializedName("email")
    String mail;
    @SerializedName("gender")
    String mi;
    @SerializedName("ip_address")
    String ip_address;
    @SerializedName("photo")
    String photo;
    @SerializedName("employment")
    Employment employment;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }

    public String getMi() {
        return mi;
    }

    public String getIp_address() {
        return ip_address;
    }

    public String getPhoto() {
        return photo;
    }

    public Employment getEmployment() {
        return employment;
    }


}
class Employment implements Serializable{
    @SerializedName("name")
    String name;
    @SerializedName("position")
    String position;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
