package com.nm.dao;
import com.nm.bean.Displaying_Data;
import com.nm.bean.Lecture_Detail;
import com.nm.util.DB_UTIL;
import com.nm.bean.Login;
import com.nm.bean.Subject;
import java.sql.Connection;
import com.nm.bean.User_Info;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.nm.bean.TheDate;
import com.nm.bean.Venue;
import static javax.swing.text.html.HTML.Tag.SELECT;

public class DBConnector {

    DB_UTIL dbUtil = new DB_UTIL();
    TheDate date = new TheDate();

    public boolean addUserInfo(User_Info userSignup, String action) throws SQLException {
        Connection connection = dbUtil.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO user_info VALUES (NULL, ?, ? ,?, NULL, NULL, ?, ?, ?, NULL, ?, NULL, NULL, ?)");
        ps.setString(1, userSignup.getTitle());
        ps.setString(2, userSignup.getFirst_name());
        ps.setString(3, userSignup.getLast_name());
        ps.setString(4, userSignup.getEmail());
        ps.setString(5, Long.toString(userSignup.getContact()));
        ps.setString(7, userSignup.getRoll_num());
        ps.setString(8, date.getDate());
        if (action.equals("teacher")) {
            ps.setString(6, "T");
        } else {
            ps.setString(6, "S");
        }
        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addLectureInfo(Lecture_Detail lecture) throws SQLException {
        Connection connection = dbUtil.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO lecture_details VALUES ( NULL, ?, ? ,?, NULL, ?, NULL, NULL, NULL, ?, ?, NULL)");

        ps.setString(1, lecture.getStart_time());
        ps.setString(2, lecture.getEnd_time());
        ps.setString(3, lecture.getDate());
        ps.setString(4, lecture.getDescription());
        ps.setString(5, lecture.getStatus());
        ps.setString(6, lecture.getTime_stamp());
        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
/**
 * 
 * @param login
 * @return
 * @throws SQLException 
 */
    public boolean addLoginInfo(Login login) throws SQLException {
        Connection connection = dbUtil.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO login VALUES ( NULL, ?, ? ,NULL, ? )");

        ps.setString(2, login.getPassword());
        ps.setString(1, login.getUser_name());
        ps.setString(3, date.getDate());

        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addSubject(Subject subject) throws SQLException {
        Connection connection = dbUtil.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO subject VALUES ( NULL, ?, NULL, ? )");

        ps.setString(1, subject.getSubject());
        ps.setString(2, date.getDate());

        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addVenue(Venue venue) throws SQLException {
        Connection connection = dbUtil.getConnection();
        PreparedStatement ps;
        ps = connection.prepareStatement("INSERT INTO venue VALUES ( NULL, ?, ? )");

        ps.setString(1, venue.getVenue_name());
        ps.setString(2, venue.getTime_stamp());

        int i = ps.executeUpdate();
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    

    public User_Info fetch(String checkusername, String checkpassword) throws SQLException {
    	
    	User_Info userInfo = new User_Info();

        String username = "";
        String password = "";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String res = "";
        try {
            conn = dbUtil.getConnection();
            String quer = "SELECT user_info.id,user_info.title, user_info.first_name, user_info.last_name, user_info.univer_id_fk, user_info.subject_fk,user_info.email, user_info.contact, user_info.contact_type, user_info.major_id_fk, user_info.roll_num, user_info.section_id_fk, user_info.year_id_fk, user_info.time_stamp FROM user_info INNER JOIN login ON user_info.id = login.user_id_fk AND login.user_name='" + checkusername + "' AND login.password='" + checkpassword + "';";

            //String query = "SELECT * FROM Login WHERE user_name ='" + checkusername + "'AND password='" + checkpassword + "'";
            //String query = "SELECT * from Login";
            st = conn.createStatement();
            rs = st.executeQuery(quer);
            res = "";
            while (rs.next()) {
            	userInfo.setId(rs.getInt("id"));
            	userInfo.setTitle(rs.getString("title"));
            	userInfo.setFirst_name(rs.getString("first_name"));
            	userInfo.setLast_name(rs.getString("last_name"));
            	userInfo.setUniver_id_fk(rs.getInt("univer_id_fk"));
            	userInfo.setSubject_fk(rs.getInt("subject_fk"));
            	userInfo.setEmail(rs.getString("email"));
            	userInfo.setContact(rs.getLong("contact"));
            	userInfo.setContact_type(rs.getString("contact_type"));
            	userInfo.setMajor_id_fk(rs.getInt("major_id_fk"));
            	userInfo.setRoll_num(rs.getString("roll_num"));
            	userInfo.setSection_id_fk(rs.getInt("section_id_fk"));
            	userInfo.setYear_id_fk(rs.getInt("year_id_fk"));
            	userInfo.setTime_stamp(rs.getString("time_stamp"));
            }
            return userInfo;

        } catch (Exception e) {

            System.err.println("Got an exception! " + "fetch method");
            System.err.println(e.getMessage());
            return userInfo;

        }

    }

    public ArrayList getLectures() {
        ArrayList<Lecture_Detail> lect = new ArrayList<Lecture_Detail>();

        try {
            Connection conn = dbUtil.getConnection();
            String query = "SELECT * FROM lecture_details ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Lecture_Detail obj = new Lecture_Detail();
                String startTime = rs.getString("start_time");
               
                String endTime = rs.getString("end_time");
                String description = rs.getString("description");
                obj.setStart_time(startTime);
                obj.setEnd_time(endTime);
                obj.setDescription(description);
                lect.add(obj);

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return lect;

    }

    
   // apply joins rather than th commented method
    
  
    public ArrayList getVenues() {
        ArrayList<Venue> venues = new ArrayList<Venue>();

        try {
            Connection conn = dbUtil.getConnection();
            String query = "SELECT * FROM venue";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Venue obj = new Venue();
                String startTime = rs.getString("venue_name");

                obj.setVenue_name(startTime);

                venues.add(obj);

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        
        return venues;

    }

    public ArrayList getSubjects() {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        try {
            Connection conn = dbUtil.getConnection();
            String query = "SELECT * FROM subject";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Subject obj = new Subject();

                obj.setSubject(rs.getString("subject"));

                subjects.add(obj);

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return subjects;

    }
    
    public ArrayList toPrint(Login login1){
        ArrayList <Displaying_Data> lectures_info = new ArrayList<Displaying_Data>();
        try {
           
        Connection conn = dbUtil.getConnection();
        String query = "SELECT  user_info.id,user_info.title, "
                + "user_info.first_name, "
                + "user_info.last_name, "
                + "user_info.email, "
                + "user_info.contact, "
                + "user_info.contact_type, "
                + "user_info.roll_num, "
                + "year.year, "
                + "subject.subject, a.description, "
                + "venue.venue_name, a.start_time, a.end_time "
                + "FROM lecture_details AS a "
                + "INNER JOIN mu_db1.subject ON (a.subject_id_fk = subject.id) "
                + "INNER JOIN venue ON (a.venu_id_fk = mu_db1.venue.id) "
                + "INNER JOIN mu_db1.year ON (a.year_id_fk = year.id) "
                + "INNER JOIN user_info  ON (a.user_id_fk = user_info.id) "
                + "WHERE user_info.id = " + getLoginID(login1);
                

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        
            while (rs.next()) {
                Displaying_Data obj = new Displaying_Data();
                
                obj.setStart(rs.getString("start_time"));
                obj.setEnd(rs.getString("end_time"));
                obj.setVenue(rs.getString("venue_name"));
                obj.setDescription(rs.getString("description"));
                obj.setSubject(rs.getString("subject"));
                lectures_info.add(obj);
                
            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! in to print !");
            System.err.println(e.getMessage());
        }
        return lectures_info;
    }
    
    public String getNameOfUser(Login login) {
        String returned = "";

        try {
            Connection conn = dbUtil.getConnection();
            String query = "SELECT * FROM login";
            String query1 = "SELECT * FROM user_info";
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSet rs1 = st1.executeQuery(query1);
            
            while (rs.next()) {
                if (rs.getString("user_name").equals(login.getUser_name() ) && rs.getString("password").equals(login.getPassword() ) )
                {
                    int id = rs.getInt("id");
                    
                    
                    while(rs1.next()){
                        
                        if (id == rs1.getInt("id"))
                            returned = rs1.getString("first_name") + " "+ rs1.getString("last_name");
                    }
                }
                
                

            }
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! b,b,bb it is here!!");
            System.err.println(e.getMessage());
        }
     
        return returned.toUpperCase();

    }
    public int getLoginID(Login login) throws SQLException {
        int id = 0;

        try {
            Connection conn = dbUtil.getConnection();
            String query = "SELECT * FROM login";
           
            Statement st = conn.createStatement();
           
            ResultSet rs = st.executeQuery(query);
            
            
            while (rs.next()) {
                if (rs.getString("user_name").equals(login.getUser_name())
                        && rs.getString("password").equals(login.getPassword() ) )
                
                    id = rs.getInt("id");
                    System.out.println(id + "   fdfdfaa");
                    
                }
                
                

            }
            
         catch (SQLException e) {
            System.err.println("Got an exception!  it is IN GETLOGINID!!");
            System.err.println(e.getMessage());
        }
     
       return id;

    }

}

//// public void fetchfromteacher(String id) {
//
//      try {
//
//          Connection conn = dbUtil.getConnection();
//        String query = "SELECT * FROM teacher ";
//      Statement st = conn.createStatement();
//    ResultSet rs = st.executeQuery(query);
//  while (rs.next()) {
//    if (id.equals(rs.getString("id"))) {
//      String title = rs.getString("title");
//    String first = rs.getString("first name");
//  String last = rs.getString("last name");
//String subject = rs.getString("subject");
//System.out.println(title);
//System.out.println(first);
//System.out.println(last);
//System.out.println(subject);
// break;
//}
//}
//st.close();
//} catch (Exception e) {
//  System.err.println("Got an exception! ");
//System.err.println(e.getMessage());
//}

    //}
