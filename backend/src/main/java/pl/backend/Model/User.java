package pl.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "userx", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    // @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    // @Size(max = 20)
    private String username;
    @NotBlank
    // @Size(max = 120)
    private String password;
    private LocalDateTime dateOfSignUp;
    // @ManyToMany(fetch = FetchType.LAZY)
    // @ManyToMany(mappedBy = "authors", cascade = {
    // CascadeType.PERSIST,
    // CascadeType.MERGE
    // })
    // @JoinTable(name = "userx_roles", joinColumns = @JoinColumn(name =
    // "userx_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    // @ManyToMany
    // @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST })
    // @JoinColumn(name = "role_id")
    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<Role> roles = new HashSet<>();

    public User(String email, String username, String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;

        this.dateOfSignUp = LocalDateTime.now();
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // return Collections.singleton(new SimpleGrantedAuthority(roles));
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = this.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // TODO: userDetails
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
