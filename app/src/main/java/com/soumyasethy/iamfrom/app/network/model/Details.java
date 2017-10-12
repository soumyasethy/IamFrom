
package com.soumyasethy.iamfrom.app.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details implements Parcelable {

    public final static Creator<Details> CREATOR = new Creator<Details>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        public Details[] newArray(int size) {
            return (new Details[size]);
        }

    };
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("timezone")
    @Expose
    public String timezone;
    @SerializedName("userDetails")
    @Expose
    public UserDetails userDetails;

    protected Details(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
        this.userDetails = ((UserDetails) in.readValue((UserDetails.class.getClassLoader())));
    }

    public Details() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(timezone);
        dest.writeValue(userDetails);
    }

    public int describeContents() {
        return 0;
    }

}
