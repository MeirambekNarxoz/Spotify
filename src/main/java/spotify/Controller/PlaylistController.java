package spotify.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spotify.Entity.Playlist;
import spotify.Service.PlaylistService;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public String getAllPlaylists(Model model) {
        List<Playlist> playlists = playlistService.findAll();
        model.addAttribute("playlists", playlists);
        return "playlists/list"; // путь к вашему шаблону
    }

    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable Long id, Model model) {
        Playlist playlist = playlistService.findById(id);
        model.addAttribute("playlist", playlist);
        return "playlists/detail"; // путь к вашему шаблону
    }

    @GetMapping("/new")
    public String createPlaylistForm(Model model) {
        model.addAttribute("playlist", new Playlist());
        return "playlists/new"; // путь к вашему шаблону
    }

    @PostMapping
    public String createPlaylist(@ModelAttribute Playlist playlist) {
        playlistService.save(playlist);
        return "redirect:/playlists"; // перенаправление после создания
    }

    @GetMapping("/delete/{id}")
    public String deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
        return "redirect:/playlists"; // перенаправление после удаления
    }
}
