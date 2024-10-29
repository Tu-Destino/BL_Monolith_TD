package com.TD.BL_Monolith_TD.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "postDiscover")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiscover {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 250, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String description;
    @Column(length = 300, nullable = false)
    private String tags;
    @Column(length = 500, nullable = false)
    private String urlImg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id",referencedColumnName = "id")
    private Place place;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;



}

