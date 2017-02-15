import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
class FormaOrganizationData implements ChangeHandler{
    private JLabel jlab;
    private JTextField name;
    private JTextField business;
    private JButton jbtnSave;
    private Controller ctrl=new Controller();
    FormaOrganizationData() throws ParseException, IllegalAccessException {
        JFrame jfrm = new JFrame("Organization");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(240, 170);
        jfrm.setBounds(300, 10, 240, 220);
        jfrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jlab = new JLabel();
        JLabel jlabSeparator=new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200,20));
        name = new JTextField();
        business = new JTextField();
        name.setColumns(15);
        business.setColumns(15);
        jbtnSave = new JButton("Save Organization Data");
        ctrl.addToListener(this);
        try {
            load();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
        jfrm.getContentPane().add(new JLabel("Name     "));
        jfrm.getContentPane().add(name);
        jfrm.getContentPane().add(new JLabel("Business "));
        jfrm.getContentPane().add(business);
        jfrm.getContentPane().add(jlabSeparator);
        jfrm.getContentPane().add(jbtnSave);
        jfrm.getContentPane().add(jlab);
        jfrm.setVisible(true);
    }
    private void load() throws ParseException, IllegalAccessException {
        try {
            ctrl.readFile(ctrl.getOrganization());
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        name.setText(ctrl.getOrganization().getName());
        business.setText(ctrl.getOrganization().getBusiness());

    }
    private void saveinput() throws IOException, ParseException, IllegalAccessException, InvocationTargetException,
            NoSuchFieldException, NoSuchMethodException {
        ctrl.getOrganization().setName(name.getText());
        ctrl.getOrganization().setBusiness(business.getText());
        ctrl.saveInput(ctrl.getOrganization());
        ctrl.onChange();
          }
      public void onChange() throws ParseException, IllegalAccessException {
        load();
    }
}

