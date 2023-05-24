package model;

public class Admin {
	private int id;
	private String name;
	private int password;
	public Admin(int id, String name, int password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Admin(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public Admin(String name, int password) {
		super();
		this.name = name;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [Id=" + id + ", Name=" + name + ", Password=" + password + "]";
	}

    
}
