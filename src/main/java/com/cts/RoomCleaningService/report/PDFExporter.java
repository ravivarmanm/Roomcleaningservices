package com.cts.RoomCleaningService.report;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

// R1 - services report
// R2 - feedbacks report
// R3 - reviews report
// R4 - cleaners report
 
public class PDFExporter {

    private static void writeR1TableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("S No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Service Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Cleaner Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("RoomCount", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Pincode", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Contact", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Registered At", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Completed At", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Time Slot", font));
        table.addCell(cell);   
    }

	private static void writeR2TableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
        
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
   
        
        cell.setPhrase(new Phrase("S No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Service Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User Id", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Question", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Answer", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Cleaner Id", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Pincode", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Time Slot", font));
        table.addCell(cell);   
 
	}

	private static void writeR3TableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("S No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Service Id", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User Id", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Rating", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Feedback", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Cleaner Id", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Pincode", font));
        table.addCell(cell);   
        
        cell.setPhrase(new Phrase("Time Slot", font));
        table.addCell(cell);   
            
    }

	private static void writeR4TableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("S No", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Cleaner Id", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("DOB", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Phone", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Registered At", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Profile Status", font));
        table.addCell(cell);
	}
    
	
    private static void writeR1TableData(PdfPTable table,List<Report1> r1) {
        for (Report1 report:r1) {
            table.addCell(String.valueOf(report.getS_no()));
            table.addCell(report.getService_id());
            table.addCell(report.getUser_id());
            table.addCell(report.getCleaner_id());
            table.addCell(report.getRoom_count());
            table.addCell(report.getAddress());
            table.addCell(report.getPincode());
            table.addCell(report.getContactNumber());
            table.addCell(report.getCreated());
            table.addCell(report.getModified());
            table.addCell(report.getTime_from() +" - "+report.getTime_to());
       }
    }

	private static void writeR2TableData(PdfPTable table,List<Report2> r2) {
		   for (Report2 report:r2) {
	            table.addCell(String.valueOf(report.getS_no()));
	            table.addCell(report.getService_id());
	            table.addCell(report.getUser_id());
	            table.addCell(report.getQuestion());
	            table.addCell(report.getAnswer());
	            table.addCell(report.getCleaner_id());
	            table.addCell(report.getAddress());
	            table.addCell(report.getPincode());
	            table.addCell(report.getTime_from() +" - "+report.getTime_to());
	       }
    }

	private static void writeR3TableData(PdfPTable table,List<Report3> r3) {
		   for (Report3 report:r3) {
	            table.addCell(String.valueOf(report.getS_no()));
	            table.addCell(report.getService_id());
	            table.addCell(report.getUser_id());
	            table.addCell(String.valueOf(report.getRating()));
	            table.addCell(report.getFeedback());
	            table.addCell(report.getCleaner_id());
	            table.addCell(report.getAddress());
	            table.addCell(report.getPincode());
	            table.addCell(report.getTime_from() +" - "+report.getTime_to());
	       }
	}

	private static void writeR4TableData(PdfPTable table,List<Report4> r4) {
		for (Report4 report:r4) {
            table.addCell(String.valueOf(report.getS_no()));
            table.addCell(report.getCleaner_id());
            table.addCell(report.getFirst() +" "+report.getLast());
            table.addCell(report.getDob());
            table.addCell(report.getGender());
            table.addCell(report.getPhone());
            table.addCell(report.getCreated());
            table.addCell(report.getProfile_status());
  	      
       }
    }
     
    public static void exportR1(HttpServletResponse response,List<Report1> r1) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Completed Services Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100);
        table.setWidths(new float[] {5,10,10,10,5,20,8,12,12.5f,12.5f,15});
        table.setSpacingBefore(5);
         
        writeR1TableHeader(table);
        writeR1TableData(table,r1);
         
        document.add(table);
         
        document.close();
         
    }

	public static void exportR23(HttpServletResponse response,List<Report2> r2,List<Report3> r3) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of FeedBacks", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {5,10,10,12,18,10,16,8,12});
        table.setSpacingBefore(10);
         
        writeR2TableHeader(table);
        writeR2TableData(table,r2);
     
        document.add(table);
        
        p = new Paragraph("List of Reviews", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
        
        table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new float[] {5,10,10,12,18,10,16,8,12});
        table.setSpacingBefore(10);
     
        
		writeR3TableHeader(table);
        writeR3TableData(table,r3);
        
		document.add(table);
         
        document.close();
         
    }

	public static void exportR4(HttpServletResponse response,List<Report4> r4) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Cleaners", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {6,13,21,10,10,15,15,10});
        table.setSpacingBefore(10);
         
        writeR4TableHeader(table);
        writeR4TableData(table,r4);
         
        document.add(table);
         
        document.close();
         
    }
}