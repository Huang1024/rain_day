package com.hht.util.excel;

import java.util.Map;

/**
 *
 * @author hht
 */
public class ExcelOverviewSheet extends ExcelSheet {
    
    private Map<String, String> viewData;

    public Map<String, String> getViewData() {
        return viewData;
    }

    public void setViewData(Map<String, String> viewData) {
        this.viewData = viewData;
    }
    
    public ExcelOverviewSheet(String sheetName, Integer sheetIndex) {
        super(sheetName, ExcelSheet.TYPE_OVERVIEW, sheetIndex);
    }
    
}
