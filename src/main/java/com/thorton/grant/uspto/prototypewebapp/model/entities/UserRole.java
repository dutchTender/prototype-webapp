package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long id;

    @Column(name = "role_name")
    private String role_name;


    @ManyToMany(mappedBy = "user_roles")
    private Set<UserCredentials> userCredentials ;


    @ManyToMany
    @JoinTable(
            name = "role_prvileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Set<RolePrivilege> rolePrivileges;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(role_name, userRole.role_name) &&
                Objects.equals(userCredentials, userRole.userCredentials) &&
                Objects.equals(rolePrivileges, userRole.rolePrivileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_name, userCredentials, rolePrivileges);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", userCredentials=" + userCredentials +
                ", rolePrivileges=" + rolePrivileges +
                '}';
    }
}
