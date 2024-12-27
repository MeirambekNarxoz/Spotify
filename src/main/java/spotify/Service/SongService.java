package spotify.Service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import spotify.Entity.Album;
import spotify.Entity.Song;
import spotify.Repository.SongRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final Logger logger = LoggerFactory.getLogger(SongService.class);
    private final AlbumService albumService;


    public List<Song> findAll(){
        return songRepository.findAll();
    }

    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    public Song save(String title, String artist,
                     byte[] audioFile, byte[] coverImage, Long albumId
                    ) {
        Song song = new Song();

        if (albumId != null) {
            Album album = albumService.findById(albumId); // Предполагается, что у вас есть AlbumService
            song.setAlbum(album);
        }

        song.setTitle(title);
        song.setArtist(artist);
        song.setAudioData(audioFile);
        song.setCoverImage(coverImage);

        logger.info("Audio file size: " + audioFile + " bytes");
        logger.info("Cover image size: " + coverImage + " bytes");


        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}
