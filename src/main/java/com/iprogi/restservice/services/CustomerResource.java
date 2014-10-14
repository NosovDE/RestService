package com.iprogi.restservice.services;

import com.iprogi.restservice.domain.Customer;
import com.iprogi.restservice.domain.CustomerList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Dmitriy E. Nosov <br>
 * @date: 25.03.14, 17:36 <br>
 * @description: <br>
 */
@Path("/customers")
public class CustomerResource {
    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
    private AtomicInteger idCounter = new AtomicInteger();

    public CustomerResource() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCustomer(InputStream is) {
        Customer customer = readCustomer(is);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Created customer " + customer.getId());
        return Response.created(URI.create("/customers/" + customer.getId())).build();

    }

    @GET
//    @BadgerFish
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customer getCustomer(@PathParam("id") int id) {
        final Customer customer = new Customer();
        customer.setFirstName("qqq");
        customer.setCity("111");
        customer.setCountry("sdfdsf");
        customer.setId(1);
        return customer;
    }

    @GET
    @Path("books")
    @Produces("application/json")
    //@Mapped // mapped is the default format
    public CustomerList getBooksMapped() {
        List<Customer> customerList = new ArrayList<Customer>();

        customerList.add(new Customer("11", "22", "33"));
        customerList.add(new Customer("dd", "ff", "gg"));

        return new CustomerList(customerList);
    }

    @GET
    @Path("books2")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    //@Mapped // mapped is the default format
    public Response getBooksMapped2() {
        final Customer customer = new Customer("11", "22", "33");
        return Response.status(200).entity(customer).build();
    }


    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateCustomer(@PathParam("id") int id, InputStream is) {
        Customer update = readCustomer(is);
        Customer current = customerDB.get(id);
        if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);

        current.setFirstName(update.getFirstName());
        current.setLastName(update.getLastName());
        current.setStreet(update.getStreet());
        current.setState(update.getState());
        current.setZip(update.getZip());
        current.setCountry(update.getCountry());
    }


    protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
        PrintStream writer = new PrintStream(os);
        writer.println("<customer id=\"" + cust.getId() + "\">");
        writer.println("   <first-name>" + cust.getFirstName() + "</first-name>");
        writer.println("   <last-name>" + cust.getLastName() + "</last-name>");
        writer.println("   <street>" + cust.getStreet() + "</street>");
        writer.println("   <city>" + cust.getCity() + "</city>");
        writer.println("   <state>" + cust.getState() + "</state>");
        writer.println("   <zip>" + cust.getZip() + "</zip>");
        writer.println("   <country>" + cust.getCountry() + "</country>");
        writer.println("</customer>");
    }

    protected Customer readCustomer(InputStream is) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            Element root = doc.getDocumentElement();
            Customer cust = new Customer();
            if (root.getAttribute("id") != null && !root.getAttribute("id").trim().equals(""))
                cust.setId(Integer.valueOf(root.getAttribute("id")));
            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                if (element.getTagName().equals("first-name")) {
                    cust.setFirstName(element.getTextContent());
                } else if (element.getTagName().equals("last-name")) {
                    cust.setLastName(element.getTextContent());
                } else if (element.getTagName().equals("street")) {
                    cust.setStreet(element.getTextContent());
                } else if (element.getTagName().equals("city")) {
                    cust.setCity(element.getTextContent());
                } else if (element.getTagName().equals("state")) {
                    cust.setState(element.getTextContent());
                } else if (element.getTagName().equals("zip")) {
                    cust.setZip(element.getTextContent());
                } else if (element.getTagName().equals("country")) {
                    cust.setCountry(element.getTextContent());
                }
            }
            return cust;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }
    }
}
