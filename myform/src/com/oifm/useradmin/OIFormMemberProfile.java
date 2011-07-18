package com.oifm.useradmin;

import com.oifm.base.OIBaseActionForm;

public class OIFormMemberProfile extends OIBaseActionForm 
{
    private String userId;
    private String nickName;
    private String hobbies;
    private String interest;
    private String totalPosting;
    private String signature;

    /**
     * @return Returns the hobbies.
     */
    public String getHobbies() {
        return hobbies;
    }
    /**
     * @param hobbies The hobbies to set.
     */
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    /**
     * @return Returns the interest.
     */
    public String getInterest() {
        return interest;
    }
    /**
     * @param interest The interest to set.
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }
    /**
     * @return Returns the nickName.
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * @param nickName The nickName to set.
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * @return Returns the signature.
     */
    public String getSignature() {
        return signature;
    }
    /**
     * @param signature The signature to set.
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }
    /**
     * @return Returns the totalPosting.
     */
    public String getTotalPosting() {
        return totalPosting;
    }
    /**
     * @param totalPosting The totalPosting to set.
     */
    public void setTotalPosting(String totalPosting) {
        this.totalPosting = totalPosting;
    }
    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
