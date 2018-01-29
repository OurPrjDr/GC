package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;

public class MonLogger {
	 
  // Cette méthode est appelée à chaque fois (et avant) qu'une méthode du package  est interceptée 
  public void logMethodEntry(JoinPoint joinPoint) throws IOException {
   
  	  File f = new File("~/logGestionContactBefore.txt");
	 
  	  PrintWriter out = null;
		
	  if(f.exists() && !f.isDirectory()) {
		try {
			out = new PrintWriter(new FileWriter(f, true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	  }
	  else {
		  out = new PrintWriter(f);
	  }
    
	  	//Object[] args = joinPoint.getArgs();
		
		// Nom de la méthode interceptée
		String name = joinPoint.getSignature().toLongString();
		StringBuffer sb = new StringBuffer(name + " called on: [" + LocalDateTime.now());
		
 
		
		sb.append("]");
		   
		    out.println(sb.toString());
		    out.flush();
		    out.close();
  }

  // Cette méthode est appelée à chaque fois (et après) qu'une méthode du package  est interceptée 
  // Elle reçoit en argument 'result' qui est le retour de la méthode interceptée
  public void logMethodExit(StaticPart staticPart, Object result) throws IOException {
  	 /* File f = new File("/home/hanitra/logGestionContactAfter.txt");
 	 
  	  PrintWriter out = null;
		
  	if(f.exists() && !f.isDirectory()) {
		try {
			out = new PrintWriter(new FileWriter(f, true));
			 String name = staticPart.getSignature().toLongString();
			  
			    System.out.println(name + " returning: [" + result + "]");
			
			out.write(name + " returning: [" + result + "]") + "on: [" + LocalDateTime.now());
 			out.close();
			    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	  }
	  else {
		  out = new PrintWriter(f);
	  }*/


  }
  
}