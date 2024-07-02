import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class TrackEvents{

    JDialog jDialog;

    public TrackEvents(String even,String Dat){  //these are event name and date to show the specic rows from databse
        jDialog =  new JDialog();

        //Header
        JLabel jLabel = new JLabel("Track Events");
        jLabel.setBounds(0,0,1100,100);
        jLabel.setBackground(new Color(1,60,50));
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("Serif",Font.ITALIC,50));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setOpaque(true);

        //Event Name
        JLabel eventName = new JLabel("Event Name");
        eventName.setBounds(50,150,200,40);
        eventName.setFont(new Font("Serif",Font.BOLD,20));

        JTextField eventTestField = new JTextField();
        eventTestField.setBounds(50,200,200,40);

        //Event date
        JLabel date = new JLabel("Event Date");
        date.setBounds(50,250,200,40);
        date.setFont(new Font("Serif",Font.BOLD,20));

        JTextField dateField = new JTextField();
        dateField.setBounds(50,300,200,40);



        //details button
        JButton details = new JButton("See Participant");
        details.setBounds(50,360,200,50);
        details.setBackground(new Color(1,60,50));
        details.setForeground(Color.WHITE);
        details.setFont(new Font("Serif",Font.BOLD,15));

        //show events button
        JButton showEvent = new JButton("See Event List");
        showEvent.setBounds(50,430,200,50);
        showEvent.setBackground(new Color(1,60,50));
        showEvent.setForeground(Color.WHITE);
        showEvent.setFont(new Font("Serif",Font.BOLD,15));



        //Go BAck button
        JButton back = new JButton("Go Back");
        back.setBounds(475,550,150,30);
        back.setBackground(new Color(1,60,50));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Serif",Font.BOLD,20));

        //Scroll bar
        //show the selected rows of registrar to the page from database (see try catch block)
        JTable table = new JTable();
        table.setBackground(Color.getHSBColor(20,300,100));
        table.setFont(new Font("Serif",Font.BOLD,15));

        JScrollPane jScrollPane = new JScrollPane(table);
        table.setTableHeader(null);
        jScrollPane.setBounds(280,240,775,200);
        jScrollPane.setBackground(Color.getHSBColor(20,300,100));
        jScrollPane.getViewport().setBackground(Color.getHSBColor(20,300,100));
        jDialog.add(jScrollPane);

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false", "root", "123456789");
            String eventInfo = "select pname,phno,college,mail from registrar where ename = ? and date = ?";
            PreparedStatement st = connection.prepareStatement(eventInfo);

            st.setString(1,even);
            st.setString(2,Dat);
            System.out.println(even + " " + Dat);

            ResultSet resultSet = st.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e){
            e.printStackTrace();
        }




        //header of scroll pane : manually created  (ignore the headers they are added after creation of JScrollpane)
        JLabel header1 = new JLabel("Participant Name");
        header1.setBounds(280,220,200,20);
        header1.setBackground(new Color(1,60,50));
        header1.setHorizontalAlignment(SwingConstants.CENTER);
        header1.setForeground(Color.WHITE);
        header1.setOpaque(true);

        JLabel header2 = new JLabel("Participant Phone");
        header2.setBounds(480,220,200,20);
        header2.setBackground(new Color(1,60,50));
        header2.setHorizontalAlignment(SwingConstants.CENTER);
        header2.setForeground(Color.WHITE);
        header2.setOpaque(true);

        JLabel header3 = new JLabel("College");
        header3.setBounds(680,220,200,20);
        header3.setBackground(new Color(1,60,50));
        header3.setHorizontalAlignment(SwingConstants.CENTER);
        header3.setForeground(Color.WHITE);
        header3.setOpaque(true);

        JLabel header4 = new JLabel("Mail ID");
        header4.setBounds(880,220,170,20);
        header4.setBackground(new Color(1,60,50));
        header4.setHorizontalAlignment(SwingConstants.CENTER);
        header4.setForeground(Color.WHITE);
        header4.setOpaque(true);

        jDialog.add(header1);
        jDialog.add(header2);
        jDialog.add(header3);
        jDialog.add(header4);


        jDialog.add(jLabel);
        jDialog.add(eventName);
        jDialog.add(eventTestField);
        jDialog.add(date);
        jDialog.add(dateField);
        jDialog.add(details);
        jDialog.add(back);
        jDialog.add(showEvent);


        //details(Click to track) button action listner
        //from the current window the text is passed to a new window where database will fetch specific row
        details.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eventTestField.getText().length()>0 && dateField.getText().length()>0){
                    jDialog.dispose();
                    new TrackEvents(eventTestField.getText(),dateField.getText());

                }
                else{
                    JOptionPane.showMessageDialog(jDialog,"Enter the All the fields");
                }

            }
        });

        back.addActionListener((new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        }));

        showEvent.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
                new ShowEvent();

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


        jDialog.setDefaultCloseOperation(jDialog.DISPOSE_ON_CLOSE);

    }

    public static void main(String args[]){
        new TrackEvents(null,null);
    }


}