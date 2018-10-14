package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;


    @ManyToMany(mappedBy = "userRoles")
    private Set<UserCredentials> userCredentials ;


    @ManyToMany
    @JoinTable(
            name = "role_prvileges",
            joinColumns = @JoinColumn(name = "user_role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Set<RolePrivilege> rolePrivileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String role_name) {
        this.roleName = role_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(roleName, userRole.roleName) &&
                Objects.equals(userCredentials, userRole.userCredentials) &&
                Objects.equals(rolePrivileges, userRole.rolePrivileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, userCredentials, rolePrivileges);
    }

    public Set<UserCredentials> getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(Set<UserCredentials> userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Set<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", userCredentials=" + userCredentials +
                ", rolePrivileges=" + rolePrivileges +
                '}';
    }
}
