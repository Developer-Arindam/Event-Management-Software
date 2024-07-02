import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class login2 {

    static JTextField userid2;
    static JPasswordField password2;

    String userName;
    String pass;
    public login2(){
        JDialog jDialog = new JDialog();


        //Header
        JLabel jLabel = new JLabel("Organizer Login Page");
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





        //submit button
        JButton jButton = new JButton("Submit");
        jButton.setBounds(250,440,100,30);
        jButton.setBackground(new Color(1,60,50));
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Serif",Font.BOLD,20));

        //back button
        JButton back = new JButton("Back");
        back.setBounds(260,480,80,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setFont(new Font("Serif",Font.BOLD,20));


        jDialog.add(jLabel);
        jDialog.add(userid);
        jDialog.add(userid2);
        jDialog.add(password);
        jDialog.add(password2);
        jDialog.add(jButton);
        jDialog.add(back);

        //submit button actionlistner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userid2.getText().length()>0 && password2.getPassword().length >= 8 ){

                    userName = userid2.getText();
                    pass = password2.getText();
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select name, password , type from account where name=? and password=?" );

                        st.setString(1, userName);
                        st.setString(2, pass);

                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            String str = rs.getString("type");
                            String org = "Organizer";
                          if(str.equals(org)){ //if not organizer then you will not be allowed to enter manage event
                              jDialog.dispose();
                              new ManageEvent();
                          }
                          else{
                              JOptionPane.showMessageDialog(jDialog, "Not valid organizer details");
                          }
                        }
                        else {
                            JOptionPane.showMessageDialog(jDialog, "Wrong Username or Password");
                        }
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }



                }else if(password2.getPassword().length < 8){
                    JOptionPane.showMessageDialog(jDialog,"Incorrect password legth");
                }
                else{

                    JOptionPane.showMessageDialog(jDialog,"Enter All the fields");
                }
            }
        });

        //back butteon ACtion listner
        back.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
                new Major();
            }
        });


        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jDialog.setIconImage(icon.getImage());
        jDialog.setResizable(false);


        jDialog.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        jDialog.setLayout(null);
        jDialog.setModal(true);

        jDialog.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jDialog.setBounds(400,140,600,600);
        jDialog.setVisible(true);

        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);




    }
    public static void main(String args[]){
        new login2();
    }
}
