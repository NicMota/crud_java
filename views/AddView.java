package views;

import models.vo.PlayerVO;
import controllers.PlayerController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddView extends JInternalFrame
{   
    private JTextField teamTF,nameTF,ageTF;
    private JRadioButton activeRB,notActiveRB;
    private ButtonGroup buttonGroup;
    private JLabel teamL,nameL,ageL,activeL;
    private ArrayList<JTextField> fields;
    private JButton addButton;
    private JLabel status;
    private PlayerController playerController;
    
    static int posicao = 30;
    static int janela = 0;
    public AddView(PlayerController p)
    {
        super("" + (++janela) , true, true, true, true);
        this.playerController =  p;
        setContentPane(criaPainel());
        pack();

        setLocation(posicao * janela, posicao * janela);

    }
    public Container criaPainel()
    {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        

        painel.add(teamL);
        painel.add(teamTF);

        painel.add(nameL);
        painel.add(nameTF);

        painel.add(ageL);
        painel.add(ageTF);
        
        painel.add(activeL);
        painel.add(activeRB);
        painel.add(notActiveRB);

        painel.add(status);
        painel.add(addButton);

        return painel;
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
