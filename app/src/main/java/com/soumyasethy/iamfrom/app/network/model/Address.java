
package com.soumyasethy.iamfrom.app.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {
    public final static Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    };
    /* public static TypeAdapter<Address> typeAdapter(Gson gson) {
         return new AutoValue_Address.GsonTypeAdapter(gson);
     }*/
    @SerializedName("address1")
    @Expose
    public String address1;
    @SerializedName("addressType")
    @Expose
    public String addressType;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;
    @SerializedName("countryId")
    @Expose
    public int countryId;
    @SerializedName("countryName")
    @Expose
    public String countryName;
    @SerializedName("stateId")
    @Expose
    public int stateId;
    @SerializedName("stateName")
    @Expose
    public String stateName;
    @SerializedName("cityId")
    @Expose
    public int cityId;
    @SerializedName("cityName")
    @Expose
    public String cityName;

    protected Address(Parcel in) {
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.addressType = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((String) in.readValue((String.class.getClassLoader())));
        this.lng = ((String) in.readValue((String.class.getClassLoader())));
        this.countryId = ((int) in.readValue((int.class.getClassLoader())));
        this.countryName = ((String) in.readValue((String.class.getClassLoader())));
        this.stateId = ((int) in.readValue((int.class.getClassLoader())));
        this.stateName = ((String) in.readValue((String.class.getClassLoader())));
        this.cityId = ((int) in.readValue((int.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Address() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address1);
        dest.writeValue(addressType);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(countryId);
        dest.writeValue(countryName);
        dest.writeValue(stateId);
        dest.writeValue(stateName);
        dest.writeValue(cityId);
        dest.writeValue(cityName);
    }

    public int describeContents() {
        return 0;
    }

}
