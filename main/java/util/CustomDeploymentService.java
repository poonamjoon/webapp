package util;

import org.kie.api.runtime.manager.RuntimeManager;

import org.jbpm.services.api.model.DeployedUnit;
//import org.kie.internal.deployment.DeploymentService;

import org.jbpm.services.api.DeploymentService;


import org.jbpm.services.api.model.DeploymentUnit;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

import org.jbpm.kie.services.impl.AbstractDeploymentService;


// plain CustomDeploymentService solves:
// Caused by: org.jboss.weld.exceptions.DeploymentException: WELD-001408 Unsatisfied dependencies for type [DeploymentService]
// with qualifiers [@Default] at injection point [[field] @Inject private org.jbpm.kie.services.impl.form.FormProviderServiceImpl.deploymentService]
@ApplicationScoped

public class CustomDeploymentService extends AbstractDeploymentService {


	private RuntimeManager runtimeManager;
	
    @Override
    public void deploy(DeploymentUnit deploymentUnit) {
    }

    @Override
    public void undeploy(DeploymentUnit deploymentUnit) {
    }

    @Override
    public RuntimeManager getRuntimeManager(String s) {
        return runtimeManager;
    }

    @Override
    public DeployedUnit getDeployedUnit(String s) {
        return null;
    }

    @Override
    public Collection<DeployedUnit> getDeployedUnits() {
        return null;
    }

	public void activate(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deactivate(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDeployed(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
/*
public class CustomDeploymentService implements DeploymentService {

 
    public void deploy(DeploymentUnit deploymentUnit) {
    }

    
    public void undeploy(DeploymentUnit deploymentUnit) {
    }

    
    public RuntimeManager getRuntimeManager(String s) {
        return null;
    }

    
    public DeployedUnit getDeployedUnit(String s) {
        return null;
    }

     
    public Collection<DeployedUnit> getDeployedUnits() {
        return null;
    }


	public void activate(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public void deactivate(String arg0) {
		// TODO Auto-generated method stub
		
	}


	public boolean isDeployed(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
*/
