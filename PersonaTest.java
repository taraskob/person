import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaTest {
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
    private Controller ctrl = Controller.getController();
    private List<ChangeHandler> listener = new ArrayList<>();
    private Organization organization = new Organization();
    private Person person = new Person();
    ArrayList<Storable> objectlist = new ArrayList<>();

    {
        objectlist.add(person);
        objectlist.add(organization);
    }

    private Storage storage = new Storage();

    void create_read() throws InstantiationException, InterruptedException {
        try {
            ctrl.t.sleep(5000);
            for (Storable st : objectlist) {
                String testresult = st.getClass().getName() + " - Create file/Read data test result is ";
                int result = 0;
                try {
                    ctrl.load(st);
                    result = st.compareTo(ctrl.getAnother(st));
                    if (result == 0)
                        testresult += "OK";
                    else
                        testresult += "negative";
                    getResult(testresult);
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void write() throws InvocationTargetException, NoSuchFieldException, ParseException, IOException,
            InterruptedException, InstantiationException {
        try {
        ctrl.t.sleep(5000);
        for (Storable st : objectlist) {
            String testresult = st.getClass().getName() + " - Write data test result is ";
            int result = 0;
            try {
                ctrl.onSave(st);
                result = st.compareTo(ctrl.getAnother(st));
                if (result == 0)
                    testresult += "OK";
                else
                    testresult += "negative";
                getResult(testresult);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }  catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
void synchronizetest() throws InterruptedException {
    try {
        ctrl.t.sleep(25000);
        person.setName("test NAME");
        person.setSurname("test SURNAME");
        person.setBirthday((new SimpleDateFormat("dd.MM.yyyy")).parse((new SimpleDateFormat("dd.MM.yyyy"))
                .format(new Date())));
        organization.setName("test NAME");
        organization.setBusiness("test BUSINESS");
        for (Storable st : objectlist) {
            String testresult = st.getClass().getName() + " - Synchronize objects test result is ";
            int result = 0;
            result = st.compareTo(ctrl.getAnother(st));
            if (result != 0)
                testresult += "OK";
            else
                testresult += "negative";
            getResult(testresult);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }

}
    void getResult(String testresult) {
        System.out.println(testresult);
    }
}
