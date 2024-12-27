package spotify.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.Entity.Song;
import spotify.Repository.SongRepository;

@RestController
@RequiredArgsConstructor
public class MediaController {

    private final SongRepository songRepository;


    @RequestMapping("/audio/{id}")
    public ResponseEntity<ByteArrayResource> getAudio(@PathVariable Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Песня не найдена"));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // или MediaType.AUDIO_MPEG
                .body(new ByteArrayResource(song.getAudioData()));
    }

    @RequestMapping("/cover/{id}")
    public ResponseEntity<ByteArrayResource> getCover(@PathVariable Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException("Песня не найдена"));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // или другой тип изображения
                .body(new ByteArrayResource(song.getCoverImage()));
    }
}
