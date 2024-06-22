package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @Column(name = "age")
   private int age;

   @Column(name = "user_name")
   private String username;

   @Column(name = "password")
   private String password;

   public void setUsername(String username) {
      this.username = username;
   }

   @ManyToMany(cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
   @LazyCollection(LazyCollectionOption.EXTRA)
   @Fetch(FetchMode.JOIN)
   @JoinTable(
           name = "user_roles",
           joinColumns = @JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
   private Set<Role> roles;


   public User() {}

   public User(int id, String firstName, String lastName, String email, int age, String username, String password, Set<Role> roles) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.age = age;
      this.username = username;
      this.password = password;
      this.roles = roles;
   }

   public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      if (roles.isEmpty()) { System.out.println("FUCK"); }
      return roles;
   }

   public void setPassword (String password) {
      this.password = password;
   }

   @Override
   public String getPassword() {
      return this.password;
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", roles=" + roles +
              ", username='" + username + '\'' +
              '}';
   }

   @Override
   public String getUsername() {
      return this.username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }

}
