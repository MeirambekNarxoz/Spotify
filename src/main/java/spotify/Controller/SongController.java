package spotify.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spotify.Entity.Song;
import spotify.Service.AlbumService;
import spotify.Service.SongService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final AlbumService albumService; // Для доступа к альбомам при создании песни
    private final Logger logger = LoggerFactory.getLogger(SongController.class);



    @GetMapping
    public String getAllSongs(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "songs/list"; // путь к вашему шаблону
    }

    @GetMapping("/{id}")
    public String getSongById(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "songs/detail"; // путь к вашему шаблону
    }

    @GetMapping("/new")
    public String createSongForm(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("albums", albumService.findAll()); // передаем альбомы для выбора
        return "songs/new"; // путь к вашему шаблону
    }

    @PostMapping("/create")
    public String createSong(@Valid @RequestParam("title") String title,
                             @RequestParam("artist") String artist,
                             @RequestParam("audioFile") MultipartFile audioFile,
                             @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
                             @RequestParam(value = "albumId", required = false) Long albumId,
                             Model model) throws IOException {

        logger.info("Title: " + title);
        logger.info("Artist: " + artist);
        logger.info("Audio file size: " + (audioFile.isEmpty() ? "No file uploaded" : audioFile.getSize() + " bytes"));
        logger.info("Cover image size: " + (coverImage.isEmpty() ? "No file uploaded" : coverImage.getSize() + " bytes"));

        byte [] audioPath = audioFile.getBytes();
        byte [] coverpath = coverImage.getBytes();


        // Сохранение песни в базе данных
        Song songSaved = songService.save(title, artist, audioPath, coverpath, albumId);

        // Проверка на ошибки
        if ( songSaved.getId() == null) {
            return "songs/new"; // Возврат на форму с ошибками
        }



        return "redirect:/songs"; // Перенаправление после успешного сохранения
    }



    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs"; // перенаправление после удаления
    }
}
