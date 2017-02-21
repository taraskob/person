import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Organization organization=new Organization();
    private Person person=new Person();
    ArrayList<Storable> objectlist = new ArrayList<>();
    {objectlist.add(person);
        objectlist.add(organization);}
    private Storage storage=new Storage();
    void addToListener(ChangeHandler changeHandler){
        listener.add(changeHandler);
    }
    private LTimerTask mytask=new LTimerTask(this);
    private Timer compTimer=new Timer(true);
    {compTimer.schedule(mytask,1000,15000);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();}
    }
     void syncronizeobjects() throws IllegalAccessException, ParseException, InstantiationException {
         for (Storable st : objectlist) {
             int result = 0;
             result = st.compareTo(getAnother(st));
             if (result != 0) {
                 onChange();
             }
         }
    }
    <T> T getAnother(T synclass) throws IllegalAccessException, InstantiationException, ParseException {
        T another= (T) synclass.getClass().newInstance();
        try {
             getStorage().readData(another);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return another;
    }
        Person getPerson() {return person;}
        Organization getOrganization() {return organization;}
        Storage getStorage() {return storage;}
    void saveInput(Object input) throws InvocationTargetException, NoSuchFieldException,
            IllegalAccessException, ParseException, IOException
    {       getStorage().writeData(input);    }
    void load(Object input) throws ParseException, IllegalAccessException {
        getStorage().readData(input);
    }
    void onChange() throws ParseException, IllegalAccessException {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
    }
