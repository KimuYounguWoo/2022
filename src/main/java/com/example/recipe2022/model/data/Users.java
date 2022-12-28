package com.example.recipe2022.model.data;

import com.example.recipe2022.model.enumer.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_member")
public class Users extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "email_addr", nullable = false, unique = true    )
    private String email;

    @Column(name = "login_id", unique = true)
    private String uid;

    @Column(name = "pic_file_id")
    private String picture;

    @Column(name = "login_passwd")
    private String password;

    @Column(name = "BDAY")
    private LocalDateTime date;

    @Column(name = "MOBILE_TEL_NUM")
    private String nums;

    @Column(name = "GENDER_CODE")
    private String gender;

    @Column(name = "SOCIAL_PROVIDER")
    private String social;

    @Column(name = "USER_DIV_CD")
    private Role role;

    @Column(name = "PASSWD_DATE")
    private LocalDateTime passwdDate;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLogin;

    @Column(name = "PASSWD_INPUT_FAIL_COUNT")
    private int passwdFailCount;

    @Column(name = "USE_YN")
    private boolean blocked;
    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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

    @Builder
    public Users (String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Users update(String name, String picture)
    {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

}