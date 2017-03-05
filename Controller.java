import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements Runnable {
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
            if (loadFlag) {
                for (Storable st : objectlist) {
                    try {
                        load(st);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setloadFlag();
            }
            if (saveFlag) {
                try {
                    getStorage().writeData(input);
                    setsaveFlag();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            try {
                syncronizeobjects();
                t.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    void syncronizeobjects() throws InterruptedException {
        for (Storable st : objectlist) {
            int result = 0;
            try {
                Storable another = getAnother(st);
                result = st.compareTo(another);
                if (result != 0) {
                    st = another;
                    setInstance(st);
                    onChange();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
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

    Person getPerson() {
        return new Person(person);
    }

    Organization getOrganization() {
        return new Organization(organization);
    }

    Storage getStorage() {
        return storage;
    }

    synchronized void saveInput(Object input) throws InvocationTargetException, NoSuchFieldException,
            IllegalAccessException, ParseException, IOException {
        this.input = input;
        setsaveFlag();
    }

    synchronized void load(Object input) throws ParseException, IllegalAccessException, InterruptedException {
        getStorage().readData(input);

    }

    void setsaveFlag() {
        saveFlag = saveFlag ? false : true;
    }

    void setloadFlag() {
        loadFlag = loadFlag ? false : true;
    }

    synchronized <T> void setInstance(T st) throws ParseException, IllegalAccessException, InterruptedException {
        if (st.getClass().getName() == "Person")
            person = (Person) st;
        if (st.getClass().getName() == "Organization")
            organization = (Organization) st;
    }

    synchronized void onChange() throws ParseException, IllegalAccessException, InterruptedException {
        for (ChangeHandler item : listener) {
            item.onChange();
        }
    }
}
