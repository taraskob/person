import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 class Person  {
    private String name;
    private String surname;
    private Date birthday;
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void setSurname(String surname) {
        this.surname = surname;
    }
    String getSurname() {
        return surname;
    }
    void setBirthday(String birthday) throws ParseException {
        this.birthday = new SimpleDateFormat("dd.MM.yyyy").parse(birthday);
    }
    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    Date getBirthday() {
        return birthday;
    }
    void setPerson(String[] strings) throws ParseException {
        String[] linefields = strings;
        if (linefields.length < 3) {
            setName("Name");
            setSurname("Surname");
            setBirthday(new Date());
        } else {
            setName(linefields[0]);
            setSurname(linefields[1]);
            setBirthday(linefields[2]);
        }
    }
    boolean comparePerson(Person another_person) throws IOException {
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
