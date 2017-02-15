import javax.swing.SwingUtilities;
import java.text.ParseException;
public class MainPersona {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new FormaPersonalData();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    new FormaOrganizationData();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
