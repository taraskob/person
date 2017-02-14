import java.io.IOException;
import java.util.Date;
 class Person  {
     private String name;
     String getName() {
         return name;
     }
     private String surname;
     String getSurname() {
         return surname;
     }
     private Date birthday=new Date();

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
     Date getBirthday() {
         return birthday;
     }
    }

