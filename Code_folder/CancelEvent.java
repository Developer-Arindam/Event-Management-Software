import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class CancelEvent {
    JDialog jDialog;
    public CancelEvent(){
        jDialog = new JDialog();


        //Header
        JLabel jLabel = new JLabel("Cancel Event");
        jLabel.setBounds(0,0,600,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //eventName
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(100,150,200,20);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventName2 = new JTextField();
        eventName2.setBounds(100,180,400,40);
        eventName2.setFont(new Font("Serif",Font.PLAIN,20));

        //Event date
        JLabel eventDate = new JLabel("Event Date");
        eventDate.setBounds(100,230,200,20);
        eventDate.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventDate2 = new JTextField();
        eventDate2.setBounds(100,260,400,40);
        eventDate2.setFont(new Font("Serif",Font.PLAIN,20));



        //submit button
        JButton jButton = new JButton("Submit");
        jButton.setBounds(250,440,100,30);
        jButton.setBackground(new Color(1,60,50));
        jButton.setForeground(Color.WHITE);
        jButton.setFont(new Font("Serif",Font.BOLD,20));

        //Go BAck button
        JButton back = new JButton("Back");
        back.setBounds(260,480,80,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,15));




        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventName2);
        jDialog.add(eventDate);
        jDialog.add(eventDate2);
        jDialog.add(jButton);
        jDialog.add(back);

        //submit button action Listner
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = eventName2.getText();
                String date = eventDate2.getText();


               if(name.length()>0 && date.length()>0){
                   try {
                       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                       PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                               "DELETE FROM event WHERE name = ? AND date = ?"
                       );
                       PreparedStatement st2 = (PreparedStatement) connection.prepareStatement(
                               "select * from event WHERE name = ? AND date = ?"
                       ) ;


                       st.setString(1, name);
                       st.setString(2, date);
                       st2.setString(1, name);
                       st2.setString(2, date);

                       //st.executeUpdate();
                       ResultSet rs = st2.executeQuery();
                       if(rs.next()){
                           st.executeUpdate();
                           JOptionPane.showMessageDialog(jDialog, "Event Successfully Cancelled");
                           jDialog.dispose();
                       }else{
                           JOptionPane.showMessageDialog(jDialog,"No Entry found. Check for spelling mistake");
                       }


                   } catch (SQLException sqlException) {
                       sqlException.printStackTrace();
                   }
               }else{
                   JOptionPane.showMessageDialog(jDialog,"Enter all the fields");
               }




            }
        });

        //back button actionlistner
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
        jDialog.setBounds(400,140,600,600);
        jDialog.setModal(true);
        jDialog.setVisible(true);


        jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String args[]){
        new CancelEvent();
    }
}
