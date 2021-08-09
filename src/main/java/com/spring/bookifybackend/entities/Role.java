package com.spring.bookifybackend.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
    @GenericGenerator(name="IdOrGenerated",strategy="com.spring.bookifybackend.idgen.IdGenerator")
    @Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 200 , nullable = true)
    private String description;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return getId().equals(role.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
