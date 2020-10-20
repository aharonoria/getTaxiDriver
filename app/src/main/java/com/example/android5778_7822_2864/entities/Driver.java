package com.example.android5778_7822_2864.entities;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class Driver {
    String firstName;
    String password;



    String lastName;
    Long id;
    String phone;
    String email;
    Long creditCard;

    /**
     * constractor
     * @param firstName
     * @param lastName
     * @param id
     * @param phone
     * @param email
     * @param creditCard
     */


    public Driver(String firstName, String password, String lastName,Long id, String phone, String email, Long creditCard) {
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.creditCard = creditCard;
    }



    /**
     *defaulte constractor
     */
    public Driver() {
        this.firstName = "";
        this.lastName = "";
        this.password="";

        this.id = new Long(0);
        this.phone = "0";
        this.email = "";
        this.creditCard = new Long(0);

    }

    /**
     * getter first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter Id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter Id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setter phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter credit card
     * @return credit card
     */
    public Long getCreditCard() {
        return creditCard;
    }

    /**
     * getter
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * setter credit card
     * @param creditCard
     */

    public void setCreditCard(Long creditCard) {
        this.creditCard = creditCard;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(firstName, driver.firstName) &&
                Objects.equals(password, driver.password) &&
                Objects.equals(lastName, driver.lastName) &&
                Objects.equals(id, driver.id) &&
                Objects.equals(phone, driver.phone) &&
                Objects.equals(email, driver.email) &&
                Objects.equals(creditCard, driver.creditCard);
    }


    /**
     * to string
     * @return string
     */
    @Override
    public String toString() {
        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
