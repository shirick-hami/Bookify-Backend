package com.spring.bookifybackend.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Comparable<User>{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="IdOrGenerated")
    @GenericGenerator(name="IdOrGenerated",strategy="com.spring.bookifybackend.idgen.IdGenerator")
    @Column(name = "id", unique = true, nullable = false, precision = 20, scale = 0)
    private Long id;

    @Column(length = 100 , nullable = false,unique = true)
    private String email;

    @Column(length = 64 , nullable = false)
    private String password;

    @Column(name ="first_name" ,length = 60)
    private String firstName;

    @Column(name ="last_name" ,length = 60)
    private String lastName;

    private boolean enabled;

    @Column(nullable = false)
    private int balance = 1000;

    @ManyToMany()
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(){
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = 1000;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enabled=" + enabled +
                ", balance=" + balance +
                ", roles=" + roles +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return (int) (this.getId() - o.getId());
    }
}
