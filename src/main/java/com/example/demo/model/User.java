package com.example.demo.model;
import jakarta.persistence.*;
@Entity
//@Table(name="users")
@Table(name="users",uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String email;
	    private String password;
	    private String fullName;
	    
	    private String role;
	    //setter,getters,default constructor,parameterized constructor

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public User()
		{
			
		}

		public User(String email, String password, String fullName, String role) {
			super();
			this.email = email;
			this.password = password;
			this.fullName = fullName;
			this.role = role;
		}
		

}