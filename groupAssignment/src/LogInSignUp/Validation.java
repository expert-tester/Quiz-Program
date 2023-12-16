/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogInSignUp;

/**
 *
 * @author Phoon Chun On
 */
public class Validation {
    // validate first name
  public boolean validateInteger (String onlyNumbers) {
    return onlyNumbers.matches("[\\d]+");
  }

  // validate last name
  public static boolean validatePhoneNumber(String phoneNumber) {
    return phoneNumber.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
  }

  // validate address
  public static boolean validateEmail(String email) {
    return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,3}$");
  }
  
  // validate password (Minimum eight characters, at least one letter, one number and one special character)
  public static boolean validatePassword(String password) {
    return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
  }

}
