package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class OIBAConsultQuestion 
{
    private String answer5;
    private String answer4;
    private String answer3;
    private String answer2;
    private int paperId;
    private String answerType;
    private String answer1;
    private int questionId;
    private String question;
    private String needOtherRemark;
	private String likertScale;
	private String useSameLikert;
	private String canMoveUp;
	private String canMoveDown;
    
	/**
	 * @return Returns the needOtherRemark.
	 */
	public String getNeedOtherRemark() {
		return needOtherRemark;
	}
	/**
	 * @param needOtherRemark The needOtherRemark to set.
	 */
	public void setNeedOtherRemark(String needOtherRemark) {
		this.needOtherRemark = needOtherRemark;
	}
    /**
     * @return Returns the answer1.
     */
    public String getAnswer1() {
        return answer1;
    }
    /**
     * @param answer1 The answer1 to set.
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    /**
     * @return Returns the answer2.
     */
    public String getAnswer2() {
        return answer2;
    }
    /**
     * @param answer2 The answer2 to set.
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    /**
     * @return Returns the answer3.
     */
    public String getAnswer3() {
        return answer3;
    }
    /**
     * @param answer3 The answer3 to set.
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    /**
     * @return Returns the answer4.
     */
    public String getAnswer4() {
        return answer4;
    }
    /**
     * @param answer4 The answer4 to set.
     */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    /**
     * @return Returns the answer5.
     */
    public String getAnswer5() {
        return answer5;
    }
    /**
     * @param answer5 The answer5 to set.
     */
    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }
    /**
     * @return Returns the answerType.
     */
    public String getAnswerType() {
        return answerType;
    }
    /**
     * @param answerType The answerType to set.
     */
    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }
    /**
     * @return Returns the paperId.
     */
    public int getPaperId() {
        return paperId;
    }
    /**
     * @param paperId The paperId to set.
     */
    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }
    /**
     * @return Returns the question.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * @param question The question to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * @return Returns the questionId.
     */
    public int getQuestionId() {
        return questionId;
    }
    /**
     * @param questionId The questionId to set.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

	 /**
     * @return Returns the likertScale.
     */
    public String getLikertScale() {
        return likertScale;
    }
    /**
     * @param likertScale The likertScale to set.
     */
    public void setLikertScale(String likertScale) {
        this.likertScale = likertScale;
    }

	 /**
     * @return Returns the useSameLikert.
     */
    public String getUseSameLikert() {
        return useSameLikert;
    }
    /**
     * @param useSameLikert The useSameLikert to set.
     */
    public void setUseSameLikert(String useSameLikert) {
        this.useSameLikert = useSameLikert;
    }

	 /**
     * @return Returns the canMoveUp
     */
    public String getCanMoveUp() {
        return canMoveUp;
    }
    /**
     * @param canMoveUp The canMoveUp to set.
     */
    public void setCanMoveUp(String canMoveUp) {
        this.canMoveUp = canMoveUp;
    }
	 /**
     * @return Returns the canMoveDown
     */
    public String getCanMoveDown() {
        return canMoveDown;
    }
    /**
     * @param canMoveUp The canMoveDown to set.
     */
    public void setCanMoveDown(String canMoveDown) {
        this.canMoveDown = canMoveDown;
    }
}
