package alarm_time;

import java.util.Date;
import java.util.Observable;


public class TimeManager extends Observable{
	private Date systemTime;
	
	
	public TimeManager(Date date, TimeObserver timeObserver){
		this.systemTime = date;
		this.addObserver(timeObserver);
	}
	
	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
		setChanged();
		notifyObservers();
	}
}

