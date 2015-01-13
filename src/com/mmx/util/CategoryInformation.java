package com.mmx.util;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;     
import com.sun.istack.internal.logging.Logger;

public class CategoryInformation {
	Connection conn = null;            
	 PreparedStatement stmt = null;     
	 String sql = null;
	 BigDecimal myId;	 
	 CallableStatement cs = null;
	 static Logger log = Logger.getLogger(CategoryInformation.class);
	public CategoryInformation(){
		super();
	}
	 //Get list of Categories for a specific store Id with Extjs paging paramaters start and limit
    public ArrayList<Category> getCategories(String storeId, String start, String limit) { 
 
        ArrayList<Category> categoryList = new ArrayList<Category>();   
 
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
           conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
        	
        	/*Class.forName(driver).newInstance();
            System.out.println("333333333");
            conn = DriverManager.getConnection(url, username,
                    password);*/
            sql = "SELECT  CAT_CODE, CAT_SHT_DESC, CAT_DESCRIPTION FROM inv_categories";                      
 
            stmt = conn.prepareStatement(sql);
            
 
            ResultSet rs = stmt.executeQuery(); 
 
            while(rs.next()){ 
                Category category = new Category();
                category.setCatCode(rs.getBigDecimal(1));
                category.setCatShtDesc(rs.getString(2).trim());
                category.setCatDescription(rs.getString(3).trim());
                categoryList.add(category);
            }                                                                        
 
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              
 
 
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){System.out.println(e);}                      
 
        finally {                                                       
             
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
 
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }
        return categoryList;
 
    } 
    //insert new Category information
    public String insertCustomer(String storeId, Category category) { 
 
        String categoryCode = "";
      //  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String todaysDate = dateFormat.format(System.currentTimeMillis());
        
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
 
            sql = "INSERT into inv_categories " +
            "(CAT_CODE,CAT_SHT_DESC,CAT_DESCRIPTION)" +
            " values(null,?,?)";
            stmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,category.getCatShtDesc()); 
            stmt.setString(2,category.getCatDescription());
            stmt.executeUpdate();
 
            // Using the getGeneratedKeys() method to retrieve
            // the key(s). In this case there is only one key column
            ResultSet keyResultSet = stmt.getGeneratedKeys();
            int newCategoryCode = 0;
            if (keyResultSet.next()) {
            	newCategoryCode = (int) keyResultSet.getInt(1);
            	categoryCode = String.valueOf(newCategoryCode);
            }
 
            stmt.close();                                                             
            stmt = null;                                                              
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){
 
            System.out.println(e);
 
        }                      
 
        finally {                                                       
             
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
 
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }              
 
        return categoryCode;
 
    }  
    //this method gets us the Total Number of Items that we have in our database
    public int getTotalCount(String storeId) { 
 
        int totalCount = 0; 
 
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
 
            sql = "Select count(*) from inv_categories";                      
 
            stmt = conn.prepareStatement(sql);
           // stmt.setInt(1,Integer.parseInt(storeId)); 
 
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){ 
                totalCount = rs.getInt(1);
                break;
            }                                                                         
 
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              
 
 
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){System.out.println(e);}                      
 
        finally {                                                       
             
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
 
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }              
 
        return totalCount;
 
    }
  //update the Category information
    public boolean updateCustomer(String storeId, Category category) { 
 
        boolean success = true;
 
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
 
            sql = "UPDATE inv_categories set CAT_SHT_DESC = ?, CAT_DESCRIPTION = ? " +
            " where  CAT_CODE = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,category.getCatShtDesc()); 
            stmt.setString(2,category.getCatDescription());            
            stmt.setBigDecimal(3,category.getCatCode());
 
            stmt.executeUpdate();
 
            stmt.close();                                                             
            stmt = null;                                                              
            conn.close();                                                             
            conn = null;                                                   
 
        }                                                               
        catch(Exception e){
 
            success = false;
            System.out.println(e);
 
        }                      
 
        finally {                                                       
             
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
 
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }              
 
        return success;
 
    }  
}
