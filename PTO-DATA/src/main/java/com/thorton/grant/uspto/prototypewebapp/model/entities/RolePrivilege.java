package com.thorton.grant.uspto.prototypewebapp.model.entities;



import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role_privileges")
public class RolePrivilege  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "rolePrivileges")
    private Set<UserRole> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePrivilege that = (RolePrivilege) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(userRoles, that.userRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userRoles);
    }

    @Override
    public String toString() {
        return "RolePrivilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
