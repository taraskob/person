import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.*;
import java.util.*;

class FormaPersonalData implements ChangeHandler {
    private DateFormat df;
    private JLabel jlab;
    private JTextField name;
    private JTextField surname;
    private JFormattedTextField birthday;
    private JButton jbtnSave;
    private Controller ctrl = Controller.getController();

    FormaPersonalData() throws ParseException, IllegalAccessException {
        JFrame jfrm = new JFrame("Person");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(240, 270);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jlab = new JLabel();
        JLabel jlabSeparator = new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200, 20));
        name = new JTextField();
        surname = new JTextField();
        name.setColumns(15);
        surname.setColumns(15);
        df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormatter dateFormatter = new DateFormatter(df);
        dateFormatter.setAllowsInvalid(true);
        dateFormatter.setOverwriteMode(true);
        birthday = new JFormattedTextField(dateFormatter);
        birthday.setColumns(15);
        birthday.setValue(new Date());
        jbtnSave = new JButton("Save Personal Data");
        ctrl.addToListener(this);
        try {
            load();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jbtnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                try {
                    saveinput();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
        jfrm.getContentPane().add(new JLabel("Name    "));
        jfrm.getContentPane().add(name);
        jfrm.getContentPane().add(new JLabel("Surname "));
        jfrm.getContentPane().add(surname);
        jfrm.getContentPane().add(new JLabel("Birthday"));
        jfrm.getContentPane().add(birthday);
        jfrm.getContentPane().add(jlabSeparator);
        jfrm.getContentPane().add(jbtnSave);
        jfrm.getContentPane().add(jlab);
        jfrm.setVisible(true);
    }

    private void load() throws ParseException, IllegalAccessException, InterruptedException {
        name.setText(ctrl.getPerson().getName());
        surname.setText(ctrl.getPerson().getSurname());
        birthday.setValue(ctrl.getPerson().getBirthday());

    }

    private void saveinput() throws IOException, ParseException, IllegalAccessException, InvocationTargetException,
            NoSuchFieldException, NoSuchMethodException {
        ctrl.getPerson().setName(name.getText());
        ctrl.getPerson().setSurname(surname.getText());
        ctrl.getPerson().setBirthday(new SimpleDateFormat("dd.MM.yyyy").parse(birthday.getText()));
        ctrl.saveInput(new Person(ctrl.getPerson()));
    }

    @Override
    public void onChange() throws ParseException, IllegalAccessException, InterruptedException {
        try {
            load();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


