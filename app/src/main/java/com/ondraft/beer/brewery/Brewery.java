package com.ondraft.beer.brewery;


import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Brewery {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("street")
    private String street;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("postal_code")
    private String postalCode;

    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("website_url")
    private String websiteUrl;

    private Brewery(BreweryBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.phoneNumber = builder.phoneNumber;
        this.websiteUrl = builder.websiteUrl;
    }

    public static BreweryBuilder Builder() {
        return new BreweryBuilder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public static class BreweryBuilder {

        private int id;
        private String name;
        private String street;
        private String city;
        private String state;
        private String postalCode;
        private String phoneNumber;
        private String websiteUrl;


        public Brewery build() {
            return new Brewery(this);
        }

        public BreweryBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public BreweryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public BreweryBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public BreweryBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public BreweryBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public BreweryBuilder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public BreweryBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public BreweryBuilder withWebsiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }
    }
}
