import views.MenuView;
import javax.swing.JFrame;
public class App{
    
    
    public static void main(String[] args)
    {
        MenuView m = new MenuView();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(700,700);
        m.setVisible(true);
    }
}
