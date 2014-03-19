package user;

import java.util.List;

public abstract class AbstractUser {
		
	protected String lastName;
	protected String firstName;
	protected String password;

	public int loginUser(List<Object> listUserConnected, String password)
	{
		if (this.password.equals(password))
		{
			if(listUserConnected.contains(this))
			{
				System.out.println("User " + this.lastName + " already connected");
				return 0;
			}
			else
			{
				listUserConnected.add(this);
				System.out.println("Connected");		
				return 1;
			}
		}
		else
			System.out.println("Connection refused");
			return 2;
	}

	public String getNom() {
		return lastName;
	}
	public void setNom(String lastName) {
		this.lastName = lastName;
	}
	public String getPrenom() {
		return firstName;
	}
	public void setPrenom(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
