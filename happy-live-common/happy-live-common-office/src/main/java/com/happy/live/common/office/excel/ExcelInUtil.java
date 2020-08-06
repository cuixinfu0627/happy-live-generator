package com.happy.live.common.office.excel;
/**
 *
 */

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExcelInUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @return
     * @throws IOException
     */
    public Workbook getWorkbok(InputStream in,Integer type) throws IOException{
        Workbook wb = null;
        if(type == 1){  //Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(type == 2){  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     * @throws Exception
     */
    public void checkExcelVaild(File file) throws Exception{
        if(!file.exists()){
            throw new Exception("文件不存在");
        }
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){
            throw new Exception("文件不是Excel");
        }
    }
    /**
     *@描述: 获取表格中的值
     *@参数: [cell]
     *@创建人: cuixinfu@51play.com
     *@创建时间: 2018/9/13 12:25
     */
    public Object getValue(Cell cell) {
        Object obj = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                obj = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                obj = cell.getErrorCellValue();
                break;
            case NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    cell.getDateCellValue();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date dateCellValue = cell.getDateCellValue();
                    obj = formatter.format(dateCellValue);
                }else {
                    //obj = String.valueOf(cell.getNumericCellValue());
                    cell.setCellType(cell.CELL_TYPE_STRING);
                    obj = String.valueOf(cell.getStringCellValue());
                }
                break;
            case STRING:
                obj = String.valueOf(cell.getStringCellValue());
                break;
            default:
                break;
        }
        return obj;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]{8}");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public Integer getLoop(String str){
        Pattern pattern = Pattern.compile("回路：([0-9]+)-回路");
        Matcher isNum = pattern.matcher(str);
        if(isNum.find()){
            return Integer.valueOf(isNum.group(1));
        }
        return null;
    }
}
