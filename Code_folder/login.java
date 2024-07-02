import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class login {

    static JTextField userid2;
    static JPasswordField password2;

    String userName;
    String pass;
    String usertype;

    public login(){
        JFrame jFrame = new JFrame();


        //Header
        JLabel jLabel = new JLabel("User Login Page");
        jLabel.setBounds(0,0,600,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //User Id
        JLabel userid = new JLabel("User ID");
        userid.setBounds(100,150,100,20);
        userid.setFont(new Font("Serif",Font.BOLD,20));

        userid2 = new JTextField();
        userid2.setBounds(100,180,400,40);
        userid2.setFont(new Font("Serif",Font.PLAIN,20));

        //Password
        JLabel password = new JLabel("Password");
        password.setBounds(100,250,100,20);
        password.setFont(new Font("Serif",Font.BOLD,20));

        password2 = new JPasswordField();
        password2.setBounds(100,280,400,40);
        password2.setFont(new Font("Serif",Font.PLAIN,20));


        //participants or organizer selection
        JLabel userType = new JLabel("Select User Type : ");
        userType.setBounds(100,330,300,40);
        userType.setFont(new Font("Serif",Font.BOLD,20));

        //combobox
        String userType1[] = {"Participants","Organizer"};
        JComboBox jComboBox = new JComboBox(userType1);
        jComboBox.setBounds(280,340,220,30);
        jComboBox.setBackground(new Color(154,255,204));


        //submit button
        JButton jButton = new JButton("Submit");
        jButton.setBounds(250,440,100,30);
        jButton.setBackground(new Color(1,60,50));
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Serif",Font.BOLD,20));

        //dont hava account option
        JLabel account = new JLabel("Don't have an account ? ");
        account.setBounds(100,500,180,30);
        account.setFont(new Font("Font",Font.PLAIN,15));

        JButton creatAccountButton = new JButton("Create account");
        creatAccountButton.setBounds(270,510,120,20);
        creatAccountButton.setBackground(new Color(154,255,204));

        //Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(450,510,80,20);
        exitButton.setBackground(new Color(10,60,50));
        exitButton.setForeground(Color.WHITE);



        jFrame.add(jLabel);
        jFrame.add(userid);
        jFrame.add(userid2);
        jFrame.add(password);
        jFrame.add(password2);
        jFrame.add(jButton);
        jFrame.add(account);
        jFrame.add(creatAccountButton);
        jFrame.add(userType);
        jFrame.add(jComboBox);
        jFrame.add(exitButton);

        //submit button actionlistner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userid2.getText().length()>0 && password2.getPassword().length >= 8 && jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString().length() > 0){

                        userName = userid2.getText();
                        pass = password2.getText();
                        usertype = jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString();
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                            PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name, password , type from account where name=? and password=? and type = ?" );

                            st.setString(1, userName);
                            st.setString(2, pass);
                            st.setString(3,usertype);
                            ResultSet rs = st.executeQuery();
                            if (rs.next()) {
                                jFrame.dispose();
                                Major page = new Major();
                            } else {
                                JOptionPane.showMessageDialog(jFrame, "Wrong Username & Password or user type");
                            }
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }



                }else if(password2.getPassword().length < 8){
                    JOptionPane.showMessageDialog(jFrame,"Incorrect password legth");
                }
                else{

                    JOptionPane.showMessageDialog(jFrame,"Enter All the fields");
                }
            }
        });


        //createAccountButton action listner
        creatAccountButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                createAccount obj = new createAccount();
            }
        });

        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });



        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jFrame.setIconImage(icon.getImage());
        jFrame.setResizable(false);

        jFrame.setLayout(null);


        jFrame.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jFrame.setBounds(400,100,600,600);
        jFrame.setVisible(true);





        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public static void main(String args[]){
        new login();
    }
}
