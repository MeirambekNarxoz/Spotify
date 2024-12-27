package spotify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spotify.Entity.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
