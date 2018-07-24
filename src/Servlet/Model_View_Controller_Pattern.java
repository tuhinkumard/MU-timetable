package Servlet;
import com.nm.bean.Displaying_Data;
import com.nm.bean.User_Info;
import com.nm.dao.DBConnector;
import com.nm.bean.Login;
import com.nm.bean.TheDate;
import com.nm.bean.Lecture_Detail;
import com.nm.bean.Subject;
import com.nm.bean.Venue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Model_View_Controller_Pattern extends HttpServlet{
    DBConnector mydbConnect = new DBConnector();
        
        User_Info userSignup = new User_Info();
        Login login = new Login();
        TheDate date = new TheDate();
        Lecture_Detail lecture = new Lecture_Detail();
        Subject subject = new Subject ();
        Venue venue = new Venue();
       
        
       
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("student")) {

            userSignup.setTitle(request.getParameter("Title"));
            userSignup.setFirst_name(request.getParameter("firstName"));
            userSignup.setLast_name(request.getParameter("lastName"));
            userSignup.setContact(Long.parseLong(request.getParameter("Contactnumber").trim()));
            userSignup.setRoll_num(request.getParameter("regID"));
            userSignup.setEmail(request.getParameter("Email"));
            userSignup.setTime_stamp(date.getDate());
            
            login.setUser_name(request.getParameter("Username"));
            login.setPassword(request.getParameter("Password"));
            
            try {
                boolean result1;
                result1 = mydbConnect.addUserInfo(userSignup, action);
                boolean result2 = mydbConnect.addLoginInfo(login);
            } catch (Exception ex) {
                Logger.getLogger(Model_View_Controller_Pattern.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equalsIgnoreCase("teacher")) {
            try {
            userSignup.setTitle(request.getParameter("Title"));
            userSignup.setFirst_name(request.getParameter("firstName"));
            userSignup.setLast_name(request.getParameter("lastName"));
            userSignup.setContact(Long.parseLong(request.getParameter("Contactnumber").trim()));
            userSignup.setRoll_num(request.getParameter("regID"));
            userSignup.setEmail(request.getParameter("Email"));
            userSignup.setTime_stamp(date.getDate());
            
            login.setUser_name(request.getParameter("Username"));
            login.setPassword(request.getParameter("Password"));
            
            boolean result = mydbConnect.addUserInfo(userSignup, action);
            boolean result2 = mydbConnect.addLoginInfo(login);
            } catch (SQLException e) {
                Logger.getLogger(Model_View_Controller_Pattern.class.getName()).log(Level.SEVERE, null, e);
            }
        } else if (action.equals("lecture")) {
            try {
                
                lecture.setStart_time(request.getParameter("start_time"));
                lecture.setEnd_time(request.getParameter("end_time"));
                lecture.setTime_stamp(date.getDate());
                lecture.setStatus("A");
                lecture.setDescription(request.getParameter("note"));
                subject.setSubject(request.getParameter("Subject"));
                subject.setTime_stamp (date.getDate());
                venue.setVenue_name(request.getParameter("venue"));
                venue.setTime_stamp(date.getDate());
                boolean result2 = mydbConnect.addSubject(subject);
                boolean result = mydbConnect.addLectureInfo(lecture);
                boolean result3 = mydbConnect.addVenue(venue);
                
               response.sendRedirect(request.getHeader("TEACHER_CALENDAR"));
               // request.getRequestDispatcher("/TEACHER_CALENDAR.jsp").forward(request, response);
                //request.getRequestDispatcher("/TEACHER_CALENDAR.jsp").forward(request, response);
               
            } catch (SQLException e) {
                Logger.getLogger(Model_View_Controller_Pattern.class.getName()).log(Level.SEVERE, null, e);
            }

        } else if (action.equals("login")) {
            String s = null;
            login.setUser_name(request.getParameter("username"));
            login.setPassword(request.getParameter("password"));
            User_Info useInfo = new User_Info();
            try {
            	useInfo = mydbConnect.fetch(request.getParameter("username"), request.getParameter("password"));
               
                if (useInfo.getContact_type().equals("")) {
                      
                      response.sendRedirect("/MU timetable/login.jsp");
            } else {
                   //String user = mydbConnect.getNameOfUser(login);
                    //System.out.println("name of user is " + user);
                if (useInfo.getContact_type().equalsIgnoreCase("S")) {
                    request.setAttribute("login",login);
                    request.setAttribute("NAME", user);
                    request.getRequestDispatcher("/STUDENT_CALENDAR.jsp").forward(request, response);
                } else if (useInfo.getContact_type().equalsIgnoreCase("T")) {
                    ArrayList<Displaying_Data> lectures_info = new ArrayList<Displaying_Data>();
                    lectures_info = mydbConnect.toPrint(login);
                    request.setAttribute("login", login);
                    ArrayList<Lecture_Detail> lectures = new ArrayList<Lecture_Detail>();
                    ArrayList<Venue> venues = new ArrayList<Venue>();
                    ArrayList<Subject> subjects = new ArrayList<Subject>();
                    lectures = mydbConnect.getLectures();
                    venues = mydbConnect.getVenues();
                    request.setAttribute("NAME", user);
                   
                    subjects = mydbConnect.getSubjects();
                    request.setAttribute("info", lectures_info);
                    request.setAttribute("lecture", lectures);
                    request.setAttribute("subjects", subjects);
                    request.setAttribute("venues", venues);
                    request.getRequestDispatcher("/TEACHER_CALENDAR.jsp").forward(request, response);
                   
                }
            }
                //lectures = mydbConnect.getLecture(Login);
               
            } catch (SQLException ex) {
                Logger.getLogger(Model_View_Controller_Pattern.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            } else if ( action.equals("remove")){
                 System.out.println("fnqkfndkqn reached here +++++++++++++=++++");
        System.out.println(request.getParameter("start_time"));
         System.out.println(request.getParameter("end_time"));
          System.out.println(request.getParameter("note"));
           System.out.println(request.getParameter("Subject"));
            System.out.println(request.getParameter("venue"));
            
            
    } 
        
        }
    }


