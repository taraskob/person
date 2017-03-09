import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Runnable,FormListener {
    private List<ChangeHandler> listener = new ArrayList<>();

    private Controller() {
    }

    private static class ControllerHandler {
        private static Controller CTRL;

        static {
            try {
                CTRL = new Controller();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Controller getController() {
        return ControllerHandler.CTRL;
    }

    private Organization organization = new Organization();
    private Person person = new Person();
    ArrayList<Storable> objectlist = new ArrayList<>();

    {
        objectlist.add(person);
        objectlist.add(organization);
    }

    private Storage storage = new Storage();

    synchronized Person getPerson() {
        return new Person(person);
    }

    synchronized Organization getOrganization() {

        return new Organization(organization);
    }

    Storage getStorage() {
        return storage;
    }

    void addToListener(ChangeHandler changeHandler) {
        listener.add(changeHandler);
    }

    private Object input;
    Boolean saveFlag = false;
    Boolean loadFlag = true;
    Thread t;

    {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Storable st : objectlist) {
                    if (saveFlag) {
                        getStorage().writeData(input);
                        saveFlag = false;
                    }
                    if (loadFlag) load(st);
                    if (synchronizedobjects(st))
                        onChange();
                }
                loadFlag = false;
                t.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }


    synchronized boolean synchronizedobjects(Storable st) throws InterruptedException {

        try {
            Storable another = getAnother(st);
            int objectscompareresult = st.compareTo(another);
            if (objectscompareresult != 0) {
                st = another;
                setInstance(st);
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    synchronized <T> T getAnother(T synclass) throws IllegalAccessException, InstantiationException,
            InterruptedException, ParseException {
        T another = (T) synclass.getClass().newInstance();
        try {
            load(another);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return another;
    }

    @Override
    synchronized public void onSave(Object obj) {
        this.input = obj;
        saveFlag = true;
    }
    
    synchronized void load(Object input) throws ParseException, IllegalAccessException, InterruptedException {
        getStorage().readData(input);

    }

    <T> void setInstance(T st) throws ParseException, IllegalAccessException, InterruptedException {
        if (st.getClass().getName() == "Person")
            person = (Person) st;
        if (st.getClass().getName() == "Organization")
            organization = (Organization) st;
    }

     void onChange() throws ParseException, IllegalAccessException, InterruptedException {
        for (ChangeHandler item : listener) {
            item.onChange();
        }
    }
}
