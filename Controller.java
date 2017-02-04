import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
public class Controller {
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
        Person new_data=new Person();
        new_data.setData();
        if(!person.compareData(new_data))
        {
            person=new_data;
            onChange();
        }
    }
    Person getData() {return person;}
    void onChange() {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
}
