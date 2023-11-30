package views;

import models.vo.PlayerVO;
import controllers.PlayerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListView extends JInternalFrame
{
   
    private ArrayList<JLabel> playersLabelList;
    private ArrayList<PlayerVO> players;

    private PlayerController playerController;
    
    static int posicao = 30;
    static int janela = 0;
    public ListView(PlayerController p)
    {   
    
        super("" + (++janela),true,true,true,true);
        this.playerController =  p;
        this.players = this.playerController.getPlayersList();
        setContentPane(criaPainel());
        pack();

        setLocation(posicao * janela, posicao * janela);
    }

    public Container criaPainel() 
    {
        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        playersLabelList = new ArrayList<JLabel>();
        
        String format = "Id: %d Nome: %s, Time: %s, Idade: %s, Ativo:%s";
        for (PlayerVO player : players) 
        {   
            String text = String.format(format, player.getId(),player.getName(),player.getTeam(),player.getAge(),(player.isActive() ? "Yes" : "No"));
            playersLabelList.add(new JLabel(text));
        }
        
        for (JLabel label : playersLabelList) 
        {
            painel.add(label);   
        }
        return painel;

    }
   
    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   
            
        }
    
    }
}
