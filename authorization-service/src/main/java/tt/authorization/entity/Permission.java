package tt.authorization.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "name", callSuper = true)
@NoArgsConstructor
@Table(name = "permissions")
@AttributeOverride(name = "id", column = @Column(name = "permission_id"))
public class Permission extends AbstractEntity {
    @Column(name = "permission_name")
    private String name;
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

}
