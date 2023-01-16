package pl.backend.Model;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Entity
@Table(name = "_user", uniqueConstraints = {
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
    @Enumerated(EnumType.STRING)
    private EUserRole role;
    private Integer failedAttempt;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Date lockTime;
    private LocalDateTime dateOfSignUp;
    private String ip;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Note> notes = new HashSet<>();

    protected void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public void addToNotes(Note note) {
        note.setUser(this);
        this.notes.add(note);
    }

    public User(String email, String username, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateOfSignUp = LocalDateTime.now();

    }

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
