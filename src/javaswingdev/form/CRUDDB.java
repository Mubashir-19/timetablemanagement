package javaswingdev.form;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUDDB {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Statement stt;
    private Statement sttt;
    private ResultSet rs2;

    CRUDDB() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetable", "root", "");
            st = con.createStatement();
            stt = con.createStatement();
            sttt = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    String sql;

    public void registerTeacher(String teachername, String subject, String education, String rank) {
        sql = "insert into teachers (teachername, subject, education, rank) VALUES ('" + teachername + "', '" + subject + "', '" + education + "', '" + rank + "')";
        sqlExecute(sql);
    }

    public void registerSemester(String department, String semester, String section) {
        sql = "insert into semesters (department, semester, section) VALUES ('" + department + "', '" + semester + "', '" + section + "')";
        sqlExecute(sql);
    }

    public int registerCourses(String courseId, String courseName, String courseInstructor, String department, String section, String semester, String courseType) {

        String sql = "SELECT id FROM courses where courseId = '" + courseId + "'  and department = '" + department + "' and semester = '" + semester + "' and section = '" + section + "';";

        try {
            rs = st.executeQuery(sql);

            if (rs.next() == false) {
                sql = "INSERT INTO `courses` (`id`, `courseId`, `courseName`, `courseInstructor`, `department`, `semester`, `section`, `courseType`) VALUES (NULL, '" + courseId + "', '" + courseName + "', '" + courseInstructor + "', '" + department + "', '" + semester + "', '" + section + "', '" + courseType + "');";
                sqlExecute(sql);
            } else {
                return 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;

    }
    //UPDATE `timings` SET `from` = '10:30', `to` = '12:30', `shift` = 'evening', `classLab` = 'lab', `day` = 'monday' WHERE `timings`.`id` = 1;

    public void registerTimings(String timing1, String timing2, String timing3, String timing4, String timing5) {
        sql = "INSERT INTO `timing` (`id`, `timing_from`, `timing_to`, `shift`, `type`, `day`) VALUES (NULL, '" + timing1 + "', '" + timing2 + "', '" + timing3 + "', '" + timing4 + "', '" + timing5 + "')";
        sqlExecute(sql);

        sql = "truncate alltimings;";
        sqlExecute(sql);

        sql = "insert into alltimings select @n := @n + 1 n,timing_from,timing_to,shift,type,day,room,Lab from timing,(SELECT @n := 0) m inner join classrooms;";
        sqlExecute(sql);

        int id = 1;
        try {
            rs = st.executeQuery("with counted as ( select timing_from,timing_to,shift,type,day,room,Lab, count(*) over (partition by day) as cnt from alltimings ) select timing_from,timing_to,shift,type,day,room,Lab from counted order by (row_number() over (order by cnt desc, day) - 1) % max(cnt) over (), row_number() over (order by cnt desc, day);");
            //return rs;
            //stt.executeUpdate("TRUNCATE timetable.classestiming");

            while (rs.next()) {
                stt.executeUpdate("REPLACE INTO alltimings (id,timing_from,timing_to,shift,type,day,room,Lab) VALUES (" + id + ", '" + rs.getString("timing_from") + "', '" + rs.getString("timing_to") + "', '" + rs.getString("shift") + "', '" + rs.getString("type") + "', '" + rs.getString("day") + "', '" + rs.getString("room") + "', '" + rs.getString("Lab") + "')");
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reArrange() {

        sql = "truncate alltimings;";
        sqlExecute(sql);

        sql = "insert into alltimings select @n := @n + 1 n,timing_from,timing_to,shift,type,day,room,Lab from timing,(SELECT @n := 0) m inner join classrooms;";
        sqlExecute(sql);

        int id = 1;
        try {
            rs = st.executeQuery("with counted as ( select timing_from,timing_to,shift,type,day,room,Lab, count(*) over (partition by day) as cnt from alltimings ) select timing_from,timing_to,shift,type,day,room,Lab from counted order by (row_number() over (order by cnt desc, day) - 1) % max(cnt) over (), row_number() over (order by cnt desc, day);");
            //return rs;
            //stt.executeUpdate("TRUNCATE timetable.classestiming");

            while (rs.next()) {
                stt.executeUpdate("REPLACE INTO alltimings (id,timing_from,timing_to,shift,type,day,room,Lab) VALUES (" + id + ", '" + rs.getString("timing_from") + "', '" + rs.getString("timing_to") + "', '" + rs.getString("shift") + "', '" + rs.getString("type") + "', '" + rs.getString("day") + "', '" + rs.getString("room") + "', '" + rs.getString("Lab") + "')");
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void arrangeClasses() {

        int id = 1;
        try {
            rs = st.executeQuery("with counted as ( select id,courseId,courseName, courseInstructor, department, semester, section, credit_hour, type, count(*) over (partition by courseInstructor) as cnt from classes ) select id,courseId,courseName, courseInstructor, department, semester, section, credit_hour, type from counted order by (row_number() over (order by cnt desc, courseInstructor) - 1) % max(cnt) over (), row_number() over (order by cnt desc, courseInstructor);");
            //return rs;
            //stt.executeUpdate("TRUNCATE timetable.classestiming");

            while (rs.next()) {
                stt.executeUpdate("REPLACE INTO classes (id,courseId,courseName, courseInstructor, department, semester, section, credit_hour, type) VALUES (" + id + ", '" + rs.getString("courseId") + "', '" + rs.getString("courseName") + "', '" + rs.getString("courseInstructor") + "', '" + rs.getString("department") + "', '" + rs.getString("semester") + "', '" + rs.getString("section") + "', '" + rs.getInt("credit_hour") + "', '"+rs.getString("type")+"')");
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //with counted as ( select id,courseId,courseName, courseInstructor, department, semester, section, type, count(*) over (partition by courseInstructor) as cnt from classes ) select id,courseId,courseName, courseInstructor, department, semester, section, type from counted order by (row_number() over (order by cnt desc, courseInstructor) - 1) % max(cnt) over (), row_number() over (order by cnt desc, courseInstructor);
    public void registerClassrooms(String department, String section, String semester) {
        sql = "insert into classrooms (department, section, semester) VALUES ('" + department + "', '" + section + "', '" + semester + "')";
        sqlExecute(sql);
    }

    public void sqlExecute(String sql) {
        try {
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteData(String table, int id) {

        String sql = "DELETE FROM " + table + " WHERE id = " + id + "";

        String query1 = "Set @autoId :=0;";
        String query2 = "update " + table + " set id = @autoid :=(@autoid+1);";
        String query3 = "ALTER TABLE " + table + " AUTO_INCREMENT = 1;";

        try {
            st.executeUpdate(sql);
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            st.executeUpdate(query3);

            System.out.println("Row" + id + " Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteTeacher(int id) {

        String sql = "DELETE FROM teachers WHERE id = '" + id + "'";

        String query1 = "Set @autoId :=0;";
        String query2 = "update teachers set id = @autoid :=(@autoid+1);";
        String query3 = "ALTER TABLE teachers AUTO_INCREMENT = 1;";

        try {
            st.executeUpdate(sql);
            st.executeUpdate(query1);
            st.executeUpdate(query2);
            st.executeUpdate(query3);

            System.out.println("Row" + id + " Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateTeacher(int id, String teachername, String subject, String education, String rank) {

        //UPDATE `teachers` SET `teachername` = 'mubashirname' WHERE `teachers`.`id` = 1;
        String sql = "UPDATE `teachers` SET `teachername` = '" + teachername + "', `subject` = '" + subject + "', `education` = '" + education + "', `rank` = '" + rank + "' WHERE `teachers`.`id` = " + id + ";";
        sqlExecute(sql);
    }

    public void UpdateSemester(int id, String department, String semester, String section) {

        //UPDATE `teachers` SET `teachername` = 'mubashirname' WHERE `teachers`.`id` = 1;
        String sql = "UPDATE `semesters` SET `department` = '" + department + "', `semester` = '" + semester + "', `section` = '" + section + "' WHERE `semesters`.`id` = " + id + ";";
        sqlExecute(sql);
    }

    public void UpdateTimings(int id, String from, String to, String shift, String purpose, String day) {

        //UPDATE `teachers` SET `teachername` = 'mubashirname' WHERE `teachers`.`id` = 1;
        String sql = "UPDATE `timing` SET `from` = '" + from + "', `to` = '" + to + "', `shift` = '" + shift + "', `classLab` = '" + purpose + "', `day` = '" + day + "' WHERE `timing`.`id` = '" + id + "';";
        sqlExecute(sql);
    }

    public ResultSet getRandomData() {
        //String query = "SELECT * from timing p INNER JOIN `classes` b INNER JOIN `classrooms` f ON p.id = b.id GROUP BY b.id ORDER BY RAND(); ";
        String query = "select classes.id,timing_from, timing_to,shift, day, room, Lab, courseId, courseName, courseInstructor, department, semester, section,classes.type, credit_hour  from alltimings inner join classes on alltimings.id = classes.id;";
        try {
            rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }

        return rs;
    }

    public void SortClasses() {
        int id = 1;
        try {
            rs = st.executeQuery("with counted as ( select id,courseId,courseName,courseInstructor,department,semester,section,credit_hour,type, count(*) over (partition by courseInstructor) as cnt from classes ) select id,courseId,courseName,courseInstructor,department,semester,section,credit_hour,type from counted order by (row_number() over (order by cnt desc, courseInstructor) - 1) % max(cnt) over (), row_number() over (order by cnt desc, courseInstructor);");
            //return rs;
            //stt.executeUpdate("TRUNCATE timetable.classestiming");

            while (rs.next()) {
                stt.executeUpdate("REPLACE INTO classes (id,courseId,courseName,courseInstructor,department,semester,section,credit_hour,type) VALUES (" + id + ", '" + rs.getString("courseId") + "', '" + rs.getString("courseName") + "', '" + rs.getString("courseInstructor") + "', '" + rs.getString("department") + "', '" + rs.getString("semester") + "', '" + rs.getString("section") + "', " + rs.getInt("credit_hour") + ", '" + rs.getString("type") + "')");
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet checkClash(String tableName, String from, String day) {

        String sql = "select * from " + tableName + " where timing_from='" + from + "' and day='" + day + "'";
        try {
            rs = st.executeQuery(sql);

        } catch (Exception e) {
            System.out.println(e);
        }

        return rs;
    }

    public ResultSet getUsers(String tableName) {

        String sql = " select * from " + tableName + " ";
        try {

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    public void Classes_reBirth() {

        sqlExecute("TRUNCATE timetable.classes;");
        sqlExecute("INSERT INTO classes (courseId, courseName, courseInstructor, department, semester, section, credit_hour, type) SELECT courses.courseId, courses.courseName,courseInstructor,courses.department,semester,section, credit_hour, \"Class\" FROM courses,allcourses WHERE courses.courseName = allcourses.courseName UNION ALL SELECT courses.courseId, courses.courseName,courseInstructor,courses.department,semester,section, credit_hour, \"Class\" FROM courses,allcourses WHERE courses.courseName = allcourses.courseName UNION ALL SELECT courses.courseId, courses.courseName,courseInstructor,courses.department,semester,section, credit_hour, \"Lab\" FROM courses,allcourses WHERE credit_hour = 4 and courses.courseName = allcourses.courseName ORDER BY courseName;");

    }

    public ResultSet getMatchingSemesters(Object department) {
        String sql = "SELECT DISTINCT semester from semesters where department = '" + department + "' ";

        try {

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet getMatchingSections(String department, String semester) {
        String sql = "SELECT DISTINCT section from semesters where department = '" + department + "' and semester = '" + semester + "' ";

        try {

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    public void generateClassTiming() {

        String query1 = "select room,Lab from classrooms";
        String query2 = "truncate timetable.classestiming;";
        int count = 7;

        try {
            rs = st.executeQuery("SELECT COUNT(*) FROM classrooms;");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String[][] arr = new String[2][count];
        try {
            st.executeUpdate(query2);
            rs2 = stt.executeQuery(query1);
            while (rs2.next()) {

                arr[0][i] = rs2.getString("room");
                arr[1][i] = rs2.getString("Lab");
                i++;
            }
        } catch (SQLException e) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, e);
        }

        try {
            rs2 = stt.executeQuery("select * from timing");

            while (rs2.next()) {
                for (int j = 0; j < count; j++) {
                    if (rs2.getString("type").equals("Class")) {
                        st.executeUpdate("INSERT INTO `classestiming` (`id`, `fromTo`, `type`, `day`, `room`) VALUES (NULL, '" + rs2.getString("timing_from") + " - " + rs2.getString("timing_to") + "', '" + rs2.getString("type") + "', '" + rs2.getString("day") + "', '" + arr[0][j] + "');");
                    } else {
                        st.executeUpdate("INSERT INTO `classestiming` (`id`, `fromTo`, `type`, `day`, `room`) VALUES (NULL, '" + rs2.getString("timing_from") + " - " + rs2.getString("timing_to") + "', '" + rs2.getString("type") + "', '" + rs2.getString("day") + "', '" + arr[1][j] + "');");

                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        int id = 1;
        try {
            rs = st.executeQuery("with counted as ( select id,fromTo,type,day,room, count(*) over (partition by day) as cnt from classestiming ) select id,fromTo,type,day,room from counted order by (row_number() over (order by cnt desc, day) - 1) % max(cnt) over (), row_number() over (order by cnt desc, day);");
            //return rs;
            //stt.executeUpdate("TRUNCATE timetable.classestiming");

            while (rs.next()) {
                stt.executeUpdate("REPLACE INTO classestiming (id,fromTo,type,day,room) VALUES (" + id + ", '" + rs.getString("fromTo") + "', '" + rs.getString("type") + "', '" + rs.getString("day") + "', '" + rs.getString("room") + "')");
                id++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet getDistinctvalues(String table) {

        String sql = "select DISTINCT department from semesters";

        try {

            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }
}
//INSERT INTO teachers (teachername, subject, education, rank) VALUES ('mubashir', 'maths', 'masters', ' external');

//INSERT INTO computerscience (coursecode, section, semester, department, coursename) VALUES ('CSC101', 'B', ' 1', 'Computer Science', 'Programming Fundamentals');
