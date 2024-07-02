import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEvent {
    JDialog jDialog;
    public CreateEvent(){
        jDialog = new JDialog();

        //Header
        JLabel jLabel = new JLabel("Create Event");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //

        //Event Name name
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(50,150,200,40);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventTestField = new JTextField();
        eventTestField.setBounds(50,200,200,40);

        //Event Date
        JLabel eventDate = new JLabel("Event Date");
        eventDate.setBounds(50,260,200,40);
        eventDate.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventDate2 = new JTextField();
        eventDate2.setBounds(50,310,200,40);


        //venue
        JLabel venue = new JLabel("Venue");
        venue.setBounds(50,360,200,40);
        venue.setFont(new Font("Serif",Font.BOLD,20));

        JTextField venueField = new JTextField();
        venueField.setBounds(50,410,200,40);



        //column 2
        //time
        JLabel time = new JLabel("Event Time");
        time.setBounds(350,150,200,40);
        time.setFont(new Font("Serif",Font.BOLD,20));

        JTextField timeField = new JTextField();
        timeField.setBounds(350,200,200,40);


        //Event Duration
        JLabel eventDuration = new JLabel("Event Duration");
        eventDuration.setBounds(350,260,200,40);
        eventDuration.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventDuration2 = new JTextField();
        eventDuration2.setBounds(350,310,200,40);


        //Event Budget
        JLabel budget = new JLabel("Event Budget");
        budget.setBounds(350,360,200,40);
        budget.setFont(new Font("Serif",Font.BOLD,20));


        JTextField budgetField = new JTextField();
        budgetField.setBounds(350,410,200,40);

        //Event Description
        JLabel eventDescription = new JLabel("Event Description (Optional)");
        eventDescription.setBounds(650,150,300,40);
        eventDescription.setFont(new Font("Font",Font.BOLD,20));

        JTextArea eventDescription2 = new JTextArea();
        eventDescription2.setBounds(650,200,400,250);





        //submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(500,550,100,30);
        submitButton.setBackground(new Color(1,60,50));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Serif",Font.BOLD,20));

        //Back button
        JButton back = new JButton("Back");
        back.setBounds(510,590,80,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,20));

        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventTestField);
        jDialog.add(eventDate);
        jDialog.add(eventDate2);
        jDialog.add(venue);
        jDialog.add(venueField);
        jDialog.add(time);
        jDialog.add(timeField);
        jDialog.add(eventDuration);
        jDialog.add(eventDuration2);
        jDialog.add(budget);
        jDialog.add(budgetField);
        jDialog.add(submitButton);
        jDialog.add(back);
        jDialog.add(eventDescription);
        jDialog.add(eventDescription2);

        //sublit button action listner
        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = eventTestField.getText();
                String date = eventDate2.getText();
                String venue = venueField.getText();
                String time = timeField.getText();
                String duration = eventDuration2.getText();
                String budget = budgetField.getText();
                String description = eventDescription2.getText();

                if(name.length() >0 && date.length()>0 && venue.length()>0 && time.length()>0  && time.length()>0 && duration.length()>0 && budget.length()>0 ){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                                "insert into event(name ,date ,venue ,time ,duration, budget,description )" + "values(?,?,?,?,?,?,?)"
                        );

                        st.setString(1, name);
                        st.setString(2, date);
                        st.setString(3,venue);
                        st.setString(4,time);
                        st.setString(5,duration);
                        st.setString(6,budget);
                        st.setString(7,description);
                        st.execute();

                        jDialog.dispose();
                        JOptionPane.showMessageDialog(jDialog, "Event successfullt Created");

                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(jDialog,"Enter All the fields");
                }

            }

        });

        //back button Action Listner
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.setVisible(false);
            }
        });

        ImageIcon icon = new ImageIcon("src\\image\\title_icon.png");
        jDialog.setIconImage(icon.getImage());
        jDialog.setResizable(false);

        jDialog.setLayout(null);
        jDialog.getContentPane().setBackground(Color.getHSBColor(20,300,100));
        jDialog.setBounds(400,140,1100,670);
        jDialog.setModal(true);
        jDialog.setVisible(true);

        jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    }

    public static void main(String args[]){
        new CreateEvent();
    }
}
