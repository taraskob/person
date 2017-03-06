import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class MainTest {
    public static void main(String[] args) throws InstantiationException, InterruptedException, IOException,
            NoSuchFieldException, ParseException, InvocationTargetException {
        PersonaTest pt = new PersonaTest();
        try {
            pt.create_read();
            pt.write();
            pt.synchronizetest();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
