import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;
public class Person {
    private File file;
    private String name;
    private String surname;
    private Date birthday;
    SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
    void setName(String name) {
     this.name=name;
    }
    String getName() {
        return name;
    }
    void setSurname(String surname) {
        this.surname=surname;
    }
    String getSurname() {
        return surname;
    }
    void setBirthday(String birthday) throws ParseException {
        this.birthday=sdf.parse(birthday);
    }
    void setBirthday(Date birthday)  {
        this.birthday=birthday;
    }
    Date getBirthday() {
        return birthday;
    }
    void setPerson() throws ParseException {
        String[] linefields = readPerson();
        if(linefields.length<3) {
            setName("Name");
            setSurname("Surname");
            setBirthday(new Date());
        }
        else {
            setName(linefields[0]);
            setSurname(linefields[1]);
            setBirthday(linefields[2]);}
    }
    boolean comparePerson(Person new_person) throws IOException {
        if(!myEquals(this.getName(),new_person.getName())) {
            return false;
        }
        if(!myEquals(this.getSurname(),new_person.getSurname())) {
            return false;
        }
        if(!(this.getBirthday().equals(new_person.getBirthday()))) {
            return false;
        }
        return true;
    }
    private boolean myEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    void writePerson() throws IOException {
        new ReadSaveFile().writePerson(getName(),getSurname(),sdf.format(getBirthday()));
    }
    String[] readPerson() {
      return  new ReadSaveFile().readPerson();
    }
    }
