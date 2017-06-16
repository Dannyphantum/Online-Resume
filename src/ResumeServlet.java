

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String education = request.getParameter("education");
		request.setAttribute("Education", education);
		String workExperience = request.getParameter("workExperience");
		request.setAttribute("Work", workExperience);
		String skill = request.getParameter("skill");
		request.setAttribute("UserSkill", skill);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

}
