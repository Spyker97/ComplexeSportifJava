
 
package complexesportifJava.entities;

/**
 *
 * @author Salma
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import complexesportifJava.services.AcademieService;

/**
 *
 * @author medom
 */
public class pdf {
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
       AcademieService su = new AcademieService();        
        List<Academie> list=su.afficherAcademie();   
        document.add(new Paragraph("La liste des academie:"));
        document.add(new Paragraph("     "));
         for(Academie u:list)
        {
            
      
        document.add(new Paragraph("Name:"+u.getName()));
          
        document.add(new Paragraph("nbr_seances :"+u.getNbr_seance()));
       
          
        
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }    
    
}