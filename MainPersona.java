import javax.swing.SwingUtilities;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainPersona {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        {
                            new FormaPersonalData();
                            new FormaOrganizationData();
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
