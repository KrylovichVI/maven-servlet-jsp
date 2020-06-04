package com.krylovichVI.pojo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Cacheable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "phone", column = @Column(nullable = true)),
            @AttributeOverride(name = "email", column = @Column(nullable = true))
    })
    private UserInfo userInfo;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "auth_id")
    private AuthUser authUser;

    public User(String firstName, String lastName, UserInfo userInfo, AuthUser authUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userInfo = userInfo;
        this.authUser = authUser;
    }
}
