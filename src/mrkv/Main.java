package mrkv;



public class Main 
{
	public static void main(String[] args) 
	{    
		
	    MRKvalidate m=new MRKvalidate();    
	    String jarpath=m.getJarPath();
	    String schemepath=jarpath+"/Scheme.txt";
	    m.LoadRollSubjectSchemeToArray(schemepath);
	    m.SetParameters();              //
	    int TotalMarklists=m.GetAllFiles();
	    m.setVisible(true);
	    m.Sort();
	   // m.CheckVacant();
	    m.iterate();
	}    



	
	
	

}

