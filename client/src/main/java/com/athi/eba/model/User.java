package com.athi.eba.model;
 
import lombok.Data;

import java.io.Serializable;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
@Data
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @XmlAttribute(name = "id")
    private int id;
 
    @XmlAttribute(name="uri")
    private String uri;
 
    @XmlElement(name = "firstName")
    private String firstName;
 
    @XmlElement(name = "lastName")
    private String lastName;
 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}