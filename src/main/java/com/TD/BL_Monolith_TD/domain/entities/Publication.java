package com.TD.BL_Monolith_TD.domain.entities;

import com.TD.BL_Monolith_TD.util.enums.StatusPublication;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "publication")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String title;
    @Column(length = 1000, nullable = true)
    private String description;
    @Column(length = 300, nullable = false)
    private String tags;
    @Column(length = 500, nullable = false)
    private String url_img;
    @Column(nullable = false)
    private LocalDateTime publication_date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPublication enum_status;



    // @ManyToOne
    // Unnecessary to return users data from publication
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
