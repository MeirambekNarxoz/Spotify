package spotify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spotify.Entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
