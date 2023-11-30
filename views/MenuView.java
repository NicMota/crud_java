package views;

import javax.swing.JFrame;


import controllers.PlayerController;
import models.vo.PlayerVO;

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
        buttons.add(new JButton("List"));
        buttons.add(new JButton("DeleteAll"));
        buttons.add(new JButton("CargaInicial"));
        buttons.add(new JButton("ContarJogadores"));

        

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
                case "List":
                    ListView ls = new ListView(playerController);

                    ls.setVisible(true);
                    add(ls);

                    break;
                case "DeleteAll":
                    int res = JOptionPane.showConfirmDialog(null, "voce tem certeza que deseja deletar todos");
                    
                    if(res==0)
                        playerController.deleteAll();
                    
                    break;
                case "CargaInicial":
                    int r = JOptionPane.showConfirmDialog(null, "Esse botao ira deletar todos os dados existentes( se houver ) e adicionar 5 jogadores pre definidos");
                    
                    if(r==0)
                    {   
                        playerController.deleteAll();
                        ArrayList<PlayerVO> cargaInicial = new ArrayList<>();
                        cargaInicial.add(new PlayerVO(1, "Furia", "Art", "25", true));
                        cargaInicial.add(new PlayerVO(2, "Furia", "Fallen", "23", true));
                        cargaInicial.add(new PlayerVO(3, "Furia", "Yurih", "21", true));
                        cargaInicial.add(new PlayerVO(4, "Furia", "Drop", "26", true));
                        cargaInicial.add(new PlayerVO(5, "Furia", "Kscerato", "27", true));
                        
                        for (PlayerVO carga : cargaInicial) {
                                playerController.addPlayer(carga);
                        }
                    
                        
                    }
                    break;
                case "ContarJogadores":
                    int playersNum = playerController.count();
                    JOptionPane.showMessageDialog(null,"Estao cadastrados: " + playersNum + " jogadores");
                    break;
            }
        }
    }
  
   
}