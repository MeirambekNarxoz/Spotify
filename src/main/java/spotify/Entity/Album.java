package spotify.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long album_id;

    private String title; // Название альбома

    @Column(name = "cover_image", columnDefinition = "BYTEA")
    private byte[] coverImage; // Обложка альбома

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person user; // Владелец альбома

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs; // Список песен
}

