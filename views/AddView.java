package views;

import models.vo.PlayerVO;
import controllers.PlayerController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddView extends JFrame
{   
    private JTextField teamTF,nameTF,ageTF;
    private JRadioButton activeRB,notActiveRB;
    private ButtonGroup buttonGroup;
    private JLabel teamL,nameL,ageL,activeL;
    private ArrayList<JTextField> fields;
    private JButton addButton;
    private JLabel status;
    private PlayerController playerController;
    
    public AddView(PlayerController p)
    {
        this.playerController =  p;
        initComponents();
    }

    public void initComponents()
    {
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        teamTF = new JTextField(20);
        nameTF = new JTextField(20);
        ageTF = new JTextField(20);

        teamL = new JLabel("Team:");
        nameL = new JLabel("Name: ");
        ageL= new JLabel("Age:");
        activeL = new JLabel("Active:");


        activeRB = new JRadioButton("Active",true);
        notActiveRB = new JRadioButton("Not Active",false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(activeRB);
        buttonGroup.add(notActiveRB);

        status = new JLabel("");
        addButton = new JButton("add");

        ButtonHandler handler = new ButtonHandler();
        addButton.addActionListener(handler);

        add(teamL);
        add(teamTF);

        add(nameL);
        add(nameTF);

        add(ageL);
        add(ageTF);
        
        add(activeL);
        add(activeRB);
        add(notActiveRB);

        add(status);
        add(addButton);
        
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            String team,name,age;
            boolean active;
            team = teamTF.getText();
            name = nameTF.getText();
            age = ageTF.getText();
            active = activeRB.isSelected();
            
          
            PlayerVO newPlayer = new PlayerVO(team,name,age,active);
             

            if(playerController.addPlayer(newPlayer))
            {
                status.setForeground(Color.blue);
                status.setText("Player Succesfully Added!            ");
            }    
            else
            {   
                 status.setForeground(Color.red);
                 status.setText("Error on Adding the Player!                   ");       
            }
               
            
            
            
            
        }
    }
}
