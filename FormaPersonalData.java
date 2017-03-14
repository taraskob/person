import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


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
        {
            load();
        }

        jbtnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {

                try {
                    saveinput();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
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

    private void load() {
        Object[] person = ctrl.getPerson().getFields();

        name.setText((String) person[0]);
        surname.setText((String) person[1]);
        birthday.setValue(person[2]);

    }

    private void saveinput() throws ParseException, IllegalAccessException {
        Person person = ctrl.getPerson();
        // person.setName(name.getText());
        // person.setSurname(surname.getText());
        // person.setBirthday(new SimpleDateFormat("dd.MM.yyyy").parse(birthday.getText()));

            person.setFields(name.getText(), surname.getText(), new SimpleDateFormat("dd.MM.yyyy").parse(birthday.getText()));

        ctrl.saveInput(person);
    }

    @Override
    public void onChange() {
        load();
    }
}


