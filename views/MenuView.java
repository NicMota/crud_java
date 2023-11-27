package views;

import javax.swing.JFrame;

import controllers.PlayerController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MenuView extends JFrame
{
    private ArrayList<JButton> buttons;
    private PlayerController playerController;
    public MenuView()
    {   
        this.playerController = new PlayerController();
        initComponents();
    }
    public void initComponents()
    {   
        setLayout(new FlowLayout());

        buttons = new ArrayList<>();
        buttons.add(new JButton("Add"));
        buttons.add(new JButton("Search"));
        buttons.add(new JButton("Remove"));
        buttons.add(new JButton("Edit"));
        

        ButtonHandler handler = new ButtonHandler();

        for(JButton b : buttons)
        {
            b.addActionListener(handler);
            add(b);
        }
        
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch(e.getActionCommand())
            {
                case "Add":
                    AddView ad = new AddView(playerController);
                  
                    ad.setVisible(true);

                    add(ad);
                    break;
                case "Remove":
                    RemoveView rm = new RemoveView(playerController);
                    rm.setVisible(true);

                    add(rm);
                    break;
                case "Search":
                    SearchView sr = new SearchView(playerController);
                    sr.setVisible(true);
                    add(sr);
                    break;
                case "Edit":
                    EditView ed = new EditView(playerController);
                   
                    ed.setVisible(true);
                    add(ed);

                    break;
            }
        }
    }
  
   
}