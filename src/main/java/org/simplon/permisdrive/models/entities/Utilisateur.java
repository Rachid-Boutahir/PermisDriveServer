package org.simplon.permisdrive.models.entities;

import lombok.*;

import org.jetbrains.annotations.Contract;
import org.simplon.permisdrive.models.enums.Sexe;
import org.simplon.permisdrive.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateur_id", nullable = false)
    private Long utilisateurId;

    @Column(name = "nom_utilisateur", length = 50, unique = true)
    @NotNull(message = "le nomUtilisateur ne doit pas être null !")
    private String nomUtilisateur;

    @Column(name = "email", unique = true)
    @Email(message = "L'Email doit être un Email bien formaté !")
    @NotNull(message = "le Email ne doit pas être null !")
    private String email;

    @Column(name = "mots_de_passe")
    @NotNull(message = "le motsDePasse ne doit pas être null !")
    private String motsDePasse;    @OneToMany(fetch = FetchType.EAGER)
    private Set<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return motsDePasse;
    }

    @Override
    public String getUsername() {
        return email;
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


