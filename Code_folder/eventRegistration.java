import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class eventRegistration {
    JDialog jDialog;
    public eventRegistration(){
        jDialog = new JDialog();

        //Header
        JLabel jLabel = new JLabel("Register for Event");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //

        //Event Name
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(50,150,200,40);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventTestField = new JTextField();
        eventTestField.setBounds(50,200,200,40);

        //Event Date
        JLabel date = new JLabel("Event Date");
        date.setBounds(50,260,200,40);
        date.setFont(new Font("Serif",Font.BOLD,20));

        JTextField dateField = new JTextField();
        dateField.setBounds(50,310,200,40);

        //participant
        JLabel participant= new JLabel("Participant Name");
        participant.setBounds(50,360,200,40);
        participant.setFont(new Font("Serif",Font.BOLD,20));

        JTextField participantField = new JTextField();
        participantField.setBounds(50,410,200,40);



        //column 2
        //phone no
        JLabel phoneNumber = new JLabel("Phone Number");
        phoneNumber.setBounds(350,150,200,40);
        phoneNumber.setFont(new Font("Serif",Font.BOLD,20));

        JTextField phoneTextField = new JTextField();
        phoneTextField.setBounds(350,200,200,40);


        //College Name
        JLabel college = new JLabel("College Name");
        college.setBounds(350,260,200,40);
        college.setFont(new Font("Serif",Font.BOLD,20));

        JTextField collegeTextField = new JTextField();
        collegeTextField.setBounds(350,310,200,40);


        //Email
        JLabel email = new JLabel("Email");
        email.setBounds(350,360,200,40);
        email.setFont(new Font("Serif",Font.BOLD,20));

        JTextField emailField = new JTextField();
        emailField.setBounds(350,410,200,40);

        JLabel right = new JLabel();
        right.setBounds(600,100,500,400);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src\\image\\Book.gif").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
        right.setIcon(imageIcon);

        //submit button
        JButton submitButton = new JButton("Register");
        submitButton.setBounds(475,500,150,30);
        submitButton.setBackground(new Color(1,60,50));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Serif",Font.BOLD,20));

        //back button
        JButton back = new JButton("Back");
        back.setBounds(510,540,80,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setFont(new Font("Serif",Font.BOLD,20));


        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventTestField);
        jDialog.add(date);
        jDialog.add(dateField);
        jDialog.add(participant);
        jDialog.add(participantField);
        jDialog.add(email);
        jDialog.add(emailField);
        jDialog.add(phoneNumber);
        jDialog.add(phoneTextField);
        jDialog.add(college);
        jDialog.add(collegeTextField);
        jDialog.add(submitButton);
        jDialog.add(right);
        jDialog.add(back);

        ImageIcon icon = new ImageIcon("C:\\Users\\hp\\IdeaProjects\\learn Swing\\image\\title_icon.png");
        jDialog.setIconImage(icon.getImage());

        //back button action listner
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });

        //submit button action listner
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = eventTestField.getText();
                String eventDate = dateField.getText();
                String participantName = participantField.getText();
                String phone = phoneTextField.getText();
                String collegeName = collegeTextField.getText();
                String emailID = emailField.getText();

                if(eventName.length() >0 && eventDate.length() >0 && participantName.length() >0 && phone.length()>0 && collegeName.length() >0 && emailID.length()>0){

                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        PreparedStatement st1 = (PreparedStatement) connection.prepareStatement(
                                "select * from event where name = ? and date = ?"
                        );
                        st1.setString(1,eventName);
                        st1.setString(2,eventDate);

                        ResultSet rs = st1.executeQuery();
                        if(rs.next()){
                            PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                                    "insert into registrar(ename ,date ,pname ,phno ,college, mail)" + "values(?,?,?,?,?,?)"
                            );

                            st.setString(1, eventName);
                            st.setString(2, eventDate);
                            st.setString(3,participantName);
                            st.setString(4,phone);
                            st.setString(5,collegeName);
                            st.setString(6,emailID);
                            st.execute();

                            JOptionPane.showMessageDialog(jDialog, "Registration successful");
                        }
                        else{
                            JOptionPane.showMessageDialog(jDialog,"No Event found. Enter EventName and EventDate correctly (go to ,track event->See event list,section) ");
                        }

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(jDialog,"Enter All the fields");
                }
            }

        });



        jDialog.setModal(true);
        jDialog.setLayout(null);
        jDialog.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        Color.HSBtoRGB(20,30,100);
        jDialog.setBounds(400,140,1100,670);
        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


    }

    public static void main(String args[]){
        new eventRegistration();
    }
}
