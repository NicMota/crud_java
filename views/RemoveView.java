package views;

import javax.swing.JFrame;

import controllers.PlayerController;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveView extends JInternalFrame
{
    private ArrayList<JTextField> fields;
    private JButton addButton;
    private JLabel status;
    private PlayerController playerController;

    static int posicao = 30;
    static int janela = 0;
    
    public RemoveView(PlayerController p)
    {   
        super("" + (++janela),true,true,true,true);
        this.playerController = p;
        
        setContentPane(criaPainel());
        pack();

        setLocation(posicao * janela, posicao * janela);
    }
    public Container criaPainel()
    {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel,BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));

        fields = new ArrayList<>();

        fields.add(new JTextField(20));
    
        addButton = new JButton("remove");
        status = new JLabel();
        ButtonHandler handler = new ButtonHandler();
        addButton.addActionListener(handler);

        for (JTextField f : fields) 
        {
            painel.add(f);
        }
        painel.add(status);
        painel.add(addButton);

        return painel;
    }
    public void initComponents()
    {
        
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
