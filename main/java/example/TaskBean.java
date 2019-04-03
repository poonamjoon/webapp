
/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.jbpm.services.task.exception.PermissionDeniedException;
import org.kie.api.task.model.Task;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;

@Stateless
public class TaskBean  {

    @Inject
    TaskService taskService;
    
    public List<TaskSummary> retrieveTaskList(String actorId) throws Exception {

        List<TaskSummary> list;
        
        try {
            list = taskService.getTasksAssignedAsPotentialOwner(actorId, "en-UK");
  
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("retrieveTaskList by " + actorId);
        for (TaskSummary task : list) {
            System.out.println(" task.getId() = " + task.getId());
        }

        return list;
    }

    public Task[] getTasks(long processInstanceId) throws Exception {

    		List<Long> taskIds = taskService
    				.getTasksByProcessInstanceId(processInstanceId);

    		Task[] tasks = new Task[taskIds.size()];
    		Long indTaskId;
    		Map<String, Object> params = new HashMap<String, Object>();
			params.put("status_out", 2000);
    		int i = 0;
    		for (Long id : taskIds) {
    			tasks[i++] = taskService.getTaskById(id);
    			indTaskId=taskService.getTaskById(id).getId();
    			System.out.println("task name = " + taskService.getTaskById(id).getName());
    			System.out.println("task potential owner = " + taskService.getTaskById(id).getPeopleAssignments().getPotentialOwners());
    			System.out.println("task ba = " + taskService.getTaskById(id).getPeopleAssignments().getBusinessAdministrators());
    			System.out.println("task initiator = " + taskService.getTaskById(id).getPeopleAssignments().getTaskInitiator());
    			System.out.println("claiming task");
    			taskService.claim(indTaskId,"bpmuser");
    			//taskService.start(indTaskId,"Administrator");
    			//taskService.complete(indTaskId,"Administrator",params);
    			taskService.start(indTaskId,"bpmuser");
    			taskService.complete(indTaskId,"bpmuser",params);
    		}

    		return tasks;
    	}
    
    public void approveTask(String actorId, long taskId) throws Exception {

        try {
            System.out.println("approveTask (taskId = " + taskId + ") by " + actorId);
            taskService.start(taskId, actorId);
        
            taskService.complete(taskId, actorId, null);
 
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            
            throw new RuntimeException(e);
        }  
    }
    
    public List<Long> retrieveTaskListProcessInstance(long processInstanceId) throws Exception {

        List <Long> tasklist;
        
        try {
            tasklist = taskService.getTasksByProcessInstanceId(processInstanceId);
  
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//todo: there might be many tasks...so iterate here
        Long taskId = tasklist.get(0);
       
           System.out.println(" taskid = " + taskId);
        

        return tasklist;
    }

    public List<TaskSummary> retrieveTaskListBA(String actorId) throws Exception {

        List<TaskSummary> list;
        
        try {
            list = taskService.getTasksAssignedAsBusinessAdministrator(actorId, "en-UK");
  
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("retrieveTaskListBA by " + actorId);
        for (TaskSummary task : list) {
            System.out.println(" task.getId() = " + task.getId());
        }

        return list;
    }

}