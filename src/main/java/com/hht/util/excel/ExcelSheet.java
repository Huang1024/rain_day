package com.hht.util.excel;

/**
 *
 * @author hht
 */
public class ExcelSheet {

    private String sheetName;
    
    private Integer sheetType;
    
    private Integer sheetIndex;
    
    public static final int TYPE_OVERVIEW = 1;
    
    public static final int TYPE_DATA = 2;

    public ExcelSheet(String sheetName, Integer sheetType , Integer sheetIndex) {
        this.sheetName = sheetName;
        this.sheetType = sheetType;
        this.sheetIndex = sheetIndex;
    }

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
    
    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getSheetType() {
        return sheetType;
    }

    public void setSheetType(Integer sheetType) {
        this.sheetType = sheetType;
    }
    
}
