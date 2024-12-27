package spotify.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SongController {
    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService; // Для доступа к альбомам при создании песни

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

    @PostMapping
    public String createSong(@ModelAttribute Song song,
                             @RequestParam("audioFile") MultipartFile audioFile,
                             @RequestParam("coverImage") MultipartFile coverImage) throws IOException {
        if (!audioFile.isEmpty()) {
            song.setAudioData(audioFile.getBytes()); // Устанавливаем аудиоданные
        }
        if (!coverImage.isEmpty()) {
            song.setCoverImage(coverImage.getBytes()); // Устанавливаем изображение обложки
        }
        songService.save(song);
        return "redirect:/songs"; // перенаправление после создания
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs"; // перенаправление после удаления
    }
}
