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

package util;

import java.util.HashMap;
import java.util.Map;
//import org.infy.*;

//import org.jbpm.runtime.manager.api.WorkItemHandlerProducer;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.internal.runtime.manager.WorkItemHandlerProducer;

public class SimpleWorkItemHandlerProducer implements WorkItemHandlerProducer {

 
    public Map<String, WorkItemHandler> getWorkItemHandlers(String s, Map<String, Object> stringObjectMap) {
        // add any WorkItemHandlers that should be registered on the session
    	System.out.println("**********Kna inside Workitem handler*********");
    	Map<String, WorkItemHandler> handlers = new HashMap<String, WorkItemHandler>();
        handlers.put("Custom", new org.infy.myworkitem());
        return new HashMap<String, WorkItemHandler>();
    }
}
