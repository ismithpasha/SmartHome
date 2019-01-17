package com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels;

public class ContactInfo {

    public String getContactId() {
        return ContactId;
    }

    public void setContactId(String contactId) {
        ContactId = contactId;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public ContactInfo(String contactId, String contactName, String mobileNo, String email, String address) {
        ContactId = contactId;
        ContactName = contactName;
        MobileNo = mobileNo;
        Email = email;
        Address = address;
    }

    private String ContactId;
    private String ContactName;
    private String MobileNo;
    private String Email;
    private String Address;

}
