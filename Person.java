import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 class Person  {
    private String name;
    private String surname;
    private Date birthday=new Date();
    String getName() {
       return name;
    }
    String getSurname() {
        return surname;
    }
    Date getBirthday() {
        return birthday;
    }
    void setFields(String[] linefields) throws ParseException, IllegalAccessException {
        Field[] setFields = this.getClass().getDeclaredFields();
        int i=0;
        if (linefields.length >= setFields.length){
        for (Field field : setFields){
               if(!field.getType().getName().endsWith("Date"))
                    field.set(this, linefields[i]);
                else
                    field.set(this, new SimpleDateFormat("dd.MM.yyyy").parse(linefields[i]));
                i++;
              }
              }
 }
   boolean compareData(Person another_person) throws IOException {
         if (!myEquals(this.getName(), another_person.getName())) {
             return false;
         }
         if (!myEquals(this.getSurname(), another_person.getSurname())) {
             return false;
         }
         if (!(this.getBirthday().equals(another_person.getBirthday()))) {
             return false;
         }
         return true;
     }

    private boolean myEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    }
