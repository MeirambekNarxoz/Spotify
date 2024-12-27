package spotify.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spotify.Entity.Song;
import spotify.Repository.SongRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<Song> findAll(){
        return songRepository.findAll();
    }

    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}
