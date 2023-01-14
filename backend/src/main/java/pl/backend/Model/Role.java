//package pl.backend.Model;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Table(name = "rolex")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated
//    private EUserRole role;
//    // @ManyToMany(fetch = FetchType.LAZY, cascade = {
//    //         CascadeType.PERSIST,
//    //         CascadeType.MERGE
//    // }, mappedBy = "roles")
//    // @JsonIgnore
//    // private Set<User> users = new HashSet<>();
//
//    public Role(EUserRole role) {
//        this.role = role;
//    }
//
//}
