package spotify.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spotify.Entity.Playlist;
import spotify.Repository.PlaylistRepository;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Playlist findById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }
}
