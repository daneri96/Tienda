package utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/subir")
public class subir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public subir() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file =null;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext servletContext;
		String filePath;
		String contentType;
		PrintWriter out;
	   FileItem fi;
	   List <FileItem> fileItems;
	   DiskFileItemFactory factory;
	   String fileName=null;
	   
		servletContext = getServletContext();
		filePath = servletContext.getInitParameter("file-upload");
	   	   
	   out=response.getWriter();

	   

	   contentType = request.getContentType();
	   if ((contentType.indexOf("multipart/form-data") >= 0)) {

	      factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("."));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
	      try{ 
	         // Parse the request to get file items.
	         fileItems = upload.parseRequest(request);

	         // Process the uploaded file items
	         Iterator<FileItem> i = fileItems.iterator();
/*
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>JSP File upload</title>");  
	         out.println("</head>");
	         out.println("<body>");*/
	         while ( i.hasNext () ) 
	         {
	            fi = (FileItem)i.next();
	            if ( !fi.isFormField () )	
	            {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();
	            fileName = fi.getName();
	            boolean isInMemory = fi.isInMemory();
	            long sizeInBytes = fi.getSize();
	            // Write the file
	            if( fileName.lastIndexOf("/") >= 0 ){
		            file = new File( filePath + 
		            fileName.substring( fileName.lastIndexOf("/"))) ;
	            }
	            else{
		            file = new File( filePath + "/" +
		            fileName.substring(fileName.lastIndexOf("/")+1)) ;
	            }
	            fi.write( file ) ;
	           
	         //   out.println("Uploaded Filename: " + filePath + "/" + fileName + "<br>");
	             response.sendRedirect("ModificarUsuario");
	            }
	         }
	       /*  out.println("</body>");
	         out.println("</html>");*/
	      }catch(Exception ex) {
	         System.out.println(ex);
	      }
	   }else{
	      out.println("<html>");
	      out.println("<head>");
	      out.println("<title>Servlet upload</title>");  
	      out.println("</head>");
	      out.println("<body>");
	      out.println("<p>No file uploaded</p>"); 
	      out.println("</body>");
	      out.println("</html>");
	   }
	out.close();
	

	

    byte buf[];
    int s, t;
    String rutaImagen;
    FileInputStream fis;
    SerialBlob campoImagen;
    Connection db;
    
    
    rutaImagen=filePath + "/" + fileName;
   

    try
     {
    	
        // Open the file
        fis = new FileInputStream(file);

        

        

      // Load the driver
      Class.forName("org.postgresql.Driver");
//System.err.println("1");
//      Class.forName("org.firebirdsql.jdbc.FBDriver");

      // Connect to database
      db = DriverManager.getConnection("jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-dalvcor", "a20-dalvcor", "a20-dalvcor");
//System.err.println("2");
//      db = DriverManager.getConnection("jdbc:firebirdsql:/l.IV.g/prf.gdb", "sysdba", "masterkey");
 // Método 1 y 2     db.setAutoCommit(false);	// Obligatorio para blob, hay que cerrar para commit


	  
      System.err.println("3");
      int codigo = 0;
	  codigo = Integer.parseInt(request.getParameter("codigo"));
      PreparedStatement pstmt = db.prepareStatement("UPDATE \"tienda\".usuarios SET imagen = ? where id =" + codigo);
      		

      //pstmt.setInt(1, 1);
/* 		Método 1 y 2 
 *      pstmt.setBlob(2, campoImagen);
 *      pstmt.execute();
 */

      pstmt.setBinaryStream(1, fis, (int)file.length());
      pstmt.executeUpdate();
      
      

      pstmt.close();
   
      db.close();
    
      System.out.println("4");
     }

    catch (Exception ex)
     {
    	System.err.println("excepcion" + ex);
     }
  
	}

	
}
