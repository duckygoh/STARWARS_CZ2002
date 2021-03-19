package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Represents a user that can access the STARS Application
 * A user can be either an admin or student in the application
 * @author Lim Bing Hong, Jasper
 * @version 1.0
 * @since 2020/10/08
 */
public class User implements Serializable {
	
    private static final long serialVersionUID = 4L;

	/**
	 * the login ID of the user
	 */
	private String loginID;
	/**
	 * the password of the user
	 */
	private String loginPW;

	/**
	 * Retrieve the login ID of this user
	 * @return this user's login ID
	 */
	public String getLoginID() {
		return loginID;
	}
	/**
	 * Modify the login ID of this user
	 * @param loginID The new login ID of this user
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * Retrieve the login password of this user
	 * @return this user's password
	 */
	public String getLoginPW() {
		return loginPW;
	}

	/**
	 * Modify the login password of this user
	 * @param loginPW The new login password of this user
	 */
	public void setLoginPW(String loginPW) {
		this.loginPW = loginPW;
	}

	/**
	 * Constructor to create a new user without any attributes required
	 */
	public User() {

	}

	/**
	 * Constructor to create a new user given the login ID and login password
	 * @param loginID This user's login ID
	 * @param loginPW This user's login password
	 */
	public User(String loginID, String loginPW) {
		this.loginID = loginID;
		this.loginPW = loginPW;
	}

	/**
	 * To validate the login of the user given the login id and password
	 * @param username User's login ID
	 * @param password User's login password
	 * @return true or false depending if the login ID and password exists
	 */
	public boolean validateLogin(String username, String password){
		String userPW = hashString(password);
		if (userPW.equals(this.loginPW)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To encrypt the given password string
	 * @param password unhashed password
	 * @return hashed password or null if the password hashing fail
	 */
	public static String hashString(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// String text = "Text to hash, cryptographically.";

			// Change this to UTF-16 if needed
			md.update(password.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();

			String hex = String.format("%064x", new BigInteger(1, digest));
			return hex;
		}
		catch(NoSuchAlgorithmException noSuchAlgorithmException){
			System.out.println("Error hashing the string!");
			return null;
		}
	}

}
