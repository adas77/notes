package pl.backend.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private LocalDateTime dateOfSignUp;
    private String ip;
    @ElementCollection
    private List<Note> notes;
    // @ManyToMany(fetch = FetchType.LAZY,
    // cascade = {
    // CascadeType.PERSIST,
    // CascadeType.MERGE
    // })
    // @JoinTable(name = "user_note",
    // joinColumns = { @JoinColumn(name = "user_id") },
    // inverseJoinColumns = { @JoinColumn(name = "note_id") })
    // private Set<Note> notes = new HashSet<>();

    // public void addNote(Note note) {
    // this.notes.add(note);
    // note.getUsers().add(this);
    // }

    // public void removeNote(Long noteId) {
    // Note note = this.notes.stream().filter(n -> n.getId() ==
    // noteId).findFirst().orElse(null);
    // if (note != null) {
    // this.notes.remove(note);
    // note.getUsers().remove(this);
    // }

    public User(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfSignUp = LocalDateTime.now();

    }

    // private void addAllRoles(Set<Role> roles) {
    // for (Role r : roles) {
    // this.addRole(r);
    // }
    // }

    // public void addRole(Role role) {
    // this.roles.add(role);
    // role.getUsers().add(this);
    // }

    // public void removeRole(long roleId) {
    // Role role = this.roles.stream().filter(r -> r.getId() ==
    // roleId).findFirst().orElse(null);
    // if (role != null) {
    // this.roles.remove(role);
    // role.getUsers().remove(this);
    // }
    // }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    // Set<Role> roles = this.getRoles();
    // List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    // for (Role role : roles) {
    // authorities.add(new SimpleGrantedAuthority(role.toString()));
    // }
    // return authorities;
    // }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(EUserRole.ROLE_USER.toString()));
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
