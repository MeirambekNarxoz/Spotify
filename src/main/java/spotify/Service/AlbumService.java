package spotify.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spotify.Entity.Album;
import spotify.Entity.Song;
import spotify.Repository.AlbumRepository;
import spotify.Repository.SongRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    public Album findById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public boolean deleteSong (Long albumId, Long songId){
        try{ //find album
            Album album = albumRepository.findById(albumId).orElse(new Album());

            //delete song from list of Song
            List<Song> songs = album.getSongs();
            songs.remove(songRepository.findById(songId));

            //save with updated list of Song
            album.setSongs(songs);
            albumRepository.save(album);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Album save(String name, byte[]  coverImage) throws IOException {
        Album album = new Album();
        album.setTitle(name);
        album.setCoverImage(coverImage);
        return albumRepository.save(album);
    }



    public void delete(Long id) {
        albumRepository.deleteById(id);
    }
}
