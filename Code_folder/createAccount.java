import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Arrays;

public class createAccount {
    JFrame jFrame;
    static JTextField jTextField;
    static JTextField jemailField;
    static JTextField jPhoneField;

    static JPasswordField confirmPassword2;

    static  JPasswordField createPaaword2;
    static JComboBox jComboBox;
    static JTextArea college2;
    public createAccount(){
        jFrame = new JFrame();


        //Header
        JLabel jLabel = new JLabel("Create User Account");
        jLabel.setBounds(0,0,600,90);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,40));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //name
        JLabel name = new JLabel("Name");
        name.setBounds(50,110,100,20);
        name.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        //name TextBox
        jTextField = new JTextField();
        jTextField.setBounds(50,140,200,30);

        //email id
        JLabel email = new JLabel("Email ID");
        email.setBounds(50,180,100,20);
        email.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        //email Id TextBox
        jemailField = new JTextField();
        jemailField.setBounds(50,210,200,30);

        //phoneNo
        JLabel phoneNo = new JLabel("Phone No");
        phoneNo.setBounds(50,250,100,20);
        phoneNo.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        //phone no TextBox
        jPhoneField = new JTextField();
        jPhoneField.setBounds(50,280,200,30);


        // Create Password
        JLabel createPassword = new JLabel("Create Password");
        createPassword.setBounds(50,320,200,20);
        createPassword.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        createPaaword2 = new JPasswordField();
        createPaaword2.setBounds(50,350,200,30);

        // Confirm Password
        JLabel confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setBounds(50,390,200,20);
        confirmPassword.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        confirmPassword2 = new JPasswordField();
        confirmPassword2.setBounds(50,420,200,30);

        //participants or organizer selection
        JLabel userType = new JLabel("Select User Type : ");
        userType.setBounds(320,110,200,20);
        userType.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        //combobox
        String userType1[] = {"Participants","Organizer"};
        jComboBox = new JComboBox(userType1);
        jComboBox.setBounds(320,140,200,30);



        //college Name
        JLabel college = new JLabel("College Name");
        college.setBounds(320,180,200,20);
        college.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));

        //collegeName
        college2 = new JTextArea();
        college2.setBounds(320,210,200,40);


        //submit button
        JButton jButton = new JButton("Submit");
        jButton.setBounds(250,480,100,30);
        jButton.setBackground(new Color(1,60,50));
        jButton.setForeground(Color.WHITE);

        //back button
        JButton back = new JButton("Back");
        back.setBounds(260,520,80,20);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);



        jFrame.add(jLabel);
        jFrame.add(name);
        jFrame.add(jTextField);
        jFrame.add(email);
        jFrame.add(jemailField);
        jFrame.add(phoneNo);
        jFrame.add(jPhoneField);
        jFrame.add(createPassword);
        jFrame.add(createPaaword2);
        jFrame.add(confirmPassword);
        jFrame.add(confirmPassword2);
        jFrame.add(userType);
        jFrame.add(jComboBox);
        jFrame.add(college);
        jFrame.add(college2);
        jFrame.add(jButton);
        jFrame.add(back);

        //submit button actionlistner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextField.getText().length() > 0 && jemailField.getText().length() > 0
                        && jPhoneField.getText().length() == 10 && createPaaword2.getPassword().length >= 8
                        && Arrays.equals(createPaaword2.getPassword(), confirmPassword2.getPassword())
                        && college2.getText().length() > 0) {

                    //database
                    String userName = jTextField.getText();
                    String userEmail = jemailField.getText();
                    String phone = jPhoneField.getText();
                    String confpas = confirmPassword2.getText();
                    String userType = jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString();
                    String college = college2.getText();
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                              //  "insert into account(name ,email ,phone ,password ,type ,college )" + "values(?,?,?,?,?,?)"
                                "insert into account values(?,?,?,?,?,?)"
                        );

                        st.setString(1, userName);
                        st.setString(2, userEmail);
                        st.setString(3,phone);
                        st.setString(4,confpas);
                        st.setString(5,userType);
                        st.setString(6,college);
                        st.execute();

                        jFrame.dispose();
                        login log = new login();
                        JOptionPane.showMessageDialog(null, "You have successfully Created account.Now login");

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }


                } else if (createPaaword2.getPassword().length < 8) {
                    JOptionPane.showMessageDialog(jFrame, "Create a password with more then 8 character");
                } else if (!Arrays.equals(createPaaword2.getPassword(), confirmPassword2.getPassword())) {
                    JOptionPane.showMessageDialog(jFrame, "Enter Confirm Password Correctly");
                } else if (jPhoneField.getText().length() != 10) {
                    JOptionPane.showMessageDialog(jFrame, "Correctly Enter phone number");
                } else {
                    JOptionPane.showMessageDialog(jFrame, "Enter All the Fields");
                }
            }


        });

        //back button action listner
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                new login();
            }
        });

        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setResizable(false);

        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jFrame.setBounds(400,100,600,600);
        jFrame.setVisible(true);


    }

    public static void main(String args[]){
        new createAccount();
    }


}



