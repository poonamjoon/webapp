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

@WebServlet(name = "task", urlPatterns = { "/task" })
public class TaskServlet extends HttpServlet {

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
		System.out.println("======== in task servlet ,doGet ========");
		
		String process_name=req.getParameter("process_name");
		String servReqId =req.getParameter("service_req_num");
		
		System.out.println("======== in task servlet ,doGet , process name="+process_name);
		System.out.println("======== in task servlet ,doGet , service req id="+servReqId);
		
		if (servReqId!= null)
		{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name1","Poonam1" );
			params.put("age1","1134" );
           try
           { System.out.println("in task servlet ,servicereqid is not null,calling startprocess");
			processInstanceId = processService.startProcess(
					"test_wk.test-process", params);
           }
           catch (Exception e) {
   			throw new ServletException(e);
   		}
         
		} else
		{
			try
	           { System.out.println("in task servlet ,servicereqid is null,calling startprocess");
				processInstanceId = processService.startProcess("cditest.hellocdi");
	           }
	           catch (Exception e) {
	   			throw new ServletException(e);
		}

		}
		
		
		System.out.println("Process started with Id " + processInstanceId);
	/*	try
		{
			System.out.println("retreiving taskid for  processInstance" +processInstanceId);
		taskService.retrieveTaskListProcessInstance(processInstanceId);
		}catch (Exception e) {
   			throw new ServletException(e);
		}
   			
   			try
   			{
   				System.out.println("retreiving task list for ba");
   			taskService.retrieveTaskListBA("bpmuser");
   			}catch (Exception e1) {
   	   			throw new ServletException(e1);
   			
	            }
		
   			try
   			{
   				System.out.println("retreiving task array for processInstance" +processInstanceId);
   			taskService.getTasks(processInstanceId);
   			}catch (Exception e1) {
   	   			throw new ServletException(e1);
   			
	            }*/
		/**
		try {
			String action = req.getParameter("action");

			System.out.println("======== in task servlet ,action="+action);
			
			if (action.equals("create")) {
				System.out.println("======== in task servlet , action is create========");
			
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("money", 2000);

				processInstanceId = processService.startProcess(
						"com.sample.bpmn", params);
					
				
				
				out.println("<br/><a href=\"task?action=submit\">Complete Task</a> <br/>");
			} else if (action.equals("submit")) {
				List<TaskSummary> taskListJohn = taskService
						.retrieveTaskList("john");
				approveAllTasks(taskListJohn, "john");

				out.println("John Appoved ");
				out.println("<a href=\"index.jsp\">Home</a> <br/>");

			}
			
**/
		

	      String title = "Process and Task Management";
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " +
	         "transitional//en\">\n";
	         
	      out.println(docType +
	         "<html>\n" +
	            "<head><title>" + title + "</title></head>\n" +
	            "<body bgcolor = \"#f0f0f0\">\n" +
	               "<h1 align = \"center\">" + title + "</h1>\n" +
	               "<ul>\n" +
	                  "  <li><b>Process Name</b>: "
	                  + req.getParameter("process_name") + "\n" +
	                  "  <li><b>Service Request Number</b>: "
	                  + req.getParameter("service_req_num") + "\n" +
	                  "  <li><b>Process Instance Id</b>: "
	                  + processInstanceId + "\n" +
	                  "</ul>\n" + 
                 "</body>"+
	              "</html>"        
	      );		
	    	 
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