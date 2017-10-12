
package com.soumyasethy.iamfrom.app.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetails implements Parcelable {

    public final static Creator<UserDetails> CREATOR = new Creator<UserDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserDetails createFromParcel(Parcel in) {
            return new UserDetails(in);
        }

        public UserDetails[] newArray(int size) {
            return (new UserDetails[size]);
        }

    };
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("addresses")
    @Expose
    public List<Address> addresses = null;
    @SerializedName("contactDetails")
    @Expose
    public ContactDetails contactDetails;
    @SerializedName("motherTongue")
    @Expose
    public String motherTongue;
    @SerializedName("maritalStatus")
    @Expose
    public String maritalStatus;
    @SerializedName("profilePic")
    @Expose
    public String profilePic;

    protected UserDetails(Parcel in) {
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.addresses, (Address.class.getClassLoader()));
        this.contactDetails = ((ContactDetails) in.readValue((ContactDetails.class.getClassLoader())));
        this.motherTongue = ((String) in.readValue((String.class.getClassLoader())));
        this.maritalStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePic = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserDetails() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(firstName);
        dest.writeValue(lastName);
        dest.writeList(addresses);
        dest.writeValue(contactDetails);
        dest.writeValue(motherTongue);
        dest.writeValue(maritalStatus);
        dest.writeValue(profilePic);
    }

    public int describeContents() {
        return 0;
    }

}
