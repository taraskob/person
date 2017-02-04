import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
public class Person {
    private File file;
    private String name;
    private String surname;
    private String birthday;
    private Date date=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.YY");
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
    void setBirthday(String birthday) {
        this.birthday=birthday;
    }
    String getBirthday() {
        return birthday;
    }
    void setData() {
        String[] linefields = readPerson();
        if(linefields.length<3) {
            setName("Name");
            setSurname("Surname");
            setBirthday(toString());
    }
        else {
            setName(linefields[0]);
            setSurname(linefields[1]);
            setBirthday(linefields[2]);}
    }
    boolean compareData(Person new_person) throws IOException {
        if(!myEquals(this.name,new_person.name)) {
            return false;
        }
        if(!myEquals(this.surname,new_person.surname)) {
            return false;
        }
        if(!myEquals(this.birthday,new_person.birthday)) {
            return false;
        }
        return true;
    }
    private boolean myEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    void writePerson() throws IOException {
        try {
            filexists("Person.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter("Person.dat");
         writer.write(getName()+"\r\n");
         writer.write(getSurname()+"\r\n");
         writer.write(getBirthday()+"\r\n");
         writer.close();
    }
    String[] readPerson() {
        StringBuilder line = new StringBuilder();
        try {
            filexists("Person.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    line.append(s);
                    line.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return line.toString().split("\n");
    }
    void filexists(String fileName) throws IOException {
        file = new File(fileName);
        if (!file.exists()){
            String line="";
            FileWriter writer = new FileWriter("Person.dat");
            {
                writer.write(line);
            }
            writer.close();
        }
    }
    public String toString(){
        String str=sdf.format(getDate());
        return str; }
    public Date getDate() {
        date=new Date();
        return date;
    }
}
