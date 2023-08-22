package kageflo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anime_genres")
@IdClass(AnimeGenre.AnimeGenreID.class)
public class AnimeGenre {
    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_anime", referencedColumnName = "id")
    @JsonIgnoreProperties({"startDate","endDate","description","type","episodes","status","source","studio"})
    private Anime animeID;

    @Id
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_anime", referencedColumnName = "id")
    private Genre genreID;

    public static class AnimeGenreID implements Serializable{
        private int animeID;

        private int genreID;

        public AnimeGenreID() {
        }

        public AnimeGenreID(int animeID, int genreID) {
            this.animeID = animeID;
            this.genreID = genreID;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AnimeGenreID that = (AnimeGenreID) o;
            return animeID == that.animeID && genreID == that.genreID;
        }

        @Override
        public int hashCode() {
            return Objects.hash(animeID, genreID);
        }
    }
}
