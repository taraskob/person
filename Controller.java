import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Person person=new Person();
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
    void syncro() throws IOException, ParseException {
        Person another_person=new Person();
        another_person.setPerson(readFile());
        if(!person.comparePerson(another_person))
        {
            person=another_person;
            onChange();
        }
    }
    Person getPersom() {return person;}
    Storage getStorage() {return storage;}
    void wrtiteInput() throws InvocationTargetException, NoSuchFieldException, IllegalAccessException, IOException
    {getStorage().writeInput(getPersom());}
    String[] readFile() {
        return getStorage().readPerson();
    }
    void onChange() {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
}
