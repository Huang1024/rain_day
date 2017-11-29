package com.hht.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * excel导出帮助类
 *
 * @author hht
 */
@Slf4j
public class ExcelExportUtil {

    private List<ExcelSheet> excelBeanList = null;

    /**
     * 构造方法，初始化参数
     *
     * @param excelData
     */
    public ExcelExportUtil(List<ExcelSheet> excelData) {
        Collections.sort(excelData, (o1, o2) -> {
            if (o1.getSheetIndex() > o2.getSheetIndex()) {
                return 1;
            } else if (o1.getSheetIndex() < o2.getSheetIndex()) {
                return -1;
            } else {
                return 0;
            }
        });
        //判断sheet名称是否重复
        String sheetName;
        boolean nameExist = false;
        for (int i = 0; i < excelData.size(); i++) {
            sheetName = excelData.get(i).getSheetName();
            for (int j = 0; j < excelData.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (sheetName.equals(excelData.get(j).getSheetName())) {
                    nameExist = true;
                }
            }
        }
        if (nameExist) {
            for (int i = 0; i < excelData.size(); i++) {
                excelData.get(i).setSheetName("sheet" + i);
            }
        }
        excelBeanList = excelData;
    }

    /**
     * 生成workBook
     *
     * @return
     */
    public HSSFWorkbook makeExcel() {
        if (this.excelBeanList != null && !this.excelBeanList.isEmpty()) {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet;
            HSSFCellStyle cellStyle;

            HSSFCellStyle headStyle = wb.createCellStyle();
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFFont font = wb.createFont();
            font.setBold(true);
            headStyle.setFont(font);
            headStyle.setWrapText(true);

            HSSFCellStyle rowStyle = wb.createCellStyle();
            rowStyle.setAlignment(HorizontalAlignment.CENTER);

            for (int i = 0; i < this.excelBeanList.size(); i++) {
                sheet = wb.createSheet();
                wb.setSheetName(i, excelBeanList.get(i).getSheetName());

                switch (excelBeanList.get(i).getSheetType()) {
                    case ExcelSheet.TYPE_OVERVIEW:
                        ExcelOverviewSheet overView = (ExcelOverviewSheet) excelBeanList.get(i);
                        if (overView.getViewData() != null && !overView.getViewData().isEmpty()) {
                            Iterator<Entry<String, String>> it = overView.getViewData().entrySet().iterator();
                            int j = 0;
                            while (it.hasNext()) {
                                HSSFRow row = sheet.createRow(j);
                                Entry<String, String> entry = it.next();
                                String key = entry.getKey();
                                String value = entry.getValue();
                                row.createCell(0).setCellValue(key);
                                row.createCell(1).setCellValue(value);
                                j++;
                            }
                        } else {
                            HSSFRow row = sheet.createRow(0);
                            row.createCell(0).setCellValue("该sheet没有内容");
                        }
                        break;
                    case ExcelSheet.TYPE_DATA:
                        ExcelDataSheet dataView = (ExcelDataSheet) excelBeanList.get(i);
                        boolean hasStyle = false;
                        if (dataView.getColsName() != null && dataView.getColsName().length != 0) {
                            if (dataView.getColsType() != null && dataView.getColsType().length == dataView.getColsName().length) {
                                hasStyle = true;
                            }
                            HSSFRow headCol = sheet.createRow(0);
                            for (int j = 0; j < dataView.getColsName().length; j++) {
                                HSSFCell cell = headCol.createCell(j);
                                cell.setCellValue(dataView.getColsName()[j]);

                                if (dataView.isHeadStyle()) {
                                    sheet.setDefaultColumnWidth(13);
                                    cell.setCellStyle(headStyle);
                                }
                            }
                        }
                        if (dataView.getDataList() != null && !dataView.getDataList().isEmpty()) {
                            HSSFCell cell;
                            for (int j = 0; j < dataView.getDataList().size(); j++) {
                                HSSFRow row = sheet.createRow(j + 1);
                                for (int k = 0; k < dataView.getDataList().get(j).size(); k++) {
                                    cell = row.createCell(k);
                                    if (hasStyle) {
                                        switch (dataView.getColsType()[k]) {
                                            case ExcelDataSheet.COLUMN_TYPE_INT:
                                                cell.setCellValue(Integer.parseInt(dataView.getDataList().get(j).get(k)));
                                                cellStyle = wb.createCellStyle();
                                                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
                                                cell.setCellStyle(cellStyle);
                                                break;
                                            case ExcelDataSheet.COLUMN_TYPE_FLOAT:
                                                cell.setCellValue(Float.parseFloat(dataView.getDataList().get(j).get(k)));
                                                cellStyle = wb.createCellStyle();
//                                                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
                                                cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.0"));
                                                cell.setCellStyle(cellStyle);
                                                break;
                                            default:
                                                cell.setCellValue(dataView.getDataList().get(j).get(k));
                                                break;
                                        }
                                    } else {
                                        cell.setCellValue(dataView.getDataList().get(j).get(k));
                                    }
                                }
                            }
                        } else {
//                            HSSFRow row = sheet.createRow(0);
//                            row.createCell(0).setCellValue("该sheet没有内容");
                        }
                        break;
                }
            }
            return wb;
        } else {
            return null;
        }
    }

    /**
     * 下载文件帮助方法
     *
     * @param wb       excelWorkBook
     * @param response
     * @param filename 文件名，可为空
     */
    public static void downloadExcel(HSSFWorkbook wb, HttpServletResponse response, String filename) {
        if (filename == null || "".equals(filename)) {
            filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
        }
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            log.error("导出失败:" + e);
        }
    }

}
