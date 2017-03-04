package com.akhil.struts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class Update extends ActionSupport implements ServletRequestAware,ApplicationAware{	
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	Map m;
	
	
	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
    }

    public void setApplication(Map m)
	{
		this.m=m;
	}   
	
	
	public String execute()
	{		
	try{
		Connection con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","password");
    PreparedStatement ps=null;	
   
    
	    ps=con.prepareStatement("select * from details where SNO=?");
	    String a = request.getParameter("fid");
		int k = Integer.parseInt(a);
		ps.setInt(1,k);			
	    //System.out.println("This is" +k);
		
		ResultSet res = ps.executeQuery();		
		List l=new ArrayList();
		
		while(res.next())
		{			
			m.put("a",res.getInt("sno"));
			m.put("b", res.getString("sname"));
			m.put("c",res.getString("scountry"));
		}
	
		ps.close();  		
		con.close();

	    } 
		    catch(Exception e){ 
 			e.printStackTrace(); 
 	}

		
			return SUCCESS;
		
	}


}
