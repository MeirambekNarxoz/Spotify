package spotify.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spotify.Entity.Album;
import spotify.Service.AlbumService;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

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
        return "albums/detail"; // путь к вашему шаблону
    }

    @GetMapping("/new")
    public String createAlbumForm(Model model) {
        model.addAttribute("album", new Album());
        return "albums/new"; // путь к вашему шаблону
    }

    @PostMapping
    public String createAlbum(@ModelAttribute Album album) {
        albumService.save(album);
        return "redirect:/albums"; // перенаправление после создания
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {
        albumService.delete(id);
        return "redirect:/albums"; // перенаправление после удаления
    }
}
