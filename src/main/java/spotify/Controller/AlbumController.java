package spotify.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spotify.Entity.Album;
import spotify.Entity.Song;
import spotify.Service.AlbumService;
import spotify.Service.SongService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final SongService songService;

    @GetMapping
    public String getAllAlbums(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        return "albums/list"; // путь к вашему шаблону
    }

    @GetMapping("/{id}")
    public String getAlbumById(@PathVariable Long id, Model model) {
        Album album = albumService.findById(id);
        model.addAttribute("album", album);
        model.addAttribute("songs", album.getSongs());
        return "albums/detail"; // путь к вашему шаблону
    }


    @GetMapping("/new")
    public String createAlbumForm(Model model) {
        model.addAttribute("album", new Album());
        return "albums/new"; // путь к вашему шаблону
    }




    @PostMapping("/create")
    public String createAlbum(@RequestParam String title,
                              @RequestParam(value = "coverPic") MultipartFile coverImage)
            throws IOException {

        byte[] coverPath = coverImage.getBytes();

        albumService.save(title, coverPath); // Передаем 'title' вместо 'name'
        return "redirect:/albums"; // Перенаправление после создания
    }


    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.delete(id);
        return "redirect:/albums"; // перенаправление после удаления
    }


}
