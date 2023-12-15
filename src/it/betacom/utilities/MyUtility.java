package it.betacom.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

public class MyUtility {
	private String path;

    public MyUtility(String path) {
        this.path = path;
    }

    public <T> void stampaPDF(String entity, List<T> lista, String fileName, EntityPrinter<T> printer) {
    	this.creaCartella();
    	
        Document document = new Document();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path + "\\" + fileName));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            document.add(new Paragraph(entity + " - Data: " + now.format(format)));

            String riga = "-----------------------------------------------------------";
            document.add(new Paragraph(riga));

            for (T element : lista) {
                document.add(new Paragraph(printer.printEntity(element)));
            }

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Documento PDF per " + entity + " creato con successo");
    }

    public <T> void stampaCSV(String entity, List<T> lista, String fileName, EntityPrinter<T> printer, String[] header) {
    	this.creaCartella();
    	
        try (FileWriter outputFile = new FileWriter(new File(path, fileName));
             CSVWriter writer = new CSVWriter(outputFile, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                                              CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String[] intestazione = {entity, " - Data: " + now.format(format)};
            writer.writeNext(intestazione);
            writer.writeNext(header);

            for (T element : lista) {
                String[] data = printer.printEntity(element).split(";");
                writer.writeNext(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void stampaTXT(String entity, List<T> lista, String fileName, EntityPrinter<T> printer) {
    	this.creaCartella();
    	
        try (PrintWriter writer = new PrintWriter(new File(path, fileName), "UTF-8")) {
            for (T element : lista) {
                writer.println(printer.printEntity(element));
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void stampaPDF(String entity, T element, String fileName, EntityPrinter<T> printer) {
    	this.creaCartella();
    	
        Document document = new Document();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path + "\\" + fileName));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            document.add(new Paragraph(entity + " - Data: " + now.format(format)));

            String riga = "-----------------------------------------------------------";
            document.add(new Paragraph(riga));

            document.add(new Paragraph(printer.printEntity(element)));

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Documento PDF per " + entity + " creato con successo");
    }

    public <T> void stampaCSV(String entity, T element, String fileName, EntityPrinter<T> printer, String[] header) {    	
    	this.creaCartella();
    	
        try (FileWriter outputFile = new FileWriter(new File(path, fileName));
             CSVWriter writer = new CSVWriter(outputFile, ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
                                              CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String[] intestazione = {entity, " - Data: " + now.format(format)};
            writer.writeNext(intestazione);
            writer.writeNext(header);

            String[] data = printer.printEntity(element).split(";");
            writer.writeNext(data);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void stampaTXT(String entity, T element, String fileName, EntityPrinter<T> printer) {
    	this.creaCartella();
    	
        try (PrintWriter writer = new PrintWriter(new File(path, fileName), "UTF-8")) {
            writer.println(printer.printEntity(element));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    private void creaCartella() {
    	File directory = new File(path);
        
        if (!directory.exists()) {
            directory.mkdir(); // Crea la sottocartella se non esiste
        }
    }
    
    public interface EntityPrinter<T> {
        String printEntity(T entity);
    }
    
}
