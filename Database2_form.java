package database2_form;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Database2_form extends WindowAdapter {

    Frame f;
    Button insert, update, select, delete;
    TextField t1, t2;
    Checkbox sport, reading, singing, dancing, male, female, other;
    CheckboxGroup cg;
    Label fname, lname, gender, hobby;

    Database2_form() {
        f = new Frame();
        f.setSize(500, 500);
        f.setVisible(true);
        f.setLayout(null);
        f.addWindowListener(this);

        fname = new Label("First Name : ");
        fname.setBounds(50, 50, 70, 70);
        f.add(fname);

        lname = new Label("Last Name : ");
        lname.setBounds(50, 100, 70, 70);
        f.add(lname);

        t1 = new TextField("");
        t1.setBounds(150, 70, 150, 30);
        f.add(t1);

        t2 = new TextField("");
        t2.setBounds(150, 120, 150, 30);
        f.add(t2);

        gender = new Label("Gender : ");
        gender.setBounds(50, 150, 70, 70);
        f.add(gender);

        cg = new CheckboxGroup();

        male = new Checkbox("Male", cg, true);
        male.setBounds(150, 150, 50, 70);
        f.add(male);

        female = new Checkbox("Female", cg, false);
        female.setBounds(220, 150, 60, 70);
        f.add(female);

        other = new Checkbox("Other", cg, false);
        other.setBounds(300, 150, 50, 70);
        f.add(other);

        hobby = new Label("Hobbys : ");
        hobby.setBounds(50, 200, 70, 70);
        f.add(hobby);

        sport = new Checkbox("Sport");
        sport.setBounds(150, 200, 70, 70);
        f.add(sport);

        reading = new Checkbox("Reading");
        reading.setBounds(220, 200, 70, 70);
        f.add(reading);

        singing = new Checkbox("singing");
        singing.setBounds(150, 250, 70, 70);
        f.add(singing);

        dancing = new Checkbox("dancing");
        dancing.setBounds(220, 250, 70, 70);
        f.add(dancing);

        insert = new Button("Insert : ");
        insert.setBounds(50, 330, 80, 35);
        f.add(insert);

        update = new Button("Update : ");
        update.setBounds(200, 330, 80, 35);
        f.add(update);

        select = new Button("Select : ");
        select.setBounds(50, 370, 80, 35);
        f.add(select);

        delete = new Button("Delete : ");
        delete.setBounds(200, 370, 80, 35);
        f.add(delete);

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String fn = t1.getText().toString();
                String ln = t2.getText().toString();

                System.out.println("" + fn);
                System.out.println("" + ln);
                /*jo aa khali aapde consol ma print karavu hoy ne toj aa no use thay..pn aapde consol and databse bey ma print
                 karvu che to jo niche jo e code lakhelo che ema bey ma print thay consol & database ma...aama magaj no athho 
                thay gyo..etle hve tane aavdvu j joye...etle etlu badhu lakhu chu...pn aa comment valo code kai kam no nathi.
                
                if(male.getState()){
                    System.out.println(""+male.getLabel());
                }
                if(female.getState()){
                    System.out.println(""+female.getLabel());
                }
                if(other.getState()){
                    System.out.println(""+other.getLabel());
                }
                if(sport.getState()){
                    System.out.println(""+sport.getLabel());
                }
                if(reading.getState()){
                    System.out.println(""+reading.getLabel());
                }
                if(singing.getState()){
                    System.out.println(""+singing.getLabel());
                }
                if(dancing.getState()){
                    System.out.println(""+dancing.getLabel());
                }*/

//aaya thi olo code chalu thay..ke data bey ma stor thay ..database and consol bey ma print thay. joje tame aavdi jashe.
                String jati;
                if (male.getState()) {
                    jati = male.getLabel();
                } else if (female.getState()) {
                    jati = female.getLabel();
                } else {
                    jati = other.getLabel();
                }
                System.out.println("" + jati); //aa line consol ma print kare

                String shokh = "";
                if (sport.getState()) {
                    shokh = sport.getLabel();
                }
                if (reading.getState()) {
                    shokh += "," + reading.getLabel();
                }
                if (singing.getState()) {
                    shokh += "," + singing.getLabel();
                }
                if (dancing.getState()) {
                    shokh = shokh + "," + dancing.getLabel();
                }
                System.out.println(shokh); //aa pn consol ma print kare

                String q = "insert into student(`id`,`fname`,`lname`,`gender`,`hobby`) values (null,'" + fn + "','" + ln + "','" + jati + "','" + shokh + "')";
                //aa uper ni line data base ma print kare
                try {
                    int val = s.executeUpdate(q);
                    if (val == 1) {
                        System.out.println("Record inserted successfully...");
                    } else {
                        System.err.println("Record not inserted...");
                    }
                } catch (SQLException ex) {
                    System.err.println("Insert ni query ma bhul che bhai." + ex);
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String input = JOptionPane.showInputDialog("Enter ID which data you want to deleted : ");
                int del_id = Integer.parseInt(input);

                String q = "delete from student where id=" + del_id + "";
                try {
                    int val = s.executeUpdate(q);
                    if (val == 1) {
                        System.out.println("Record Deleted successfully...");
                    } else {
                        System.err.println("Record Not Deleted...");
                    }
                } catch (SQLException ex) {
                    System.err.println("Delete ni Query ma Bhul che." + ex);
                }
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String jati;
                if (male.getState()) {
                    jati = male.getLabel();
                } else if (female.getState()) {
                    jati = female.getLabel();
                } else {
                    jati = other.getLabel();
                }

                String shokh = "";
                if (sport.getState()) {
                    shokh = sport.getLabel();
                }
                if (reading.getState()) {
                    shokh += "," + reading.getLabel();
                }
                if (singing.getState()) {
                    shokh += "," + singing.getLabel();
                }
                if (dancing.getState()) {
                    shokh = shokh + "," + dancing.getLabel();
                }

                String q = "Update student set fname='" + t1.getText().toString() + "',lname='" + t2.getText().toString() + "',gender='" + jati + "',hobby='" + shokh + "' where id='" + select_id + "'";
                try {
                    int val = s.executeUpdate(q);
                    if (val == 1) {
                        System.out.println("Record Updated Successfully..!");
                    } else {
                        System.err.println("Record Not updated..!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Database2_form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        select.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void actionPerformed(ActionEvent ae) {

                String input = JOptionPane.showInputDialog("Enter Id Which You Want To View Data : ");
                select_id = Integer.parseInt(input);

                String q = "Select * from student where id='" + select_id + "'";
                try {
                    ResultSet val = (ResultSet) s.executeQuery(q);
                    while (val.next()) {
                        t1.setText(val.getString("fname"));
                        t2.setText(val.getString("lname"));

                        String jati = val.getString("gender");
                        System.out.println(jati);

                        if (jati.equalsIgnoreCase("Male")) {
                            male.setState(true);
                        } else if (jati.equalsIgnoreCase("Female")) {
                            female.setState(true);
                        } else {
                            other.setState(true);
                        }

                        String shokh = val.getString("hobby");
                        System.out.println(hobby);
                        String str[] = shokh.split(",");
                        /*for(String i:str){
                            System.out.println(""+i);
                        }*/
                        for (int i = 0; i < str.length; i++) {
                            if (str[i].equalsIgnoreCase("sport")) {
                                sport.setState(true);
                                System.out.println("sport if");
                            } else if (str[i].equalsIgnoreCase("reading")) {
                                reading.setState(true);
                                System.out.println("reading if");
                            } else if (str[i].equalsIgnoreCase("singing")) {
                                singing.setState(true);
                                System.out.println("singing if");

                            } else if (str[i].equalsIgnoreCase("dancing")) {
                                dancing.setState(true);
                                System.out.println("dancing if");
                            } else {
                                sport.setState(false);
                                reading.setState(false);
                                singing.setState(false);
                                dancing.setState(false);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Database2_form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    static Statement s;    //statment na object ne function ni bar use karva no che etle..globle banavyo che.
    int select_id;  //aa select_id ne aapde 2 jagya e use karvani che select & update part ma etle globle banavi padi nkr khali select ma bnavo to pn chale
    //and ek select_id 2 kam kare

    public static void main(String[] args) {
        Database2_form db = new Database2_form();
        connection();//aa method static bnavi che etle khali function na name thi call thay che.nkr object. kari ne lakhvu pade
    }

    @Override
    public void windowClosing(WindowEvent we) {
        f.dispose();
    }

    static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abhay_try", "root", "");
            System.out.println("Connction Successfully");
            s = (Statement) conn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database2_form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
