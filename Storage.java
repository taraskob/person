import java.io.*;
import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
public class Storage {
    private File file;
    void writeInput(Object input) throws IOException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
     try {
            filexists("Person.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter("Person.dat");
        Class writeclass = input.getClass();
        Method[] inputmethods= writeclass.getDeclaredMethods();
        for (Method method : inputmethods) {
            if(method.getName().startsWith("get")){
            if(method.getReturnType().getName().endsWith("String")){
                writer.write((String) method.invoke(input)+"\r\n");
             }
            if(method.getReturnType().getName().endsWith("Date")){
                writer.write(new SimpleDateFormat("dd.MM.yyyy").format(method.invoke(input))+"\r\n");
             }
             }
         }
        writer.close();
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
}
