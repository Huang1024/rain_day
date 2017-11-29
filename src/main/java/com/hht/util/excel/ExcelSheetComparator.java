package com.hht.util.excel;

import java.util.Comparator;

/**
 *
 * @author hht
 */
public class ExcelSheetComparator implements Comparator<ExcelSheet>{

    @Override
    public int compare(ExcelSheet o1, ExcelSheet o2) {
        if(o1.getSheetIndex() > o2.getSheetIndex()){
            return 1;
        }else if(o1.getSheetIndex() < o2.getSheetIndex()){
            return -1;
        }else{
            return 0;
        }
    }
    
}
