package tt.authorization.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "name", callSuper = true)
@Entity
@Table(name = "user_roles")
@AttributeOverride(name = "id", column = @Column(name = "user_role_id"))
public class UserRole extends AbstractEntity {
    @Column(name = "user_role_name")
    private String name;
    @OneToMany(mappedBy = "userRole")
    private Set<User> userSet;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles_permissions", joinColumns = @JoinColumn(name = "user_role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public UserRole(String name) {
        this.name = name;
    }
}
