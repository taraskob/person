import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.concurrent.locks.ReentrantLock;

class FormaOrganizationData implements ChangeHandler {
    private JLabel jlab;
    private JTextField name;
    private JTextField business;
    private JButton jbtnSave;
    private Controller ctrl = Controller.getController();

    FormaOrganizationData() throws ParseException, IllegalAccessException {
        JFrame jfrm = new JFrame("Organization");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(240, 170);
        jfrm.setBounds(300, 10, 240, 220);
        jfrm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jlab = new JLabel();
        JLabel jlabSeparator = new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200, 20));
        name = new JTextField();
        business = new JTextField();
        name.setColumns(15);
        business.setColumns(15);
        jbtnSave = new JButton("Save Organization Data");
        ctrl.addToListener(this);
        {
            load();
        }
        jbtnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent le) {
                try {
                    saveinput();
                } catch (IllegalAccessException e) {
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

    private void load() {
        Object[] organization = ctrl.getOrganization().getFields();
        name.setText((String) organization[0]);
        business.setText((String) organization[1]);
    }

    private void saveinput() throws IllegalAccessException {
        Organization organization = ctrl.getOrganization();
        //   organization.setName(name.getText());
        //   organization.setBusiness(business.getText());

            organization.setFields(name.getText(), business.getText());

        ctrl.saveInput(organization);
    }

    @Override
    public void onChange() throws ParseException, IllegalAccessException, InterruptedException {
        load();
    }
}

