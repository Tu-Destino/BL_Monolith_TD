package com.TD.BL_Monolith_TD.domain.entities;

import com.TD.BL_Monolith_TD.util.enums.Enum_Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "place")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Enum_Type enum_type;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false,length = 2000)
    private String details;
    @Column(length = 100,nullable = false)
    private String price;
    @Column(length = 100)
    private String schedule;
    @Column(length = 100,nullable = false)
    private String address;
    @Column(length = 100)
    private String link_address;
    @Column(length = 1000)
    private String vr;
    @Column(length = 120)
    private String web;
    @Column(length = 100)
    private String phone;
    @Column(length = 100,nullable = false)
    private double rate;
    @Column(nullable = false ,length = 2000)
    private String information;
    @Column(length = 1000)
    private String btn_url;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL,orphanRemoval = false,fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<PostDiscover> postDiscovers;

    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL,orphanRemoval = false,fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> comment;

}
