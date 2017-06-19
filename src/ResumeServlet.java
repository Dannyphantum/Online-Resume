

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class ResumeServlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResumeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: AppleBees").append(request.getContextPath());
		
		String username = request.getParameter("username");
		request.setAttribute("UserName", username);
		
		String email = request.getParameter("email");
		request.setAttribute("UserEmail", email);
		
		ArrayList<String> schoolin = new ArrayList<>();
		
		for(int i=1; i < 11; i++){
			String edu = "education" + i;
			String education = request.getParameter(edu);
			if(!education.equals("")) schoolin.add(education);
		}
		request.setAttribute("Education",schoolin);
		
		ArrayList<String> workin = new ArrayList<>();
		
		for(int i =1; i < 11; i++){
			String work = "workExperience" + i;
			String workExperience = request.getParameter(work);
			if(!workExperience.equals("")) workin.add(workExperience);
		}
		request.setAttribute("Work", workin);
		
		ArrayList<String> SkillsList = new ArrayList<>();
		
		for (int i = 1; i < 21; i++){
			String userskills = "skill" + i;
			String skill = request.getParameter(userskills);
			if(!skill.equals("")) SkillsList.add(skill);
		}
		request.setAttribute("UserSkill", SkillsList);
		
		//DBConnection connect = new DBConnection();
		
		//connect.addRecord("userinfo", "username", "email", username, email);
		
		try {
			Connection con = null;
			PreparedStatement statement = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/roboresume?user=root&password=password");
			String sql = String.format("insert into %s (%s,%s, %s) values (?,?)", "userinfo", "username", "email", "skills.skill", username, email, SkillsList);
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, email);
			System.out.println(sql);
			statement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		
	}
	
/*
	public void addRecord(String table, String column1, String column2, String username, String email) {
		try {
			Connection con = null;
			PreparedStatement statement = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost/robo_resume?user=root&password=password");
			String sql = String.format("insert into %s (%s,%s) values (?,?)", userinfo, column1, column2, username, email);
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, email);
			System.out.println(sql);
			statement.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();}
	}
	*/
}
