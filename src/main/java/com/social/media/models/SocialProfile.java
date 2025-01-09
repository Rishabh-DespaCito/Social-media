package com.social.media.models;

import jakarta.persistence.*;

@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne//mappedBy willmaking Bi-directional relationship between entities.
    @JoinColumn(name = "social_user")
    private SocialUser user;
}
