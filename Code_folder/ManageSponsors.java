import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ManageSponsors{

    JDialog jDialog;

    public ManageSponsors(){
        jDialog =  new JDialog();

        //Header
        JLabel jLabel = new JLabel("Manage Sponsors");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //Event Name
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(50,130,200,30);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventTestField = new JTextField();
        eventTestField.setBounds(50,165,200,30);

        //sponsorName
        JLabel sponsorsName = new JLabel("Sponsor Name");
        sponsorsName.setBounds(50,200,200,30);
        sponsorsName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField sponsorName2 = new JTextField();
        sponsorName2.setBounds(50,235,200,30);

        //mail
        JLabel mail = new JLabel("Sponsor Mail");
        mail.setBounds(50,270,200,30);
        mail.setFont(new Font("Serif",Font.BOLD,20));

        JTextField mailField = new JTextField();
        mailField.setBounds(50,305,200,30);

        //amount
        JLabel amount = new JLabel("Amount");
        amount.setBounds(50,340,200,30);
        amount.setFont(new Font("Serif",Font.BOLD,20));

        JTextField amount2 = new JTextField();
        amount2.setBounds(50,375,200,30);

        //status
        JLabel status = new JLabel("Status");
        status.setBounds(50,410,200,30);
        status.setFont(new Font("Serif",Font.BOLD,20));

        JTextField statusField = new JTextField();
        statusField.setBounds(50,445,200,30);



        //details button
        JButton details = new JButton("Add Sponsors");
        details.setBounds(300,130,200,50);
        details.setBackground(new Color(1,60,50));
        details.setForeground(Color.WHITE);
        details.setFocusPainted(false);
        details.setFont(new Font("Serif",Font.PLAIN,20));

        //details button
        JButton deleteSponcor = new JButton("Delete Sponsors");
        deleteSponcor.setBounds(830,130,200,50);
        deleteSponcor.setBackground(new Color(1,60,50));
        deleteSponcor.setForeground(Color.WHITE);
        deleteSponcor.setFocusPainted(false);
        deleteSponcor.setFont(new Font("Serif",Font.PLAIN,20));





        //Go BAck button
        JButton back = new JButton("Go Back");
        back.setBounds(550,550,150,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setFont(new Font("Serif",Font.BOLD,20));

        //header of scroll pane : manually created  (ignore the headers they are added after creation of JScrollpane)
        JLabel header1 = new JLabel("Event Name");
        header1.setBounds(300,210,145,20);
        header1.setBackground(new Color(1,90,50));
        header1.setHorizontalAlignment(SwingConstants.CENTER);
        header1.setForeground(Color.WHITE);
        header1.setOpaque(true);

        JLabel header2 = new JLabel("Sponsor Name");
        header2.setBounds(445,210,145,20);
        header2.setBackground(new Color(1,90,50));
        header2.setHorizontalAlignment(SwingConstants.CENTER);
        header2.setForeground(Color.WHITE);
        header2.setOpaque(true);

        JLabel header3 = new JLabel("Sponsor mail");
        header3.setBounds(590,210,145,20);
        header3.setBackground(new Color(1,90,50));
        header3.setHorizontalAlignment(SwingConstants.CENTER);
        header3.setForeground(Color.WHITE);
        header3.setOpaque(true);

        JLabel header4 = new JLabel("Amount");
        header4.setBounds(735,210,145,20);
        header4.setBackground(new Color(1,90,50));
        header4.setHorizontalAlignment(SwingConstants.CENTER);
        header4.setForeground(Color.WHITE);
        header4.setOpaque(true);

        JLabel header5 = new JLabel("status");
        header5.setBounds(880,210,145,20);
        header5.setBackground(new Color(1,90,50));
        header5.setHorizontalAlignment(SwingConstants.CENTER);
        header5.setForeground(Color.WHITE);
        header5.setOpaque(true);


        jDialog.add(header1);
        jDialog.add(header2);
        jDialog.add(header3);
        jDialog.add(header4);
        jDialog.add(header5);


        //show the sponsors list to the page from database
        JTable table = new JTable();
        table.setBackground(Color.getHSBColor(20,300,200));
        table.setFont(new Font("Serif",Font.BOLD,15));

        JScrollPane jScrollPane = new JScrollPane(table);
        table.setTableHeader(null);
        jScrollPane.setBounds(300,230,730,300);
        jScrollPane.setBackground(Color.getHSBColor(20,300,100));
        jScrollPane.getViewport().setBackground(Color.getHSBColor(20,300,100));
        jDialog.add(jScrollPane);
        //database connection
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");
            String eventInfo = "select * from sponsor";
            ResultSet resultSet = connection.createStatement().executeQuery(eventInfo);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e){
            e.printStackTrace();
        }

        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventTestField);
        jDialog.add(sponsorsName);
        jDialog.add(sponsorName2);
        jDialog.add(mail);
        jDialog.add(mailField);
        jDialog.add(amount);
        jDialog.add(amount2);
        jDialog.add(status);
        jDialog.add(statusField);
        jDialog.add(details);
        jDialog.add(deleteSponcor);
        jDialog.add(back);


        details.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = eventTestField.getText();
                String sponsorName = sponsorName2.getText();
                String mail = mailField.getText();
                String amount = amount2.getText();
                String status = statusField.getText();


               if(eventName.length()>0 && sponsorName.length()>0 && mail.length()>0 && amount.length()>0 && status.length()>0){
                   try {
                       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                       PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                               "insert into sponsor(ename ,sname, mail ,amount ,status)" + "values(?,?,?,?,?)"
                       );

                       st.setString(1, eventName);
                       st.setString(2, sponsorName);
                       st.setString(3,mail);
                       st.setString(4,amount);
                       st.setString(5,status);
                       st.execute();

                       JOptionPane.showMessageDialog(jDialog, "Sponsor Added");
                       jDialog.setVisible(false);
                       new ManageSponsors();


                   } catch (SQLException sqlException) {
                       sqlException.printStackTrace();
                   }
               }
               else{
                   JOptionPane.showMessageDialog(jDialog,"Enter All the sponsors details");
               }

            }
        });


        deleteSponcor.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = eventTestField.getText();
                String sponsorName = sponsorName2.getText();



                if(eventName.length()>0 && sponsorName.length()>0){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");

                        PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                                "delete from sponsor where ename = ? and sname = ?"
                        );

                        st.setString(1, eventName);
                        st.setString(2, sponsorName);
                        st.execute();

                        JOptionPane.showMessageDialog(jDialog, "Sponsor deleted");
                        jDialog.setVisible(false);
                        new ManageSponsors();


                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(jDialog,"Enter Only the Event_Name and Sponcor_Name to delete an entry");
                }
            }
        });

        back.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        }));


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
        new ManageSponsors();
    }




}