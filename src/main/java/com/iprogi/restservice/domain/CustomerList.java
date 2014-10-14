package com.iprogi.restservice.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author: Dmitriy E. Nosov <br>
 * @date: 26.03.14, 12:13 <br>
 * @description: <br>
 */
@XmlRootElement(name = "listing")
public class CustomerList {
    private List<Customer> books;

    public CustomerList() {
    }

    public CustomerList(List<Customer> books) {
        this.books = books;
    }

    @XmlElement(name = "customers")
    public List<Customer> getBooks() {
        return books;
    }
}
