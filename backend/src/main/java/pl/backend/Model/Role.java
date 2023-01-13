package pl.backend.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rolex")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private EUserRole role;
    // @ManyToMany(fetch = FetchType.LAZY, cascade = {
    //         CascadeType.PERSIST,
    //         CascadeType.MERGE
    // }, mappedBy = "roles")
    // @JsonIgnore
    // private Set<User> users = new HashSet<>();

    public Role(EUserRole role) {
        this.role = role;
    }

}
