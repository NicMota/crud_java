import java.awt.Container;
import java.awt.Dimension;
 
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class FormularioFilho extends JInternalFrame
{   
    // Declara componentes
    JLabel rotulo;
    JTextField campo;
    JButton botao;
     
    // Declara variaveis
    static int formulario = 0;
    static int posicao = 30;
     
    public FormularioFilho()
    {       
        // Herda da classe filho
        super("Formulario Filho - Instancia :" + (++formulario), true, true, true, true);
 
        // Cria painel
        setContentPane(criaPainel());
        pack();
         
        // Define posição do formulario
        setLocation(posicao * formulario, posicao * formulario);        
         
    }
     
    public Container criaPainel()
    {
        // Cria painel
        JPanel painel = new JPanel(); 
         
        // Define layout
        painel.setLayout(new BoxLayout(painel, BoxLayout.PAGE_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
         
        // Cria componentes
        rotulo = new JLabel("Digite Seu nome:");
        campo = new JTextField();
        botao = new JButton("Ok");
         
        // define tamanho do campo
        campo.setPreferredSize(new Dimension(300,20));
         
        // Adiciona componentes ao painel   
        painel.add(rotulo);     
        painel.add(Box.createVerticalStrut(10));
        painel.add(campo);
        painel.add(Box.createVerticalStrut(20));
        painel.add(botao);
         
        return painel;
    }
     
    private static void criaGUI() 
    {
        // Cria GUI (Janela Filho)
        JFrame.setDefaultLookAndFeelDecorated(true);
      
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