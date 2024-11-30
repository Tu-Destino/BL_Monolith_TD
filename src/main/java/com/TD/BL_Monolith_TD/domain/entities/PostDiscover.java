package com.TD.BL_Monolith_TD.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "postDiscover")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDiscover implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id",referencedColumnName = "id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    public interface PostDiscoverRepository extends JpaRepository<PostDiscover, String> {
        @EntityGraph(attributePaths = {"user", "place"})
        List<PostDiscover> findAllByPlaceId(String placeId); }
}

