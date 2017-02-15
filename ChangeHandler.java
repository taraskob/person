import java.text.ParseException;

interface ChangeHandler {
    void onChange() throws ParseException, IllegalAccessException;
}