package kageflo.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private String startDate;

    @Nullable
    @Column(name = "end_date")
    private String endDate;

    @Nullable
    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnail;

    @Column(name = "type")
    private String type;

    @Nullable
    @Column(name = "episode_count")
    private Integer episodes;

    @Column(name = "status")
    private String status;

    @Column(name = "source")
    private String source;

    @Column(name = "studio")
    private String studio;
}
