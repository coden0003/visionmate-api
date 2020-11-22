package se.visionmate.api.v1.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import se.visionmate.api.v1.model.User;

@Component
@Scope("singleton")
public class LoggedUser {

	private User loggedUser = null;
	
	
	public LoggedUser() {
	}

	
	public LoggedUser(User aLoggedUser) {
		setLoggedUser(aLoggedUser);
	}


	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
