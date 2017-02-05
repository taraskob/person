import java.io.*;
public class ReadSaveFile {
    private File file;
    void writePerson(String name,String surname,String birthday) throws IOException {
        try {
            filexists("Person.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter("Person.dat");
        writer.write(name+"\r\n");
        writer.write(surname+"\r\n");
        writer.write(birthday+"\r\n");
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
}
