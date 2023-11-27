package views;

import javax.swing.JFrame;

import controllers.PlayerController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveView extends JFrame
{
    private ArrayList<JTextField> fields;
    private JButton addButton;
    private JLabel status;
    private PlayerController playerController;
    
    public RemoveView(PlayerController p)
    {
        this.playerController = p;
        initComponents();
    }

    public void initComponents()
    {
        setLayout(new FlowLayout());
        fields = new ArrayList<>();

        fields.add(new JTextField(20));
    
        addButton = new JButton("remove");
        status = new JLabel();
        ButtonHandler handler = new ButtonHandler();
        addButton.addActionListener(handler);

        for (JTextField f : fields) 
        {
            add(f);
        }
        add(status);
        add(addButton);
        
    }
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String name = fields.get(0).getText();
            if(playerController.removePlayer(name))
            {
                status.setForeground(Color.blue);
                status.setText("Player Removed Succesfully                            ");
            }
             
            else
            {   
                status.setForeground(Color.red);
                status.setText("Player Not Removed                                    ");
            }
                
            
        }
    }
}
