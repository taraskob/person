import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller  {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Organization organization=new Organization();
    private Person person=new Person();
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
    void syncro() throws IllegalAccessException, ParseException, InstantiationException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException, IOException {
        try {
            {syncro(getPerson());
            syncro(getOrganization());}
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    <T> void syncro(T synclass) throws IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException, ParseException, NoSuchFieldException, IOException {
        T another= (T) synclass.getClass().newInstance();
        try {
            getStorage().readData(another);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = 0;
        if (synclass instanceof Person) {
            result = getPerson().compareTo(another);
        } else if (synclass instanceof Organization) {
            result = getOrganization().compareTo(another);
        }
        if (result != 0) {
            synclass = another;
            onChange();
        }
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
