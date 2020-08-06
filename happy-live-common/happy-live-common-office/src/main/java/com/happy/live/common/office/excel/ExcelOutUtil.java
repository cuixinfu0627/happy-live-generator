package com.happy.live.common.office.excel;
/**
 *
 */

import com.happy.live.common.base.StringHandler;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ExcelOutUtil {

    private static Logger logger = LoggerFactory.getLogger(ExcelOutUtil.class);

    public static <T> void  export(OutputStream out, List<Three<String,String, DataFormat>> headers, List<T> datas){
        LocalDateTime localDateTime =  LocalDateTime.now();
        System.out.println("导出时间"+localDateTime);
        if(headers == null || headers.isEmpty()){
            throw new RuntimeException("没有表头信息") ;
        }
        List<List<String>> rows = new ArrayList<>() ;
        List<String> head = new ArrayList<>() ;
        for (Three<String,String, DataFormat> header : headers) {
            head.add(header.getOne()) ;
        }
        rows.add(head) ;
        if(datas != null && !datas.isEmpty()){
            for (T data : datas) {
                List<String> row = new ArrayList<>() ;
                for (Three<String,String, DataFormat> header : headers) {
                    String field = header.getTwo() ;
                    try {
                        Method method = data.getClass().getMethod("get" + field.substring(0,1).toUpperCase() + field.substring(1),new Class[]{}) ;
                        Object object = method.invoke(data,new Object[]{}) ;
                        if(header.getThree() != null){
                            object = "" + header.getThree().format(object) ;
                        }
                        if(StringHandler.isNotEmpty(object)){
                            row.add(object.toString()) ;
                        }else{
                            row.add("-") ;
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                rows.add(row) ;
            }
        }
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook workBook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workBook.createSheet();
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 设置字体
        HSSFFont font = workBook.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
        font.setFontName("黑体"); //字体
        font.setBold(true); //字体


        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(true);

        for (int i = 0; i < rows.size(); i++) {
            row = sheet.createRow(i);
            List<String> rowData =  rows.get(i);
            // 第四步，创建单元格，并设置值
            int j = 0;
            for (String cellData : rowData) {
                HSSFCell cell = row.createCell(j) ;
                if(i == 0){
                    cell.setCellStyle(style);
                }
                cell.setCellValue(cellData);
//                sheet.autoSizeColumn(j,true);
                j++ ;
            }
        }
        try {
            workBook.write(out);
            Duration duration = Duration.between(localDateTime,  LocalDateTime.now() );
            System.out.println("导出耗费时长"+duration.toMillis()+"ms" );
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出功能
     * 注意：泛型T类字段名和containBean集合里字段名字的一致性
     *
     * @param response
     * @param title       表名
     * @param headers     表头
     * @param list        数据集
     * @param <T>
     * @throws Exception
     */

    public static <T> void exportExcel(HttpServletResponse response, String title, String[] headers, List<List<T>> list) throws Exception {
        LocalDateTime startTime = LocalDateTime.now();
        logger.info("{}执行导出Excel表格",startTime.format(DateTimeFormatter.ISO_DATE_TIME));
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);
            // 设置字体
            HSSFFont font = workbook.createFont();
            font.setFontName("微软雅黑"); //字体
            font.setBold(true); //字体
            //
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            //水平居中
            style.setAlignment(HorizontalAlignment.CENTER);
            //垂直居中
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setWrapText(true);

            HSSFRow row = sheet.createRow(0);
            /*创建第一行表头*/
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
                cell.setCellStyle(style);
            }
            Iterator<List<T>> it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                List<T> next = it.next();
                for (int i = 0; i < next.size(); i++) {
                    Object o = next.get(i);
                    setCellValue(o, row, i);
                }
            }
            /*application/vnd.ms-excel告诉浏览器要下载的是个excel*/
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            /*请求头设置，Content-Disposition为下载标识，attachment标识以附件方式下载*/
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((title).getBytes(), "ISO8859-1") + ".xls");
            workbook.write(response.getOutputStream());
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        LocalDateTime endTime = LocalDateTime.now();
        logger.info("{}执行导出Excel表格完毕",endTime.format(DateTimeFormatter.ISO_DATE_TIME));
        logger.info("消耗时间{}", Duration.between(startTime,endTime));
    }
    /**
     * 设置每一行中的列
     *
     * @param value
     * @param row
     * @param index
     * @param <T>
     */
    private static <T> void setCellValue(Object value, HSSFRow row, int index) {
        HSSFCell cell = row.createCell(index);
        String textValue = null;
        if (value != null) {
            if (value instanceof Date) {
                Date date = (Date) value;
                textValue = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            } else {
                textValue = value.toString();
            }
        }else{
            textValue = "-";
        }
        if (textValue != null) {
            cell.setCellValue(textValue);
        }
    }

//    List<Alarm> alarmList = this.alarmServiceRemote.queryByParamMap(params);
//    // 头部
//    String[] headers = {"序号", "报警源", "设备类型", "状态", "报警类型", "所属单位", "位置", "发生时间", "确认人", "确认时间", "关闭人", "关闭时间"};
//    // 标题
//    String title = "报警列表" + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//    Iterator<Alarm> iterator = alarmList.iterator();
//
//    List<Object> stringLists;
//    List<List<Object>> exportLists = new ArrayList<>();
//        while (iterator.hasNext()) {
//        Alarm alarm = iterator.next();
//        stringLists = new ArrayList<>();
//        // 序号
//        stringLists.add(alarm.getId().toString());
//        // 报警源
//        stringLists.add(alarm.getUserId() != 0 ? alarm.getNickName() : alarm.getDeviceName());
//        // 设备类型
//        stringLists.add(alarm.getDeviceTypeName());
//        // 状态
//        stringLists.add(AlarmStatus.getAlarmStatus(alarm.getStatus()).getName());
//        // 报警类型
//        stringLists.add(AlarmLevel.getAlarmLevel(alarm.getEventLevel()).getName());
//        // 所属单位
//        stringLists.add(alarm.getUnitName());
//        //位置
//        String local = alarm.getBuildingName() +
//                alarm.getFloorNumber() +
//                alarm.getRoomNumber();
//        stringLists.add(local.replace("null", ""));
//        // 发生时间
//        stringLists.add(alarm.getStartTime() == null ? "-" : alarm.getStartTime());
//        // 确认人
//        stringLists.add(alarm.getConfirmNickName() == null ? "-" : alarm.getConfirmNickName());
//        // 确认时间
//        stringLists.add(alarm.getConfirmTime() == null ? "-" : alarm.getConfirmTime());
//        // 关闭人
//        stringLists.add(alarm.getCancelNickName() == null ? "-" : alarm.getCancelNickName());
//        // 关闭时间
//        stringLists.add(alarm.getEndTime() == null ? "-" : alarm.getEndTime());
//        exportLists.add(stringLists);
//    }
//    // 导出
//    // ExcelOutUtil.exportExcel(response, title, headers, exportLists);

}
