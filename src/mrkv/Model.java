package mrkv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;


public class Model {
    
    private int TotalMarklists;
    private ArrayList<String> strArray = new ArrayList<String>(); //array for RollSubScheme
    private ArrayList<String> pathArray = new ArrayList<String>(); //array containing full paths
    private ArrayList<String> nameArray = new ArrayList<String>(); //array containing file names only
    private ArrayList<String> rollArray=new ArrayList<String>();   //array Containg roll
    private ArrayList<String> markArray=new ArrayList<String>();   //array Containg roll
 
    
	 public ArrayList<String>  Div = new ArrayList<String>();
	 public ArrayList<Integer> Left = new ArrayList<Integer>();
	 public ArrayList<Integer> Right = new ArrayList<Integer>();
	 public ArrayList<String>  Subjects = new ArrayList<String>();
	
	 private String Division,Examination, Subject, Examiner,Date,MaxMarks;
	 public String JarFilePath;
	 
	 void SetParameters()
	 {	 String temp1[],temp2[];
	     Div.removeAll(Div);
	     Left.removeAll(Left);
	     Right.removeAll(Right);
	     Subjects.removeAll(Subjects);
	  for(int i=0;i<strArray.size();i++)
	    { temp1= strArray.get(i).split("#");
         	  Div.add(temp1[0]);
	      temp2=temp1[1].split("-");
	    Left.add(Integer.parseInt(temp2[0].replaceAll("[^0-9.]","")));
	    Right.add(Integer.parseInt(temp2[1].replaceAll("[^0-9.]","")));
	    Subjects.add(temp1[2]);
	   } 

	 for(int i=0;i<Div.size();i++)
	 { System.out.print(Div.get(i)+"  ");
	   System.out.print(Left.get(i)+"  ");
	   System.out.print(Right.get(i)+"  ");
	   System.out.println(Subjects.get(i));
	 }
	 
	 
	 }
    
    public String getJarPath()
    {
    	File f = new File(System.getProperty("java.class.path"));
     	File dir = f.getAbsoluteFile().getParentFile();
        JarFilePath=dir.toString();
        
     	return  JarFilePath;
    }


    public void LoadRollSubjectSchemeToArray(String filenamewithpath)
    {       strArray.removeAll(strArray);
    
        	BufferedReader reader=null;
    		try {
    			reader = new BufferedReader(new FileReader(filenamewithpath));
    		} catch (FileNotFoundException e1) 
    		{
    		
    			e1.printStackTrace();
    		}
     				
    		String line = null;
        	try { while ((line = reader.readLine()) != null) 
    			{
    			 if(!line.contains("#")) continue;
    			 strArray.add(line);
    			 System.out.println(line);
    			}
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    }


    
    

    public int GetAllFiles(String JarFilePath)
    { 
//     getJarPath();
  	  FilenameFilter mrkFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".mrk")) {
					return true;
				} else {
					return false;
				}
			}
		};
  	  
  	  pathArray.removeAll(pathArray);
  	  nameArray.removeAll(nameArray);
  	  File folder = new File(JarFilePath);
  	  File[] listOfFiles = folder.listFiles(mrkFilter);
  	      for (int i = 0; i < listOfFiles.length; i++) {
  	        if (listOfFiles[i].isFile()) 
  	        {  
  	           pathArray.add(listOfFiles[i].getAbsolutePath());
  	           nameArray.add(listOfFiles[i].getName());
  	           
  	         } 
  	      }
  	  TotalMarklists=pathArray.size();
       System.out.println(TotalMarklists);
  	  return TotalMarklists;
    }


    public void LoadMarkListFileToStrArray(int currentindex)
    {       strArray.removeAll(strArray);
    		markArray.removeAll(markArray);
    		rollArray.removeAll(rollArray);
    
        	BufferedReader reader=null;
    		try {
    			reader = new BufferedReader(new FileReader(pathArray.get(currentindex)));
    		} catch (FileNotFoundException e1) 
    		{
    		
    			e1.printStackTrace();
    		}
     				
    		String line = null;
        	try { while ((line = reader.readLine()) != null) 
    			{
    			 
    			 strArray.add(line);
    			}
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
         }
    

    public void ExtractAllHeaderFields()
    {	String temp[],stemp;
	    stemp=strArray.get(7);
	    temp=stemp.split(":");
	    int TotalSets=Integer.parseInt(temp[1].replaceAll("[^0-9.]",""));
    
	    stemp=strArray.get(13+3*TotalSets); temp=stemp.split(":");
    	Examiner=temp[1].trim();
    	
    	stemp=strArray.get(15+3*TotalSets); temp=stemp.split(":");
    	Division=temp[1].trim();
    	
    	stemp=strArray.get(17+3*TotalSets); temp=stemp.split(":");
    	Subject=temp[1].trim();
    	
    	
    	stemp=strArray.get(18+3*TotalSets); temp=stemp.split(":");
    	Examination=temp[1].trim();
    	
    	stemp=strArray.get(19+3*TotalSets); temp=stemp.split(":");
    	MaxMarks=temp[1].trim();
    	stemp=strArray.get(20+3*TotalSets); temp=stemp.split(":");
    	Date=temp[1].trim();

		for(int i=28+3*TotalSets;i<strArray.size();i++) 
		{
		stemp=strArray.get(i); temp=stemp.split(":");
    	rollArray.add(temp[0].trim());markArray.add(temp[1].trim());
		
		}
		
		

    	
    }

    
    public void ProcessCurrentMarklist()
    {
    	System.out.println("processing");
    	System.out.println(rollArray.size());
    for(int i=0;i<rollArray.size();i++)
	{  
    	String str=rollArray.get(i);
    	int introll=Integer.parseInt(str);
    	if(Vacant(introll)) System.out.println(introll);
		
    	//if(markArray.get(i).contains("AB")) 
	    //show(rollArray.get(i)+":"+markArray.get(i));
	}
    }
    
    
    private boolean Vacant(int roll)
    { //boolean vacant=true;
      for(int i=0;i< Div.size();i++)
          {if(roll>=Left.get(i) && roll<=Right.get(i)) 
    	    return false; // Student exists NOT vacant
    	 }
       return true;
    	
    }
    

    public void SaveList(int x) throws IOException
    {   FileWriter f0=null;
    	try {f0 = new FileWriter(pathArray.get(x));	} catch (IOException e1) {e1.printStackTrace();	}
        String newLine = System.getProperty("line.separator");
        
        String temp[],stemp;
	    stemp=strArray.get(7);
	    temp=stemp.split(":");
	    int TotalSets=Integer.parseInt(temp[1].replaceAll("[^0-9.]",""));
        
        
        strArray.set(15+3*TotalSets,"Div         :"+Division); 
    	strArray.set(17+3*TotalSets,"Subject     :"+Subject);
    	strArray.set(18+3*TotalSets,"Examination :"+Examination);
    	
    	for(int i=0;i<strArray.size();i++) 
    		{f0.write(strArray.get(i));f0.write(newLine);}
    	
    	f0.close();
    	
    	/* Rename Routine - Implement Later 
    	 
    	 
    	 File oldfile = new File(pathArray.get(x));
         File newfile = new File(JarFilePath+"/"+Division+"-"+Examination+"-"+Subject+"-[Rectified]-"+Examiner+".mrk");

         if(oldfile.renameTo(newfile)) {
            System.out.println("File name changed succesful");
         } else {
            System.out.println("Rename failed");
         } 
    	*/
    }
    

    
    
    
    

}