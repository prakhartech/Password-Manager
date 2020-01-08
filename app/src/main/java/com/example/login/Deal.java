package com.example.login;

public class Deal {
    private String userLink;
    private String userTitle;
    private String userUserId;
    private String userWebsite;
    private String userpass;

    public Deal() {
    }

    public Deal(String userLink, String userTitle, String userUserId, String userWebsite, String userpass) {
        this.userLink = userLink;
        this.userTitle = userTitle;
        this.userUserId = userUserId;
        this.userWebsite = userWebsite;
        this.userpass = userpass;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(String userUserId) {
        this.userUserId = userUserId;
    }

    public String getUserWebsite() {
        return userWebsite;
    }

    public void setUserWebsite(String userWebsite) {
        this.userWebsite = userWebsite;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}
