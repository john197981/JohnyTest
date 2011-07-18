
package com.oifm.survey;

import java.io.Serializable;
import java.util.Hashtable;

public class OIBASurveyResponse implements Serializable {

	private String strDivisioncode;
	private String strSchoolCode;
	private String strNickName;	
	private Hashtable htbUserResponse;
	private String age;
	private String service;
	private String designation;
	private String email;
	private String schoolLevel;

    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
	public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSchoolLevel() {
        return schoolLevel;
    }
    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

	public OIBASurveyResponse() {
		super();
	}
	
	public String getStrDivisioncode() {
		return strDivisioncode;
	}
	public String getStrNickName() {
		return strNickName;
	}
	public String getStrSchoolCode() {
		return strSchoolCode;
	}
	public Hashtable getHtbUserResponse() {
		return htbUserResponse;
	}
	public void setHtbUserResponse(Hashtable htbUserResponse) {
		this.htbUserResponse = htbUserResponse;
	}
	public void setStrDivisioncode(String strDivisioncode) {
		this.strDivisioncode = strDivisioncode;
	}
	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}
	public void setStrSchoolCode(String strSchoolCode) {
		this.strSchoolCode = strSchoolCode;
	}

}
