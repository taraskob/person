import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
 class Person implements Comparable {
     private String name;
     private String surname;
     private Date birthday;
     Person() {
     name="NAME";
     surname="SURNAME";
     Date birthday=new Date();}
     void setName(String name) {this.name=name;}
     String getName() {
         return name;
     }
     void setSurname(String surname) {this.surname=surname;}
     String getSurname() {
         return surname;
     }
     void setBirthday(Date birthday) {this.birthday=birthday;}
     Date getBirthday() { return birthday; }
    @Override
     public int compareTo(Object o) {
         Person another=(Person) o;
        Field[] compareFields = o.getClass().getDeclaredFields();
         int result;
        for (Field field : compareFields) {
            result = getName().compareTo(another.getName());
            if (result != 0) {
                return result;
            }
        }
         return 0;
     }
 }

