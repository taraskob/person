import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import  java.text.*;
import  java.util.*;
public class FormaPersonalData implements ChangeHandler {
    private DateFormat df;
    private JLabel jlab;
    private JTextField jtfName;
    private JTextField jtfSurname;
    private JFormattedTextField jftfBirthday;
    private JButton jbtnSave;
    private Controller ctrl=new Controller();
        FormaPersonalData() throws ParseException {
            JFrame jfrm = new JFrame("Person");
            jfrm.getContentPane().setLayout(new FlowLayout());
            jfrm.setSize(240, 270);
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jlab = new JLabel();
            JLabel jlabSeparator=new JLabel();
            jlabSeparator.setPreferredSize(new Dimension(200,20));
            jtfName = new JTextField();
            jtfSurname = new JTextField();
            jtfName.setColumns(15);
            jtfSurname.setColumns(15);
            df=new SimpleDateFormat("dd.MM.yyyy");
            DateFormatter dateFormatter = new DateFormatter(df);
            dateFormatter.setAllowsInvalid(true);
            dateFormatter.setOverwriteMode(true);
            jftfBirthday = new JFormattedTextField(dateFormatter);
            jftfBirthday.setColumns(15);
            jftfBirthday.setValue(new Date());
            jbtnSave = new JButton("Save Personal Data");
            ctrl.addToListener(this);
            load();
            jbtnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent le) {
                    try {
                        saveinput();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
            jfrm.getContentPane().add(new JLabel("Name    "));
            jfrm.getContentPane().add(jtfName);
            jfrm.getContentPane().add(new JLabel("Surname "));
            jfrm.getContentPane().add(jtfSurname);
            jfrm.getContentPane().add(new JLabel("Birthday"));
            jfrm.getContentPane().add(jftfBirthday);
            jfrm.getContentPane().add(jlabSeparator);
            jfrm.getContentPane().add(jbtnSave);
            jfrm.getContentPane().add(jlab);
            jfrm.setVisible(true);
        }
    private void load() {
        String[] linefields = ctrl.getPersom().readPerson();
        if(linefields.length<3) {
        jtfName.setText("Name");
        jtfSurname.setText("Surname");
        jftfBirthday.setValue(new Date());}
        else {
            jtfName.setText(linefields[0]);
            jtfSurname.setText(linefields[1]);
            jftfBirthday.setText(linefields[2]);}
        }
    private void saveinput() throws IOException, ParseException {
        ctrl.getPersom().setName(jtfName.getText());
        ctrl.getPersom().setSurname(jtfSurname.getText());

        try {
            ctrl.getPersom().setBirthday(jftfBirthday.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ctrl.getPersom().writePerson();
        ctrl.onChange();
    }
    public void onChange() {
        load();
    }
    }


