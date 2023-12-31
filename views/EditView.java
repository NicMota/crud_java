package views;

import models.vo.PlayerVO;
import controllers.PlayerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class EditView extends JInternalFrame
{
    private JTextField teamTF,nameTF,ageTF;
    private JRadioButton activeRB,notActiveRB;
    private ButtonGroup buttonGroup;

    private JLabel idL,teamL,nameL,ageL,activeL;
    private JComboBox playersComboBox;

    private JButton addButton;

    private ArrayList<String> playersName;
    private JLabel status;
    private PlayerController playerController;
    
    static int posicao = 30;
    static int janela = 0;
    public EditView(PlayerController p)
    {   
        super("" + (++janela),true,true,true,true);
        this.playerController =  p;
        setContentPane(criaPainel());
        pack();

        setLocation(posicao * janela, posicao * janela);
    }

    public Container criaPainel()
    {
        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
         
        playersName = playerController.getPlayersName();
        playersComboBox = new JComboBox<String>();
        for(int i = 0 ; i<playersName.size();i++)
        {   
            playersComboBox.addItem(playersName.get(i));
        }
    
        idL = new JLabel();
        idL.setVisible(false);
        teamTF = new JTextField(20);
        nameTF = new JTextField(20);
        ageTF = new JTextField(20);

        teamL = new JLabel("Team:");
        nameL = new JLabel(" Name: ");
        ageL= new JLabel("Age:");
        activeL = new JLabel("Active:");

        activeRB = new JRadioButton("Active",false);
        notActiveRB = new JRadioButton("Not Active",false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(activeRB);
        buttonGroup.add(notActiveRB);
            
        status = new JLabel("");
        addButton = new JButton("add");

        ButtonHandler handler = new ButtonHandler();
        addButton.addActionListener(handler);
        playersComboBox.addFocusListener(handler);
        
        painel.add(playersComboBox);

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
   
    private class ButtonHandler implements ActionListener,FocusListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            if(playersComboBox.getSelectedItem() != null)
            {
                int id = Integer.parseInt(idL.getText());
                String newTeam = teamTF.getText();
                String newName = nameTF.getText();
                String newAge = ageTF.getText();
                boolean isActive = activeRB.isSelected();

                if(playerController.editPlayer(id,newTeam,newName,newAge,isActive))
                {   
                    
                    playersName = playerController.getPlayersName();
                   
                    playersComboBox.setSelectedItem(newName);
                    status.setForeground(Color.blue);
                    status.setText("Player Succesfully Edited!            ");
                }else
                {
                    status.setForeground(Color.blue);
                    status.setText("Player Could Not be Edited!           ");
                }
            }
        }
        @Override
        public void focusLost(FocusEvent e)
        {
             if(playersComboBox.getSelectedItem() != null)
            {
                String playerSelectedName = playersComboBox.getSelectedItem().toString();
                PlayerVO playerSelected = playerController.searchPlayer(playerSelectedName);
                idL.setText(Integer.toString(playerSelected.getId()));
                teamTF.setText(playerSelected.getTeam());    
                nameTF.setText(playerSelected.getName());
                ageTF.setText(playerSelected.getAge());
                activeRB.setSelected(playerSelected.isActive());
                notActiveRB.setSelected(!playerSelected.isActive());
            }
        }
        @Override
        public void focusGained(FocusEvent e) {
           
           
        }
       
    }
}
