package cn.gaowenbonum1.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.gaowenbonum1.service.UserService;
import cn.gaowenbonum1.utils.BaseServlet;
import cn.gaowenbonum1.vo.User;



public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	public String check(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// ��session�л���û�����Ϣ
		User existUser = (User) req.getSession().getAttribute("existUser");
		// �ж�session�е��û��Ƿ����
		if(existUser == null){
			// ��¼����Ϣ�Ѿ�������!
			resp.getWriter().println("1");
		}else{
			// ��¼����Ϣû�й���
			resp.getWriter().println("2");
		}
		return null;
	}
	
	/**
	 *  �˳�������
	 * @throws IOException 
	 */
	public String exit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// ���session����
		HttpSession session = request.getSession();
		// ��session����.
		session.invalidate();
		// ҳ��ת��.
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return null;
	}
	
	/**
	 * ������������
	 * @throws IOException 
	 */
	public String sendMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.�������� ��
		System.out.println("sendMessage invoke....");
		String from = req.getParameter("from"); // ������
		String face = req.getParameter("face"); // ����
		String to = req.getParameter("to"); // ������
		String color = req.getParameter("color"); // ������ɫ
		String content = req.getParameter("content"); // ��������
		// ����ʱ�� ���������ʹ��SimpleDateFormat
		String sendTime = new Date().toLocaleString(); // ����ʱ��
		ServletContext application = getServletContext();
		String sourceMessage = (String) application.getAttribute("message");
		sourceMessage += "<font color='blue'><strong>" + from
				+ "</strong></font><font color='#CC0000'>" + face
				+ "</font>对<font color='green'>[" + to + "]</font>说"
				+ "<font color='" + color + "'>" + content + "</font>"
				+ sendTime + "<br>";
		// ����Ϣ���뵽application�ķ�Χ
		application.setAttribute("message", sourceMessage);
		return getMessage(req, resp);
	}
	
	/**
	 * ��ȡ��Ϣ�ķ���
	 * @throws IOException 
	 */
	public String getMessage(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String message = (String) getServletContext().getAttribute("message");
		if(message != null){
			resp.getWriter().println(message);
		}
		return null;
	}
	/**
	 * ���˵Ĺ���
	 * @throws IOException 
	 */
	public String kick(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		// 1.���ղ���
		int id = Integer.parseInt(req.getParameter("id"));
		// 2.����:��userMap�н��û���Ӧ��session����.
		// ���userMap����(�����б�)
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
				.getAttribute("userMap");
		// �������û���Ӧ��session.���֪�����ĸ��û���? id�Ѿ����ݹ���.ȥ���ݿ��в�ѯ.
		// ��дuser��equals �� hashCode ���� ��ôֻҪ�û���id��ͬ����Ϊ��ͬһ���û�.
		User user = new User();
		user.setId(id);
		// ��map�����л���û��Ķ�Ӧ��session 
		HttpSession session = userMap.get(user);
		// ����session
		session.invalidate();
		// �ض���ҳ��
		resp.sendRedirect(req.getContextPath()+"/main.jsp");
		return null;
	}
	
	/**
	 * ��¼�Ĺ���
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		// ��������
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();
		// ��װ����
		try {
			BeanUtils.populate(user, map);
			// ����Service�㴦������ 
			UserService us = new UserService();
			User existUser = us.login(user);
			if (existUser == null) {
				// �û���¼ʧ��
				req.setAttribute("msg", "用户名或密码错误!");
				return "/index.jsp";
			} else {
				// �û���¼�ɹ�
				// ��һ��BUG�Ľ��:�ڶ����û���¼��֮ǰ��session����!
				req.getSession().invalidate();
				
				// �ڶ���BUG�Ľ��:�ж��û��Ƿ��Ѿ���Map������,���ڣ��Ѿ����б���.������session.
				// ��õ�ServletCOntext�д��Map����.
				Map<User, HttpSession> userMap = (Map<User, HttpSession>) getServletContext()
						.getAttribute("userMap");
				// �ж��û��Ƿ��Ѿ���map������'
				if(userMap.containsKey(existUser)){
					// ˵��map��������û�.
					HttpSession session = userMap.get(existUser);
					// �����session����.
					session.invalidate();
				}
				
				// ʹ�ü�����:HttpSessionBandingListener������JavaBean�ϵļ�����.
				req.getSession().setAttribute("existUser", existUser);
				ServletContext application = getServletContext();

				String sourceMessage = "";

				if (null != application.getAttribute("message")) {
					sourceMessage = application.getAttribute("message")
							.toString();
				}

				sourceMessage += "系统公告:<font color='gray'>"
						+ existUser.getUsername() + "走进了聊天室!</font><br>";
				application.setAttribute("message", sourceMessage);

				resp.sendRedirect(req.getContextPath() + "/main.jsp");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
