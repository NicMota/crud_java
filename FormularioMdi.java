import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
 
import java.awt.event.*;
import java.awt.*;
 
 
public class FormularioMdi extends JFrame implements ActionListener
{
    // Declara componetes
    JDesktopPane painel;
 
    public FormularioMdi() 
    {
        // herda classe
        super("Java - Formularios - MDI - JFrame / JDesktopPane / JInternalFrame");
         
        // Define tamanho da janela principal
        int bordas = 50;
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(bordas, bordas, tela.width  - bordas *2 , tela.height - bordas *2);
         
        // Cria painel principal
        painel = new JDesktopPane(); 
        painel.setBackground(Color.LIGHT_GRAY);
                 
        setContentPane(painel);
        setJMenuBar(createMenuBar());        
    }
 
    protected JMenuBar createMenuBar()
    {
        // Cria barra de menu
        JMenuBar barra = new JMenuBar();
         
        // Cria menu
        JMenu menu = new JMenu("Formularios");        
        barra.add(menu);
 
        // Cria itens dos menus
        JMenuItem item = new JMenuItem("Novo");
        item.setActionCommand("novo");
        item.addActionListener(this);
        menu.add(item);
 
        //Cria segundo item
        item = new JMenuItem("Sair");
        item.setActionCommand("sair");
        item.addActionListener(this);
        menu.add(item);
 
        return barra;
    }
 
     
    public void actionPerformed(ActionEvent e) 
    {
        // Cria eventos do menu
        if (e.getActionCommand() == "novo")
        { 
            criaFormulario();
        } 
        else
        {
            sair();
        }
    }
 
    protected void sair() 
    {
        // Fecha sistema
        System.exit(0);
    }
     
    protected void criaFormulario()
    {
        // Cria formulario filho
        FormularioFilho frame = new FormularioFilho();
        frame.setVisible(true); 
         
        // Adiciona janela filho na janela pai
        painel.add(frame);
        try
        {
            // Seta foco
            frame.setSelected(true);
        }
        catch (java.beans.PropertyVetoException e)        
        {
             
        }
    }
        
     
    private static void criaGUI() 
    {     
        // Decora barra da janela
        JFrame.setDefaultLookAndFeelDecorated(true);
 
        // Cria formulario filho     
        FormularioMdi formulario = new FormularioMdi();
        formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        // Exibe formulario
        formulario.setVisible(true);
    }
 
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() 
            {
                // Chama GUI
                criaGUI();
            }
        });
    }
}