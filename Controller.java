import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Person person=new Person();
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
    void syncro() throws IOException {
        Person new_person=new Person();
        new_person.setData();
        if(!person.compareData(new_person))
        {
            person=new_person;
            onChange();
        }
    }
    Person getPersom() {return person;}
    void onChange() {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
}
