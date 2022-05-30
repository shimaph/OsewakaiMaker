package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import model.ShowMembersLogic;
/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String party = request.getParameter("party");
		int grade = Integer.parseInt(request.getParameter("grade"));
		int sex = Integer.parseInt(request.getParameter("sex"));
		int exp = Integer.parseInt(request.getParameter("exp"));
		
		Member member = new Member(name, party, grade, sex, exp);
		
		HttpSession session = request.getSession();
		
		
		ArrayList<Member> memberList = (ArrayList<Member>)session.getAttribute("memberList");
		
		//メンバーリスト初期化（初回リクエスト時実行）
		if(memberList == null) {
			memberList = new ArrayList<Member>();
		}
		
		//一時的サンプルデータ
		
		memberList.add(member);
		session.setAttribute("memberList", memberList);
		
		ShowMembersLogic showmemberslogic = new ShowMembersLogic();
		session.setAttribute("showMemberList", showmemberslogic.execute(memberList));
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}
}
