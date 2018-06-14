package pl.coderslab.carrental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{1,45})", message="Nazwa użytkownika powinna składać się z liter lub cyfr i mieć od 2 do 30 znaków")
	private String userName;
	
	@NotBlank
	@Size(min=2, max=30, message="Imię powinno mieć od 2 do 30 znaków")
	private String firstName;
	
	@NotBlank
	@Size(min=2, max=30, message="Nazwisko powinno mieć od 2 do 30 znaków")
	private String lastName;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})", message="Hasło musi zawierać przyajmniej jedną cyfrę od 0-9, przynajmniej jedną małą literę, przynajmniej jedną dużą literę, przynajmniej jeden symbol specjalny (@#$%) i posiadać od 8 do 20 znaków.")
	private String password;
	
	
	
	@NotNull
	private boolean enabled;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	/*
	 * isPasswordCorrect
	 */
	public boolean isPasswordCorrect(String pwd) {
		return BCrypt.checkpw(pwd, this.password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, userName=%s, firstName=%s, lastName=%s, email=%s, enabled=%s]",
			id, userName, firstName, lastName, email, enabled);
	}



}
