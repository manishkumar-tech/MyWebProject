

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class MyServlet
 */
@MultipartConfig
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "/tmp/uploads";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		
		 // Write to browser response
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("fasfafafff");
        
        
    	//	try {
        //
//        			 JSONObject json = new JSONObject();
//        		        json.put("message", "Hello from server");
//        		        json.put("status", "success");
        		        
        		
//        		    response.addHeader("Access-Control-Allow-Origin", "*");
//        		    response.setContentType("application/json");
//        		    response.setCharacterEncoding("UTF-8");
//        		    response.getWriter().print("kubbjbb");
//        		    response.getWriter().flush();
//        		   } catch (Exception e) {
//        		     e.printStackTrace();
//        		  }
        		
        		
        		//response.getWriter().append("test");
        		// TODO Auto-generated method stub
        		//doGet(request, response);
        		
        		//System.out.println("manishlog"+request.getParameter("email"));
        		
		
		//  Part part = request.getPart("override");
        		
        		String email = request.getParameter("email");
        		
        		String pwd = request.getParameter("psw");
        		
        		// Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        		//    String filename = filePart.getName();
        		  //  InputStream filecontent = filePart.getInputStream();
        		
        		//System.out.println(email);
        		//System.out.println(pwd);
        		
        		
//        	    String contentType = request.getContentType();
//        	    
//        	    System.out.println(contentType);
//        	    
//        	    
//        	    try (BufferedReader reader = request.getReader()) {
//        	    	
//        	        Gson gson = new Gson();
//        	        StudentModel newStt = gson.fromJson(reader, StudentModel.class);
//        	        response.getWriter().append(gson.toJson(newStt).toString());
//        	           
//
//        	    } catch (IOException ex) {
//        	    	//response.setAttribute("message", "There was an error: " + ex.getMessage());
//        	    }
		
		// for (Part part : request.getParts()) {
        		
        	//Part filePart=	request.getPart("file");
			// String fileName = getFileName(filePart);
			 
			// System.out.println(fileName);
		// }
	    
        		    
//			 File uploadDir = new File(UPLOAD_DIR);
//			 if (!uploadDir.exists()) uploadDir.mkdirs();
//			 
//
//
//
//		        for (Part part : request.getParts()) {
//		            // Check if it's a file part
//		            if (part.getName().equals("file") && part.getSize() > 0) {
//		                String fileName = getFileName(part);
//		                File file = new File(uploadDir, fileName);
//
//		                try (InputStream input = part.getInputStream();
//		                     FileOutputStream out = new FileOutputStream(file)) {
//		                    input.transferTo(out);
//		                }
//
//		                System.out.println("Saved: " + file.getAbsolutePath());
//		                
//		            }
//		        }
//
//		        response.setContentType("text/plain");
//		        response.getWriter().println("Uploaded by: " + email);
		        
		        
		        
		        
		      //  response.getWriter().println("File saved to: " + file.getAbsolutePath());
//        		
//        		
        		//String url="jdbc:mysql://localhost:3306/student?useSSL=false&serverTimezone=UTC";
        		//String url="jdbc:postgresql://localhost:5432/student";
        		//postgresql://postgres:kzVwDENhXytNzAoToxuRnbNYXPFfZpqE@ballast.proxy.rlwy.net:23184/railway
        		String url="jdbc:postgresql://ballast.proxy.rlwy.net:23184/railway";
        	//	String uname="postgres";
        	//	String pass="root";
        		String uname="postgres";
            		String pass="kzVwDENhXytNzAoToxuRnbNYXPFfZpqE";
        		
        		//String query = "select password from student_table where email='manish@gmail.com'";
        		//String query = "insert into student_table values ("+email+","+pwd+")";
        		//String query = "INSERT INTO student_table (email, password) VALUES ('manish@gmail.com', 'pass123')";
        		try {
        			
        			
        			//Class.forName("com.mysql.cj.jdbc.Driver");
        			Class.forName("org.postgresql.Driver");
        			
        			System.out.println("Driver loaded successfully!");
        			
        			Connection con=	DriverManager.getConnection(url,uname,pass);
        			
        			System.out.println("connection done");
        			
        			// Define the INSERT query with placeholders
                   String query = "INSERT INTO student_table (email, password) VALUES (?, ?)";
        
                    // Create a PreparedStatement
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, email);
                    pstmt.setString(2, pwd);
        
                    // Execute the query
                    int rowsAffected = pstmt.executeUpdate();
                    
                    
                    if(rowsAffected > 0) {
                    	JSONObject jsonObject=new JSONObject();
                    	jsonObject.put("success", "inserted");
                    	
                    	response.getWriter().println(jsonObject.toString());
                    }
                    
        
                    // Send response to the client
//                    response.setContentType("text/html");
//                    if (rowsAffected > 0) {
//                        response.getWriter().write("Data inserted successfully! Rows affected: " + rowsAffected);
//                    } else {
//                        response.getWriter().write("Insert failed.");
//                    }
        			
                    
                   // JSONArray jsonArray=new JSONArray();
                    
                   // JSONObject jsonObject=new JSONObject();
                    
        	        
        	       // String queryStList = "SELECT * from student_table";
        	        
        	     //   Statement stmt = con.createStatement();
        	     // ResultSet rs=  stmt.executeQuery(queryStList);
        	      
        	   //   while (rs.next()) {
				//	jsonObject.put("email", rs.getString("email"));
				//	jsonObject.put("password", rs.getString("password"));
					
				//	jsonArray.put(jsonObject);
					
					//System.out.println(rs.getString("email"));
					//System.out.println(rs.getString("password"));
				//}
        	    //    response.getWriter().println(jsonArray.toString());
        			
        	    //    stmt.close();
        	        
        	        
        		//	con.close();
        			
        			
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
		
	}

	
	 private String getFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        if (contentDisp == null) return null;
	        for (String token : contentDisp.split(";")) {
	            if (token.trim().startsWith("filename")) {
	                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
	            }
	        }
	        return null;
	    }
	 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
