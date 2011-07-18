package com.oifm.consultation;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			08/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */


import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;

public class OIFormConsultListingWeb extends OIBaseActionForm 
{
    ArrayList arOIBVPaperPresent;
    ArrayList arOIBVPaperPast;

    public OIFormConsultListingWeb()
    {
        arOIBVPaperPresent = new ArrayList();
        arOIBVPaperPast = new ArrayList();
    }
    
    /**
     * @return Returns the arOIBVPaperPast.
     */
    public ArrayList getArOIBVPaperPast() {
        return arOIBVPaperPast;
    }
    /**
     * @param arOIBVPaperPast The arOIBVPaperPast to set.
     */
    public void setArOIBVPaperPast(ArrayList arOIBVPaperPast) {
        this.arOIBVPaperPast = arOIBVPaperPast;
    }

    public void addArOIBVPaperPast(Object obj) {
        this.arOIBVPaperPast.add(obj);
    }
    /**
     * @return Returns the arOIBVPaperPresent.
     */
    public ArrayList getArOIBVPaperPresent() {
        return arOIBVPaperPresent;
    }
    /**
     * @param arOIBVPaperPresent The arOIBVPaperPresent to set.
     */
    public void setArOIBVPaperPresent(ArrayList arOIBVPaperPresent) {
        this.arOIBVPaperPresent = arOIBVPaperPresent;
    }
    public void addArOIBVPaperPresent(Object obj) {
        this.arOIBVPaperPresent.add(obj);
    }
}
