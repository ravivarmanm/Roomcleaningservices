package com.cts.RoomCleaningService.report;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lowagie.text.Phrase;
 
public class ExcelExporter {
	
	
	private static void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
	
 
    private static void writeR1HeaderLine(XSSFWorkbook workbook,XSSFSheet sheet) {
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(sheet,row, 0, "S No", style);      
        createCell(sheet,row, 1, "Service Id", style);       
        createCell(sheet,row, 2, "User Id", style);    
        createCell(sheet,row, 3, "Cleaner Id", style);
        createCell(sheet,row, 4, "RoomCount", style);
        createCell(sheet,row, 5, "Address", style);
        createCell(sheet,row, 6, "Pincode", style);
        createCell(sheet,row, 7, "Contact", style);
        createCell(sheet,row, 8, "Registered At", style);
        createCell(sheet,row, 9, "Completed At", style);
        createCell(sheet,row, 10, "Time Slot", style);
                 
    }
    
    private static void writeR2HeaderLine(XSSFWorkbook workbook,XSSFSheet sheet) {
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(sheet,row, 0, "S No", style);      
        createCell(sheet,row, 1, "Service Id", style);       
        createCell(sheet,row, 2, "User Id", style);    
        createCell(sheet,row, 3, "Question", style);
        createCell(sheet,row, 4, "Answer", style);
        createCell(sheet,row, 5, "Cleaner Id", style);
        createCell(sheet,row, 6, "Address", style);
        createCell(sheet,row, 7, "Pincode", style);
        createCell(sheet,row, 8, "Time Slot", style);
        
    }
    
    private static void writeR3HeaderLine(XSSFWorkbook workbook,XSSFSheet sheet) {
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(sheet,row, 0, "S No", style);      
        createCell(sheet,row, 1, "Service Id", style);       
        createCell(sheet,row, 2, "User Id", style);    
        createCell(sheet,row, 3, "Rating", style);
        createCell(sheet,row, 4, "Feedback", style);
        createCell(sheet,row, 5, "Cleaner Id", style);
        createCell(sheet,row, 6, "Address", style);
        createCell(sheet,row, 7, "Pincode", style);
        createCell(sheet,row, 8, "Time Slot", style);   
             
    }
    
    private static void writeR4HeaderLine(XSSFWorkbook workbook,XSSFSheet sheet) {
    	
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(sheet,row, 0, "S No", style);      
        createCell(sheet,row, 1, "Cleaner Id", style);       
        createCell(sheet,row, 2, "Full Name", style);    
        createCell(sheet,row, 3, "DOB", style);
        createCell(sheet,row, 4, "Gender", style);
        createCell(sheet,row, 5, "Phone", style);
        createCell(sheet,row, 6, "Registered At", style);
        createCell(sheet,row, 7, "Profile Status", style);
            
    }
     
    private static void writeR1DataLines(XSSFWorkbook workbook,XSSFSheet sheet,List<Report1> r1) {
        
    	int rowCount = 1;
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Report1 r : r1) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(sheet,row, columnCount++, r.getS_no(), style);
            createCell(sheet,row, columnCount++, r.getService_id(), style);
            createCell(sheet,row, columnCount++, r.getUser_id(), style);
            createCell(sheet,row, columnCount++, r.getCleaner_id().toString(), style);
            createCell(sheet,row, columnCount++, r.getRoom_count(), style);
            createCell(sheet,row, columnCount++, r.getAddress(), style);
            createCell(sheet,row, columnCount++, r.getPincode(), style);
            createCell(sheet,row, columnCount++, r.getContactNumber(), style);
            createCell(sheet,row, columnCount++, r.getCreated(), style);
            createCell(sheet,row, columnCount++, r.getModified(), style);
            createCell(sheet,row, columnCount++, r.getTime_from() +" - " + r.getTime_to(), style);
             
        }
    }
    
    private static void writeR2DataLines(XSSFWorkbook workbook,XSSFSheet sheet,List<Report2> r2) {
        
    	int rowCount = 1;
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Report2 r : r2) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(sheet,row, columnCount++, r.getS_no(), style);
            createCell(sheet,row, columnCount++, r.getService_id(), style);
            createCell(sheet,row, columnCount++, r.getUser_id(), style);
            createCell(sheet,row, columnCount++, r.getQuestion().toString(), style);
            createCell(sheet,row, columnCount++, r.getAnswer(), style);
            createCell(sheet,row, columnCount++, r.getCleaner_id(), style);
            createCell(sheet,row, columnCount++, r.getAddress(), style);
            createCell(sheet,row, columnCount++, r.getPincode(), style);
            createCell(sheet,row, columnCount++, r.getTime_from() +" - " + r.getTime_to(), style);
             
        }
    }
    
    private static void writeR3DataLines(XSSFWorkbook workbook,XSSFSheet sheet,List<Report3> r3) {
        
    	int rowCount = 1;
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Report3 r : r3) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(sheet,row, columnCount++, r.getS_no(), style);
            createCell(sheet,row, columnCount++, r.getService_id(), style);
            createCell(sheet,row, columnCount++, r.getUser_id(), style);
            createCell(sheet,row, columnCount++, r.getRating(), style);
            createCell(sheet,row, columnCount++, r.getFeedback(), style);
            createCell(sheet,row, columnCount++, r.getCleaner_id(), style);
            createCell(sheet,row, columnCount++, r.getAddress(), style);
            createCell(sheet,row, columnCount++, r.getPincode(), style);
            createCell(sheet,row, columnCount++, r.getTime_from() +" - " + r.getTime_to(), style);
             
        }
    }

    private static void writeR4DataLines(XSSFWorkbook workbook,XSSFSheet sheet,List<Report4> r4) {
        
    	int rowCount = 1;
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Report4 r : r4) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(sheet,row, columnCount++, r.getS_no(), style);
            createCell(sheet,row, columnCount++, r.getCleaner_id(), style);
            createCell(sheet,row, columnCount++, r.getFirst()+" "+r.getLast(), style);
            createCell(sheet,row, columnCount++, r.getDob(), style);
            createCell(sheet,row, columnCount++, r.getGender(), style);
            createCell(sheet,row, columnCount++, r.getPhone(), style);
            createCell(sheet,row, columnCount++, r.getCreated(), style);
            createCell(sheet,row, columnCount++, r.getProfile_status(), style);
             
        }
    }
    
    public static void exportR1(HttpServletResponse response,List<Report1> r1) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Completed Services");
        
        writeR1HeaderLine(workbook,sheet);
        writeR1DataLines(workbook,sheet,r1);
        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
    
    public static void exportR23(HttpServletResponse response,List<Report2> r2,List<Report3> r3) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("FeedBacks");
        
        writeR2HeaderLine(workbook,sheet);
        writeR2DataLines(workbook,sheet,r2);
        
        sheet = workbook.createSheet("Reviews");
        
        
        writeR3HeaderLine(workbook,sheet);
        writeR3DataLines(workbook,sheet,r3);
    
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
    
    
    public static void exportR4(HttpServletResponse response,List<Report4> r4) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Cleaners");
        
        writeR4HeaderLine(workbook,sheet);
        writeR4DataLines(workbook,sheet,r4);
        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}