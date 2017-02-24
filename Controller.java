import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class Controller implements Runnable{
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
    private Object input;
    Boolean saveFlag=false;
    Thread t;
    {t=new Thread(this);
    t.start();}
    @Override
    public void run() {
       while(true) {
           if(saveFlag) {
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

           try {synconizeobjects();
               t.sleep(15000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
    void synconizeobjects() {
        for (Storable st : objectlist) {
            int result = 0;
            try {
                result = st.compareTo(getAnother(st));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (result != 0) {
                try {
                    onChange();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
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
        {this.input=input;
          setsaveFlag(); }
    void load(Object input) throws ParseException, IllegalAccessException {   getStorage().readData(input);  }
    void setsaveFlag() {
        if(saveFlag)
            saveFlag=false;
        else
            saveFlag=true;
    }
    void onChange() throws ParseException, IllegalAccessException {
        for(ChangeHandler item:listener){
            item.onChange();
        }
    }
}
