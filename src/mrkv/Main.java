package mrkv;

import java.io.File;



public class Main 
{
	public static void main(String[] args) 
	{    
		
	    MRKvalidate m=new MRKvalidate();    
	    String jarpath=m.getJarPath();  
	    String schemepath=jarpath+"/Scheme.txt";
	    File tmpDir = new File(schemepath);
	    boolean exists = tmpDir.exists();
	    if(!exists) 
	      { m.jb.setText("  Scheme.txt does not exits. Program will close");
            try{Thread.sleep(4000);}catch(Exception e){}
            System.exit(0);
	      }
	 	
	    m.LoadRollSubjectSchemeToArray(schemepath);
	    m.SetParameters();              //
	    int TotalMarklists=m.GetAllFiles();
	    m.setVisible(true);
	    m.Sort();
	   // m.CheckVacant();
	    m.iterate();
	}    



	
	
	

}

