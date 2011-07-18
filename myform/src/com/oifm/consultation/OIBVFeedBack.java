package com.oifm.consultation;

import java.util.Date;
import java.util.ArrayList;

public class OIBVFeedBack 
{
    private String userId;
    private String feedBack;
    private String userName;
    private Date feedBackDate;
    private String school;
    private String division;
    private String age;
    private String service;
    private String designation;
    private String email;
    private String schoolLevel;
	private ArrayList responses;
    
    /**
     * @return Returns the age.
     */
    public String getAge() {
        return age;
    }
    /**
     * @param age The age to set.
     */
    public void setAge(String age) {
        this.age = age;
    }
    /**
     * @return Returns the designation.
     */
    public String getDesignation() {
        return designation;
    }
    /**
     * @param designation The designation to set.
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return Returns the schoolLevel.
     */
    public String getSchoolLevel() {
        return schoolLevel;
    }
    /**
     * @param schoolLevel The schoolLevel to set.
     */
    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }
    /**
     * @return Returns the division.
     */
    public String getDivision() {
        return division;
    }
    /**
     * @param division The division to set.
     */
    public void setDivision(String division) {
        this.division = division;
    }
    /**
     * @return Returns the school.
     */
    public String getSchool() {
        return school;
    }
    /**
     * @param school The school to set.
     */
    public void setSchool(String school) {
        this.school = school;
    }
    /**
     * @return Returns the feedBack.
     */
    public String getFeedBack() {
        return feedBack;
    }
    /**
     * @param feedBack The feedBack to set.
     */
    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
    /**
     * @return Returns the feedBackDate.
     */
    public Date getFeedBackDate() {
        return feedBackDate;
    }
    /**
     * @param feedBackDate The feedBackDate to set.
     */
    public void setFeedBackDate(Date feedBackDate) {
        this.feedBackDate = feedBackDate;
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
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return Returns the service.
     */
    public String getService() {
        return service;
    }
    /**
     * @param service The service to set.
     */
    public void setService(String service) {
        this.service = service;
    }

	 /**
     * @return Returns the responses.
     */
    public ArrayList getResponses() {
        return responses;
    }
    /**
     * @param responses The responses to set.
     */
    public void setResponses(ArrayList responses) {
        this.responses = responses;
    }
}
