import javax.swing.SwingUtilities;
import java.text.ParseException;
import java.util.concurrent.locks.ReentrantLock;

public class MainPersona {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {
                    {
                        new FormaPersonalData();
                        new FormaOrganizationData();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
