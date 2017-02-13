import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
class Controller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Organization organization=new Organization();
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
     <T> void syncrogen(T synclass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ParseException {
        String nameoffile=synclass.getClass().getName()+".dat";
        Class cl = synclass.getClass();
        Class[] paramTypes = new Class[] {java.lang.String[].class};
        Method setmethod = cl.getDeclaredMethod("setFields",paramTypes);
        Object[] setargs = new Object[] { readFile(nameoffile) };
        T another= (T) cl.newInstance();
        setmethod.invoke(another,setargs);
        Class[] paramTypes1 = new Class[] {synclass.getClass()};
        Method comparemethod = cl.getDeclaredMethod("compareData",paramTypes1);
        Object[] compareargs = new Object[] { another };
        if(!((Boolean) (comparemethod.invoke(synclass,compareargs))))
            {
            synclass=another;
            onChange();
        }
    }
    void syncro() throws IOException, ParseException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        try {
            syncrogen(getPerson());
            syncrogen(getOrganization());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    Person getPerson() {return person;}
    Organization getOrganization() {return organization;}
    Storage getStorage() {return storage;}
    void wrtiteInput(Object input) throws InvocationTargetException, NoSuchFieldException, IllegalAccessException, IOException
    {getStorage().writeInput(input);}
    String[] readFile(String filename) {
        return getStorage().readFile(filename);
    }
    void onChange() throws ParseException, IllegalAccessException {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
}
