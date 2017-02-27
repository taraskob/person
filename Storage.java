import java.io.*;
import java.lang.Object;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Storage {
    private File file;
    synchronized void writeData(Object input) throws IOException, IllegalAccessException, NoSuchFieldException,
        InvocationTargetException, ParseException {
        String filename=getFilename(input.getClass());
        try {
            sourcexists(filename);
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
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    synchronized void sourcexists(String fileName) throws IOException {
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
    synchronized void readData(Object input) throws IllegalAccessException, ParseException {
        String filename=getFilename(input.getClass());
        StringBuilder line = new StringBuilder();
        try {
            sourcexists(filename);
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    line.append(s);
                    line.append("\n");
                }
            } finally {
                in.close();
                String[] linefields=line.toString().split("\n");
                Field[] setFields = input.getClass().getDeclaredFields();
                int i = 0;

                    for (Field field : setFields) {
                        field.setAccessible(true);
                        if (linefields.length >= setFields.length) {
                            if (field.getType().getName().endsWith("Date"))
                                field.set(input, new SimpleDateFormat("dd.MM.yyyy").parse(linefields[i]));
                            else
                                field.set(input, linefields[i]);
                            i++;
                        }
                        else {
                            if (field.getType().getName().endsWith("Date"))
                                field.set(input,new Date());
                            else
                                field.set(input, field.getName());
                        }
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String getFilename(Class classname) {return classname.getName()+".dat";}
}
