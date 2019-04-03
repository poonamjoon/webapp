<html>
   <body>
      <form action = "http://localhost:8080/jbpm6-basic/task" method = "POST">
         Process Name: <input type = "text" name = "process_name">
         <br />
         Service Request Number: <input type = "text" name = "service_req_num" />
         <input type = "submit" value = "Start process" />
      </form>
      
      <form action = "http://localhost:8080/jbpm6-basic/usertask" method = "GET">
         <input type = "submit" value = "Get Task List" />
      </form>
   </body>
</html>