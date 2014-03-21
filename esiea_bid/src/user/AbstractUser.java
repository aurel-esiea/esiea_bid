package user;

import java.util.List;
import java.util.Set;

import objects.Offer;
import alarm_time.Alarm;

public abstract class AbstractUser {
		
	protected String lastName;
	protected String firstName;
	protected String password;

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
}
