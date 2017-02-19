import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
public class Basecontroller {
    private List<ChangeHandler> listener = new ArrayList<>();
    private Organization organization=new Organization();
    private Person person=new Person();
    private Storage storage=new Storage();
    void addToListener(ChangeHandler changeHandler){
        listener.add(changeHandler);
    }
    void syncro() throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException,
            IOException, NoSuchMethodException, NoSuchFieldException {
            try {
                syncro(getPerson());
                syncro(getOrganization());
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
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
     void syncro(Object synclass) throws IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException, ParseException, NoSuchFieldException, IOException {
        Class cl = synclass.getClass();
        Object another = cl.newInstance();
        try {
            getStorage().readData(another);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = 0;
        if (synclass instanceof Person) {
            result = (int) getPerson().compareTo(another);
        } else if (synclass instanceof Organization) {
            result = (int) getOrganization().compareTo(another);
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
}}
