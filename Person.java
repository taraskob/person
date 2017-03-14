import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Person extends Storable {
    private String name;
    private String surname;
    private Date birthday;

    Person() {
        name = "NAME";
        surname = "SURNAME";
        try {
            birthday = (new SimpleDateFormat("dd.MM.yyyy")).parse((new SimpleDateFormat("dd.MM.yyyy"))
                    .format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Person(String name, String surname, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    Person(Person other) {
        this(other.getName(), other.getSurname(), other.getBirthday());
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    synchronized void setFields(String name, String surname, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    Date getBirthday() {
        return birthday;
    }

    @Override
    public int compareTo(Object o) {
        Person another = (Person) o;
        int result = this.name.compareTo(another.name);
        if (result != 0) {

            return result;
        }
        result = this.surname.compareTo(another.surname);
        if (result != 0) {
            return result;
        }
        result = this.birthday.compareTo(another.birthday);
        if (result != 0) {
            return result;
        }
        return 0;
    }

}

