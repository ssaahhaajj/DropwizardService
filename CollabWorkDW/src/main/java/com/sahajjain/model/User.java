package com.sahajjain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;
import java.util.Objects;
import java.util.Set;

public class User implements Principal {
    private String email;
    private String password;
    private  Set<String> roles;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

    @JsonProperty
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    @JsonProperty
    public Set<String> getRoles() {
        return roles;
    }

    @Override
    @JsonProperty
    public String getName() {
        return email;
    }
}