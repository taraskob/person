import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Person person=new Person();
    private Organization organization=new Organization();
    private Storage storage=new Storage();
    private LTimerTask mytask=new LTimerTask(this);
    private Timer compTimer=new Timer(true);
    void addToListener(ChangeHandler changeHandler){
        listener.add(changeHandler);
    }
    {compTimer.schedule(mytask,1000,15000);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();}
    }
    void syncro() throws IOException, ParseException, IllegalAccessException, InstantiationException {
        Person another_person=new Person();
        try {
            another_person.setPerson(readFile(getPersom().getClass().getName()+".dat"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(!person.comparePerson(another_person))
        {
            person=another_person;
            onChange();
        }
    }
    Person getPersom() {return person;}
    Organization getOrganization() {return organization;}
    Storage getStorage() {return storage;}
    void wrtiteInput(String filename,Object input) throws InvocationTargetException, NoSuchFieldException, IllegalAccessException, IOException
    {getStorage().writeInput(input,filename);}
    String[] readFile(String filename) {
        return getStorage().readFile(filename);
    }
    void onChange() throws ParseException, IllegalAccessException {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
class Gen<T> {
        T ob;
        Gen(T o) {ob=o;}
       T getob() {return ob;}
}
}
