package com.hht.util.excel;

import java.util.List;

/**
 *
 * @author hht
 */
public class ExcelDataSheet extends ExcelSheet {
    
    //字符串格式
    public static final int COLUMN_TYPE_STRING = 0;
    //整形
    public static final int COLUMN_TYPE_INT = 1;
    //浮点型
    public static final int COLUMN_TYPE_FLOAT = 2;

    private String[] colsName;
    
    private int[] colsType;
    
    private boolean headStyle;
    
    private List<List<String>> dataList;
    
    public ExcelDataSheet(String sheetName, Integer sheetIndex , String[] colsName , List<List<String>> dataList) {
        super(sheetName, ExcelSheet.TYPE_DATA, sheetIndex);
        this.setColsName(colsName);
        this.setDataList(dataList);
    }

    public int[] getColsType() {
        return colsType;
    }

    public void setColsType(int[] colsType) {
        this.colsType = colsType;
    }

    public String[] getColsName() {
        return colsName;
    }

    public void setColsName(String[] colsName) {
        this.colsName = colsName;
    }

    public List<List<String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<String>> dataList) {
        this.dataList = dataList;
    }

	public boolean isHeadStyle() {
		return headStyle;
	}

	public void setHeadStyle(boolean headStyle) {
		this.headStyle = headStyle;
	}

}
