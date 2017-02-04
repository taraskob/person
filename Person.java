import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
public class Person {
    private ArrayList<String> current_person=new ArrayList<String>();
    File file;
    String name;
    String surname;
    String birthday;
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
        current_person.add(readPerson());
    }
    void saveInput(String[] inputdata) throws IOException {
        for(int i=0;i<inputdata.length;i++)
        {  if (current_person.size() >= inputdata.length) {
            current_person.set(i, inputdata[i]);
        }
        else {
            current_person.add(i, inputdata[i]);
        }}

    }
    private boolean isEqual(ArrayList<String> current_data,ArrayList<String> new_data){
        if(current_data.size()!=new_data.size()){
            return false;}
        else{
            for(int i=0;i<current_data.size();i++){
                if(!myEquals(current_data.get(i),new_data.get(i)))
                {
                    return false;
                }
            }
            return true;
        }}
    private boolean myEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
    boolean compareData(Person new_data) throws IOException {
        if(isEqual(current_person,strToAList(new_data.readPerson()))) {
            return true;
        }
        return false;
    }
    ArrayList<String> strToAList(String inputtext)  {
        ArrayList<String> al=new ArrayList<String>();
        al.add(inputtext);
        return al;
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
    String readPerson() {
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
        return line.toString();
    }
    void filexists(String fileName) throws IOException {
        file = new File(fileName);
        if (!file.exists()){
            String line="";
            FileWriter writer = new FileWriter("Data.dat");
            {
                writer.write(line);
            }
            writer.close();
        }
    }
}
