package com.example.demo.registration.token;

import com.example.demo.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Token {

    @Id
    @SequenceGenerator(name="token_sequence",sequenceName ="token_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "token_sequence")
    private Long id;

    public Token(String token, LocalDateTime created, LocalDateTime expires, LocalDateTime confirmed, AppUser appUser) {
        this.token = token;
        this.created = created;
        this.expires = expires;
        this.confirmed = confirmed;
        this.appUser = appUser;
    }

    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime expires;
    @Column(nullable = true)
    private LocalDateTime confirmed;
    @ManyToOne
    @JoinColumn(nullable = false,name = "app_user_id")
    private AppUser appUser;
}
