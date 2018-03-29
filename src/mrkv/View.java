package mrkv;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;

public class View {
      
    private JFrame frame;
    private JButton b01,b02;

    
    public View(String text)
    {
        frame = new JFrame("View");                                    
                                                  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
        Container MainPane=frame.getContentPane();

        JLabel labelWEST = new JLabel("WEST");
        JLabel labelNORTH = new JLabel("NORTH",JLabel.CENTER);
        JLabel labelCENTER = new JLabel("CENTER",JLabel.CENTER);
        JLabel labelEAST = new JLabel("EAST");
        JPanel buttonpanel=new JPanel();
        
        frame.getContentPane().setLayout(new BorderLayout());
        MainPane.setLayout(new BorderLayout());
        MainPane.add(labelWEST,BorderLayout.WEST);
        MainPane.add(labelNORTH,BorderLayout.NORTH);
        MainPane.add(labelCENTER,BorderLayout.CENTER);
        MainPane.add(labelEAST,BorderLayout.EAST);
        
        b01 = new JButton("b01");
        buttonpanel.add(b01);
        
        b02 = new JButton("b02");
        buttonpanel.add(b02);
    
        frame.getContentPane().add(buttonpanel, BorderLayout.SOUTH);        
    }
        
    public JButton getb01(){
        return b01;
    }

    public JButton getb02(){
        return b02;
    }
    
    
    
    
}