import java.text.ParseException;
import java.util.*;

class Controller implements Runnable {
    private List<ChangeHandler> listener = new ArrayList<>();

    private Controller() {
    }

    private static class ControllerHandler {
        private static Controller CTRL;

        static {
            CTRL = new Controller();
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
         return person;
      //  return new Person(person);
    }

    synchronized Organization getOrganization() {
          return organization;
       // return new Organization(organization);
    }

    Storage getStorage() {
        return storage;
    }

    void addToListener(ChangeHandler changeHandler) {
        listener.add(changeHandler);
    }

    Boolean loadFlag = true;
    Boolean saveFlag = false;
    Object input;
    Thread t;

    {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(saveFlag) {
                    getStorage().writeData(input);
                    saveFlag=false;
                }
                for (Storable st : objectlist) {

                    if (loadFlag) load(st);
                    if (synchronizedobjects(st))
                        onChange();
                }
                loadFlag = false;

                t.sleep(10000);
            } catch (InterruptedException e) {
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    synchronized boolean synchronizedobjects(Storable st) throws InterruptedException, IllegalAccessException {

        try {
            Storable another = getAnother(st);
            int objectscompareresult = st.compareTo(another);
            if (objectscompareresult != 0) {
                st = another;
                setInstance(st);
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    synchronized <T> T getAnother(T synclass) throws IllegalAccessException, InstantiationException, InterruptedException {
        T another = (T) synclass.getClass().newInstance();
        try {
            load(another);
        } catch (IllegalAccessException | InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return another;
    }


    synchronized void saveInput(Object input) throws IllegalAccessException {
        this.input=input;
        saveFlag=true;
        t.interrupt();
        t = new Thread(this);
        t.start();
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
