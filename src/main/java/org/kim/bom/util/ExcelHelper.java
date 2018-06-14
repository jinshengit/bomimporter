package org.kim.bom.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.kim.bom.annotation.ExcelCell;
import org.kim.bom.annotation.ExcelFile;
import org.kim.bom.model.BomHeader;
import org.kim.bom.model.BomItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel操作工具类
 */
public class ExcelHelper {

    private static HSSFWorkbook hssfWorkbook;
    private static XSSFWorkbook xssfWorkbook;
    private static HSSFSheet sheet;

    /**
     * 根据文件名读取Excel文件
     * @param filePath
     */
    public static BomHeader readExcel(String filePath) {
        try {
            FileInputStream stream = new FileInputStream(filePath);
            String extensionName = FileHelper.getExtension(filePath);
            Workbook workbook = null;
            if (extensionName.toLowerCase().equals("xls")) {
                workbook = new HSSFWorkbook(stream);
            } else {
                workbook = new XSSFWorkbook(stream);
            }
            return readExcel(workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据输入流读取Excel文件, 默认读取Sheet1
     */
    public static BomHeader readExcel(Workbook workbook) {
        try {
            //实例化一个sheet对象，根据annotation的sheetIndex
            ExcelFile headerAnnotation = BomHeader.class.getAnnotation(ExcelFile.class);
            Sheet sheet = workbook.getSheetAt(headerAnnotation.sheetIndex());
            if (sheet != null) {
                BomHeader bomHeader = new BomHeader();
                Field[] fields = BomHeader.class.getDeclaredFields();
                if (fields == null) {
                    throw new Exception("Can not get any fields");
                }
                for (Field field : fields) {
                    ExcelCell cellAnnotation = field.getAnnotation(ExcelCell.class);
                    //表示设置了row和column索引的属性，按照指定读取值
                    if (cellAnnotation != null && cellAnnotation.rowIndex() >= 0 && cellAnnotation.columnIndex() >= 0) {
                        Cell cell = sheet.getRow(cellAnnotation.rowIndex()).getCell(cellAnnotation.columnIndex());
                        String cellValue = getCellData(cell);
                        field.setAccessible(true);
                        field.set(bomHeader, cellValue);
                    }
                }

                //再生成BomItem的List
                //Item开始的行数
                int startIndex = headerAnnotation.ignoreRows();
                //最大的行数
                int maxIndex = sheet.getPhysicalNumberOfRows();

                bomHeader.setItems(new ArrayList<BomItem>());

                for (int i = startIndex; i <= maxIndex; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        BomItem item = getBomItem(row);
                        bomHeader.getItems().add(item);
                    }
                }
                //全部完成后返回一个BomHeader对象
                return bomHeader;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static BomItem getBomItem(Row row) {
        Field[] fields = BomItem.class.getDeclaredFields();

        if (fields != null && fields.length > 0) {
            BomItem item = new BomItem();
            for (Field field : fields) {
                ExcelCell annotation = field.getAnnotation(ExcelCell.class);
                if (annotation != null && annotation.columnIndex() >= 0) {
                    Cell cell = row.getCell(annotation.columnIndex());
                    String value = getCellData(cell);
                    field.setAccessible(true);
                    try {
                        field.set(item, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            return item;
        }

        return null;
    }

    private static String getCellData(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
            } else {
                return Double.toString(cell.getNumericCellValue());
            }
        } else if (cellType == CellType.FORMULA) {
            return cell.getCellFormula();
        } else {
            return "";
        }
    }
}

























