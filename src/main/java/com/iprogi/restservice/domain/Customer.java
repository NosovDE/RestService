package com.iprogi.restservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author: Dmitriy E. Nosov <br>
 * @date: 25.03.14, 17:34 <br>
 * @description: <br>
 */
@XmlRootElement
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private String zip;

    private String country;


    public Customer() {
    }

    public Customer(String firstName, String lastName, String street) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
    }

    @XmlElement
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    @XmlElement
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    @XmlElement
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    @XmlElement
    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }
    @XmlElement
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
    @XmlElement
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }
    @XmlElement
    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }
    @XmlElement
    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zip='").append(zip).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
