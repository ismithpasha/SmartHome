package com.smarthomesystem.ju.smarthomesystemapplication.adaptermodels;

public class MemberInfo {
    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMemberMobileNo() {
        return MemberMobileNo;
    }

    public void setMemberMobileNo(String memberMobileNo) {
        MemberMobileNo = memberMobileNo;
    }

    public MemberInfo(String memberId, String memberName, String email, String memberMobileNo) {
        MemberId = memberId;
        MemberName = memberName;
        Email = email;
        MemberMobileNo = memberMobileNo;
    }

    private String MemberId;
    private String MemberName;
    private String Email;
    private String MemberMobileNo;

}
