package alarm_time;

import objects.Bid;
import user.SystemUser;

public class Alarm {
	
	private AlarmType alarmType;
	private Bid bid;
	private SystemUser user;
	
	public Alarm(AlarmType alarmType, Bid bid, SystemUser user){
		this.alarmType =alarmType;
		this.bid = bid;
		this.user = user;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	

}
