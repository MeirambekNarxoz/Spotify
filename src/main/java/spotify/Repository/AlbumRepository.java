package spotify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spotify.Entity.Album;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
