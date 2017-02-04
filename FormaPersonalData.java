import java.beans.*;
import  java.awt.*;
import  java.awt.event.*;
import  javax.swing.*;
import java.io.IOException;
import  java.text.*;
import  javax.swing.text.*;
import  java.util.*;
public class FormaPersonalData implements ChangeHandler {
        DateFormat df;
        JLabel jlab;
        JTextField jtfName;
        JTextField jtfSurname;
        JFormattedTextField jftfBirthday;
        JButton jbtnSave;
        Controller ctrl=new Controller();
        public FormaPersonalData() {
            JFrame jfrm = new JFrame("Person");
            jfrm.getContentPane().setLayout(new FlowLayout());
            jfrm.setSize(240, 270);
            jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jlab = new JLabel();
            JLabel jlabSeparator=new JLabel();
            jlabSeparator.setPreferredSize(new Dimension(200,7));
            jtfName = new JTextField();
            jtfSurname = new JTextField();
            jtfName.setColumns(15);
            jtfSurname.setColumns(15);
            df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            jftfBirthday = new JFormattedTextField(df);
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
            String[] linefields = ctrl.getData().readPerson().split("\n");
    }
    private void saveinput() throws IOException {
        ctrl.getData().setName(jtfName.getText());
        ctrl.getData().setSurname(jtfSurname.getText());
        ctrl.getData().setBirthday(jftfBirthday.getText());
        ctrl.getData().writePerson();
        ctrl.onChange();
    }
    public void onChange() {
        load();
    }
    }


