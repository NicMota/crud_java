package views;

import controllers.PlayerController;
import models.vo.PlayerVO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchView extends JInternalFrame
{   
    
    JTextField searchBar;
    JButton searchButton;
    JLabel status,rotulo;
    ArrayList<JLabel> res;
    PlayerController playerController;

    static int posicao = 30;
    static int janela = 0;
    
    public SearchView(PlayerController p)
    {
        super("" + (++janela),true,true,true,true);
        this.playerController = p;

        setContentPane(criaPainel());
        pack();

        setLocation(posicao * janela, posicao * janela);
    }

    public Container criaPainel()
    {   
        JPanel painel= new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        rotulo = new JLabel("entre com o nome do jogador que deseja pesquisar");
        searchBar = new JTextField(20);
        searchButton = new JButton("search");
        res = new ArrayList<>();
        
        ButtonHandler handler = new ButtonHandler();
        searchButton.addActionListener(handler);

        status = new JLabel();

        painel.add(rotulo);     
        painel.add(Box.createVerticalStrut(10));
        painel.add(searchBar);
        painel.add(Box.createVerticalStrut(20));
        painel.add(status);
        painel.add(Box.createVerticalStrut(20));
        for (JLabel r : res) {
            painel.add(r);
            painel.add(Box.createVerticalStrut(10));
        }
        painel.add(Box.createVerticalStrut(20));
        painel.add(searchButton);


        return painel;
    }
    
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            ArrayList<PlayerVO> list;
            String name = searchBar.getText();
           
            
            if(playerController.searchPlayer(name) != null)
            {   
                list = playerController.searchPlayer(name);
                
                for (PlayerVO searchedPlayer : list)
                {
                    
                    String text = String.format("Player Founded: Name: %s, Team: %s, Age: %s, Active: %s",searchedPlayer.getName(),searchedPlayer.getTeam(),searchedPlayer.getAge(),(searchedPlayer.isActive() ? "Yes" : "No"));
                    
                    res.add(new JLabel(text));
                }
               
                status.setForeground(Color.blue);
                status.setText("Founded Players:");
                criaPainel();
            
                return;
            }else 
            {   
                status.setForeground(Color.red);
                status.setText("Player Not Found");
                return;
            }
           
        }
    }
}
