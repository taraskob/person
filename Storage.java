import java.io.*;
import java.lang.Object;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Storage {
    private File file;
    public void writeData(Object input) throws IOException, IllegalAccessException, NoSuchFieldException,
        InvocationTargetException, ParseException {
        String filename=getFilename(input.getClass());
        try {
            sourcexists(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = new FileWriter(filename);
        Field[] getFields = input.getClass().getDeclaredFields();
        for(Field field:getFields) {
            field.setAccessible(true);
            if (field.getType().getName().endsWith("Date"))
            {String fieldValue = new SimpleDateFormat("dd.MM.yyyy").format(field.get(input));
                writer.write(fieldValue+"\r\n");
            }
            else
            { String fieldValue = (String) field.get(input);
                writer.write(fieldValue+"\r\n");}
        }
        writer.close();}
    public void sourcexists(String fileName) throws IOException {
        file = new File(fileName);
        if (!file.exists()){
            String line="";
            FileWriter writer = new FileWriter(fileName);
            {
                writer.write(line);
            }
            writer.close();
        }
    }
    public void readData(Object input) throws IllegalAccessException, ParseException {
        String filename=getFilename(input.getClass());
        StringBuilder line = new StringBuilder();
        try {
            sourcexists(filename);
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
        String[] linefields=line.toString().split("\n");
        Field[] setFields = input.getClass().getDeclaredFields();
        int i = 0;
        if (linefields.length >= setFields.length) {
            for (Field field : setFields) {
                field.setAccessible(true);
                if (field.getType().getName().endsWith("Date"))
                field.set(input, new SimpleDateFormat("dd.MM.yyyy").parse(linefields[i]));
                else
                field.set(input, linefields[i]);
                i++;
            }
        }
    }
    String getFilename(Class classname) {return classname.getName()+".dat";}
}
