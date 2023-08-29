package kageflo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @JsonIgnoreProperties({"username","password","email","dob","is_admin"})
    private User userID;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_anime", referencedColumnName = "id")
    @JsonIgnoreProperties({"startDate","endDate","description","type","episodes","status","source","studio"})
    private Anime animeID;

    private String comment;

    private LocalDateTime timestamp;
}
