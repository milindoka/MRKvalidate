package mrkv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Controller {

    private Model model;
    private View view;
    private String jarpath="";
    private ActionListener b01,b02;
    
    
//////Easy display message for string, int, long
public void show(String msg) 
{JOptionPane.showMessageDialog(null, msg);}
public void show(int msg)
{JOptionPane.showMessageDialog(null, msg);}
public void show(long msg)
{JOptionPane.showMessageDialog(null, msg);}

    
    
    
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
       // String path;
	     System.out.println(model.getJarPath()); ///set JAR path in model variable path;
    }
    
    public void contol()
    {        
        b01 = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) {                  
                  linkBtnAndLabel();
              }
        };                
        view.getb01().addActionListener(b01);
        
        b02 = new ActionListener()
        {
              public void actionPerformed(ActionEvent actionEvent) {                  
                  CheckVacant();
              }
        };                
        view.getb02().addActionListener(b02);
        
        
        linkBtnAndLabel();
//        CheckVacant();
        
    }
    
    private void linkBtnAndLabel()
    {   jarpath=model.getJarPath();
        String schemepath=jarpath+"/Scheme.txt";
        model.LoadRollSubjectSchemeToArray(schemepath);
        model.SetParameters();
    }    



    private void CheckVacant()
    { 
    	int totalfiles=model.GetAllFiles(jarpath);
    	for(int fileindex=0;fileindex<totalfiles;fileindex++)
    	{
    	model.LoadMarkListFileToStrArray(fileindex);
    	model.ExtractAllHeaderFields();
       // model.ProcessCurrentMarklist();
    	try {
			model.SaveList(fileindex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	show("Pause");
    	}
    	/*
    	String errorMessage = "";
    	do {
    	    // Show input dialog with current error message, if any
    	    String stringInput = JOptionPane.showInputDialog(errorMessage + "Enter number.");
    	    try {
    	        int number = Integer.parseInt(stringInput);
                     	        
    	        TraceNumber(number);
    	        
    	    } catch (NumberFormatException e) {
    	        // The typed text was not an integer
    	        errorMessage = "The text you typed is not a number.\n";
    	    }
    	} while (!errorMessage.isEmpty());
       */
    
    }

    



}