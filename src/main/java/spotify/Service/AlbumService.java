package spotify.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spotify.Entity.Album;
import spotify.Repository.AlbumRepository;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public Album findById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}
