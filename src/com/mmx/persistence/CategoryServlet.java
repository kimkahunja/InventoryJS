package com.mmx.persistence;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.mmx.util.Category;
import com.mmx.util.CategoryInformation;
import com.sun.istack.internal.logging.Logger;

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String storeId;
    static Logger log = Logger.getLogger(CategoryServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	try{
    	  //get the store Id from the request
        storeId = request.getParameter("store_id");
 
        //get out Grid paging parameters
        String start = request.getParameter("start");
        String limit = request.getParameter("limit");
        
      //set content type
        response.setContentType("text/html");
         //printwriter to send the JSON response back
        PrintWriter out = response.getWriter();        
 
        //create a new JSON array to send the list of items
   
        
        log.info("info message");
        	//System.out.println("Kimotho Kahunja");
        //get arraylist of customers based on the request 
        CategoryInformation categoryInformation = new CategoryInformation();
        ArrayList<Category> categoryList = categoryInformation.getCategories(storeId, start, limit);
 
       
        //out.println("<h1>"+limit+"</h1>");
          JSONArray arrayObj=new JSONArray();
        //loop thru the array list to populate the JSON array
             for(int i=0;i<categoryList.size();i++){
 
            //get customer Object
            Category category = categoryList.get(i);
            //this creates a JSON object from bean object
            JSONObject categoryObj = JSONObject.fromObject(category);
            //add to array list
            arrayObj.add(categoryObj);
        }
 
        //Create a JSON object to wrap your JSOn array and provide the root element items
        JSONObject myObj = new JSONObject();
        //sets success to true
        myObj.put("success", true);
        //set the JSON root to items
        myObj.put("data", arrayObj);
        //set the total number of Items
        myObj.put("count", categoryInformation.getTotalCount(storeId));
 
        //convert the JSON object to string and send the response back
        out.println(myObj.toString());
        out.close();
    		
    	}
    	catch(Exception e){
    		response.setContentType("text/html");
            PrintWriter printWriter  = response.getWriter();
            printWriter.println("<h1>Kimotho Kahunja!</h1>");
    	}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
        //get the store Id from the request
        storeId = request.getParameter("store_id");
        //get the request type update or insert
        String action = request.getParameter("action");
 
        //if update
        if(action.trim().equalsIgnoreCase("update")){
            updateCustomer(request, response);
        }
        //if insert
        else if(action.trim().equalsIgnoreCase("insert")){
            insertCustomer(request, response);
        }
 
    }
    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	 
        //get the record Information 
        String recordInfo = request.getParameter("recordInfo");
 
        //parse JSON object and populate the Customer bean 
        JSONObject categoryObj = (JSONObject) JSONSerializer.toJSON(recordInfo); 
        Category category = (Category) JSONObject.toBean(categoryObj, Category.class);
 
        CategoryInformation customerInformation = new CategoryInformation();
        boolean success = customerInformation.updateCustomer(storeId,category);
 
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
 
        //send response back whether the request was successful
        JSONObject myObj = new JSONObject();
        myObj.put("success", success);
        out.println(myObj.toString());
        out.close();
 
    }
 
    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
 
        //get the record Information 
        String recordInfo = request.getParameter("recordInfo");
 
        //parse JSON object and populate the Customer bean 
        JSONObject categoryObj = (JSONObject) JSONSerializer.toJSON(recordInfo); 
        Category category = (Category) JSONObject.toBean(categoryObj, Category.class);
 
        CategoryInformation categoryInformation = new CategoryInformation();
        String categoryId = categoryInformation.insertCustomer(storeId,category);
 
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
 
        //send response back whether the request was successful
        JSONObject myObj = new JSONObject();
        if(categoryId.isEmpty()){
            myObj.put("success", false);
        }
        else {
            myObj.put("success", true);
            myObj.put("customerId", categoryId);
        }
        out.println(myObj.toString());
        out.close();
 
    }
}
