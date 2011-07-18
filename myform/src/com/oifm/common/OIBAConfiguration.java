package com.oifm.common;

public class OIBAConfiguration 
{
    private int configId;
    private String caption;
    private String value;
    private String unit;
    private String tag;
    
    /**
     * @return Returns the caption.
     */
    public String getCaption() {
        return caption;
    }
    /**
     * @param caption The caption to set.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }
    /**
     * @return Returns the configId.
     */
    public int getConfigId() {
        return configId;
    }
    /**
     * @param configId The configId to set.
     */
    public void setConfigId(int configId) {
        this.configId = configId;
    }
    /**
     * @return Returns the tag.
     */
    public String getTag() {
        return tag;
    }
    /**
     * @param tag The tag to set.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    /**
     * @return Returns the unit.
     */
    public String getUnit() {
        return unit;
    }
    /**
     * @param unit The unit to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
