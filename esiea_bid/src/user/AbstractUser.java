package user;

import esiea_bid.MemoryObject;

public abstract class AbstractUser {
		
	private int idUser;
	private static int currentId = 0; 
	private String lastName;
	private String firstName;
	private String password;
	
	public AbstractUser (String lastName, String firstName, String password)
	{
		this.idUser = currentId;
		currentId ++;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}
	public int loginUser(MemoryObject memoryObject, String password)
	{
		if (this.password.equals(password))
		{
			if(memoryObject.connectedUsers.contains(this.idUser))
			{
				System.out.println("User " + this.lastName + " already connected");
				return 0;
			}
			else
			{
				memoryObject.connectedUsers.add(this.idUser);
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
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
