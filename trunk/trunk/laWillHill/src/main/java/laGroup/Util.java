package laGroup;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.Test;

/*
 * These class contain many reusable Utility methods 
 */
public class Util {
	
	/*
	 * method to create x random Alphabets
	 */
	
	public String xNumberOfAlphabets(int x){
		String Alphabets = RandomStringUtils.randomAlphabetic(x);
		//System.out.println(Alphabets);
		return Alphabets;
	}
	
	/*
	 * method to create x random Numbers
	 */
	public String xNumberOfNumeric(int x){
		String RandomNumbers = RandomStringUtils.randomNumeric(x);
		//System.out.println(RandomNumbers);
		return RandomNumbers;
	}
	
	/*
	 * method to create mixture of random alphabets and numbers
	 */	
    public String randomName(int x) {
        String s = "";
           String randomString = "abcdefghijklmno";
           Random randomGenerator = new Random();
           for (int i = 0; i < 5; i++) {
        	   s+= randomGenerator.nextInt(x);
                   //s += randomGenerator.nextInt(9);
                   s += randomString.charAt(randomGenerator.nextInt(randomString.length()));
           }
           //System.out.println(s);
           return s;
	}
	

	/*
	 * Another method to create mixture of random alphabets and numbers
	 */	
	public String randomLettersAndNumbers(int x) {
	    String s = "";
		String randomString = "abcdefghijklmno";
		Random randomGenerator = new Random();
		for (int i = 0; i < x; i++) {
			s += randomGenerator.nextInt(9);
			s += randomString.charAt(randomGenerator.nextInt(randomString.length()));
		}
		//System.out.println(s);
		return s;
	}
	
/*
	public void Dothis(){
		xNumberOfAlphabets(52);
		xNumberOfNumeric(40);
		randomName(15);
		randomLettersAndNumbers(2); //Note this method and the one above it "randomName" do thesame thing 	
		}
		*/
	
	public final String firstname_valid(){
		String firstname = xNumberOfAlphabets(6);
		return firstname;
	}
	
	public final String lastname_valid(){
		String lastname = xNumberOfAlphabets(6);
		return lastname;
	}
	
	public String region_valid(){
		String region = xNumberOfAlphabets(6);
		return region;
	}
	
	public String city_valid(){
		String city = xNumberOfAlphabets(6);
		return city;
	}
	
	public String emailAddress_valid(){
		String email_address = (xNumberOfAlphabets(6) + "@" + xNumberOfAlphabets(3) + "." + "com");
		return email_address;
	}
	
	public String postcode_valid(){
		String Postcode = randomLettersAndNumbers(4);
		return Postcode;
	}
	
	public String addressLine1_valid(){
		String Address1 = xNumberOfNumeric(3);
		return Address1;
	}
	
	public String addressLine2_valid(){
		String Address2 = xNumberOfAlphabets(10);
		return Address2;
	}
	
	public String username_valid(){
		String username = randomLettersAndNumbers(6);
		return username;
	}
	
	public String password_valid(){
		String password_valid = "ILikeToread204";
		return password_valid;
	}
	
	
	public String mobileNumber(){
		String mobile = xNumberOfNumeric(8);
		return mobile;
	}
	
	/*
	 * method to create random letters and numbers
	 */
	
	
	


	/*
	 * create random numbers
	 */
	
	
	
	
}
