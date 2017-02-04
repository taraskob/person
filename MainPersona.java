import java.io.IOException;
import javax.swing.SwingUtilities;
public class MainPersona {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FormaPersonalData();
            }
        });
    }
}
