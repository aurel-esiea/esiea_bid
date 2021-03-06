package user;

import java.util.List;

public abstract class AbstractUser {
		
	protected String login;
	protected String lastName;
	protected String firstName;
	protected String password;
	protected List<String> notificationList;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<String> getNotificationList() {
		return notificationList;
	}
	public void setNotificationList(List<String> notificationList) {
		this.notificationList = notificationList;
	}
}
