import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class WeakServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluate","root","");
            Statement st=con.createStatement();
            String query="select rollno,name,marks from Student where marks < 60";
            ResultSet rs=st.executeQuery(query);
            out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("table {");
        out.println("    border-collapse: collapse;");
        out.println("    width: 50%;");
        out.println("    margin: 0 auto;");
        out.println("}");

        out.println("th, td {");
        out.println("    border: 1px solid black;");
        out.println("    padding: 8px;");
        out.println("    text-align: center;");
        out.println("}");

        out.println("th {");
        out.println("    background-color: #f2f2f2;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Name</th>");
        out.println("<th>Roll Number</th>");
        out.println("<th>Marks</th>");
        out.println("</tr>");

        while (rs.next()) {
            String n = rs.getString("name");
            String rn = rs.getString("rollno");
            String m = rs.getString("marks");
            out.println("<tr>");
            out.println("<td>" + n + "</td>");
            out.println("<td>" + rn + "</td>");
            out.println("<td>" + m + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
            con.close();
            st.close();
        }
        catch(Exception e){
            out.println(e);
        }
    }
}