package com.mwstudio.component.rest.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER", schema = "STUDIO")
public class UserEntity {
    private Integer id;
    private String surname;
    private String firstname;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("User[");
        sb.append("id : ");
        sb.append(id);
        sb.append(", ");
        sb.append("firstname : ");
        sb.append(firstname);
        sb.append(", ");
        sb.append("surname : ");
        sb.append(surname);
        sb.append("]");
        return sb.toString();
    }

}
