package util;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;


//import org.jbpm.kie.services.api.IdentityProvider;

import org.kie.internal.identity.IdentityProvider;

// dummy CustomIdentityProvider solves:
// org.jboss.weld.exceptions.DeploymentException: WELD-001408 Unsatisfied dependencies for type [IdentityProvider]
// with qualifiers [@Default] at injection point [[field] @Inject private org.jbpm.kie.services.impl.KModuleDeploymentService.identityProvider]
@ApplicationScoped
public class CustomIdentityProvider implements IdentityProvider {

    public String getName() {
        return "dummy";
    }

    public List<String> getRoles() {

        return Collections.emptyList();
    }

	public boolean hasRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
