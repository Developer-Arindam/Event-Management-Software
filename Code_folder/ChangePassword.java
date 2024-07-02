import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ChangePassword {

    public JDialog jDialog;
    public ChangePassword(){
        jDialog = new JDialog();

        //Header
        JLabel jLabel = new JLabel("Change Password");
        jLabel.setBounds(0,0,600,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //User id
        JLabel userid = new JLabel("User Name");
        userid.setBounds(100,150,100,20);
        userid.setFont(new Font("Serif",Font.BOLD,20));

        JTextField userid2 = new JTextField();
        userid2.setBounds(100,180,400,40);
        userid2.setFont(new Font("Serif",Font.PLAIN,20));

        //Password
        JLabel passwordOld = new JLabel("Old Password");
        passwordOld.setBounds(100,250,200,20);
        passwordOld.setFont(new Font("Serif",Font.BOLD,20));

        JPasswordField password2 = new JPasswordField();
        password2.setBounds(100,280,400,40);
        password2.setFont(new Font("Serif",Font.PLAIN,20));

        //Password
        JLabel passwordNew = new JLabel("New Password");
        passwordNew.setBounds(100,350,200,20);
        passwordNew.setFont(new Font("Serif",Font.BOLD,20));

        JPasswordField password3 = new JPasswordField();
        password3.setBounds(100,380,400,40);
        password3.setFont(new Font("Serif",Font.PLAIN,20));

        //submit button
        JButton jButton = new JButton("Submit");
        jButton.setBounds(250,440,100,30);
        jButton.setBackground(new Color(1,60,50));
        jButton.setForeground(Color.WHITE);
        jButton.setFocusPainted(false);
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
        jDialog.add(passwordOld);
        jDialog.add(password2);
        jDialog.add(passwordNew);
        jDialog.add(password3);
        jDialog.add(jButton);
        jDialog.add(back);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userid2.getText().length() == 0){
                    JOptionPane.showMessageDialog(jDialog,"Enter User Name");
                }
                else if(password2.getPassword().length < 8){
                    JOptionPane.showMessageDialog(jDialog,"old password length should be atleast 8");
                }
                else if(password3.getPassword().length < 8){
                    JOptionPane.showMessageDialog(jDialog,"New password length should be atleast 8");
                }
                else{
                    String userName = userid2.getText();
                    String oldpas = password2.getText();
                    String newpas = password3.getText();

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        //if the entered userid and old password is not a vaid one then show messege
                        PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select * from account where name=? and password=?" );

                        st.setString(1, userName);
                        st.setString(2, oldpas);

                        ResultSet rs = st.executeQuery();

                        if (rs.next()) {    //if correct entry exist in database then only password change is possible
                            PreparedStatement st1 = (PreparedStatement) connection.prepareStatement(
                                    "update account set password=? where name = ? and password = ?"
                            );

                            st1.setString(1,newpas);
                            st1.setString(2, userName);
                            st1.setString(3,oldpas);

                            st1.executeUpdate();


                            JOptionPane.showMessageDialog(jDialog, "password change successful");
                            jDialog.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(jDialog, "Unsuccessful password change. Enter your details correctly");
                        }





                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }


                }


            }
        });

        //back button actionListner
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //jDialog.setVisible(false);
                jDialog.dispose();
            }
        });

        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jDialog.setIconImage(icon.getImage());
        jDialog.setResizable(false);

        jDialog.setModal(true);
        jDialog.setLayout(null);
        jDialog.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jDialog.setBounds(400,140,600,600);
        jDialog.setVisible(true);

    }

    public static void main(String args[]){
        new ChangePassword();
    }
}
