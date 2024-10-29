package com.TD.BL_Monolith_TD.domain.entities;

import com.TD.BL_Monolith_TD.util.enums.Iso_639_1;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "cookies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cookies {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
   @Column(length = 200)
    private String label_counter;
   @Column(length = 2)
   private Iso_639_1 language;
   @Column(length = 2000)
   private String favorites;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private User user;
}
