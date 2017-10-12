
package com.soumyasethy.iamfrom.app.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactDetails implements Parcelable {

    public final static Creator<ContactDetails> CREATOR = new Creator<ContactDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ContactDetails createFromParcel(Parcel in) {
            return new ContactDetails(in);
        }

        public ContactDetails[] newArray(int size) {
            return (new ContactDetails[size]);
        }

    };
    @SerializedName("mobileNo")
    @Expose
    public String mobileNo;

    protected ContactDetails(Parcel in) {
        this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ContactDetails() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mobileNo);
    }

    public int describeContents() {
        return 0;
    }

}
