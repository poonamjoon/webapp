package example;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example.ProcessBean;
import example.TaskBean;
import org.kie.api.task.model.TaskSummary;

@WebServlet(name = "usertask", urlPatterns = { "/usertask" })
public class UserTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessBean processService;
	@EJB
	private TaskBean taskService;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("======== in task servlet ,do post========");
		doGet(req, res);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		long processInstanceId = -1;
		System.out.println("======== in usertask servlet ,doGet ========");
		
		try
		{
		taskService.retrieveTaskList("Administrator");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		
		}
	}

		
		
		
		

	

	private void approveAllTasks(List<TaskSummary> taskList, String actor) {

		try {

			System.out.println("======== TASK LIST ========");
			for (TaskSummary task : taskList) {

				System.out.println(task.getName());
				System.out.println(task.getId());
				System.out.println(task.getProcessInstanceId());

				taskService.approveTask(actor, task.getId());

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}