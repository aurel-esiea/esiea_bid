package user;

import java.util.List;
import java.util.Set;

import esiea_bid.Alarm;
import esiea_bid.Offer;

public abstract class AbstractUser {
		
	protected String lastName;
	protected String firstName;
	protected String password;
	protected List<Alarm> listAlarm;
	protected Set listAlarmMessage;

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

	public List<Alarm> getListAlarm() {
		return listAlarm;
	}

	public void setListAlarm(List<Alarm> listAlarm) {
		this.listAlarm = listAlarm;
	}
}
