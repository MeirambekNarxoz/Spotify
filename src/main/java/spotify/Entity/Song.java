package spotify.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Название песни
    private String artist; // Исполнитель

//    @Column(name = "audioData", columnDefinition = "BYTEA")

    private byte[] audioData; // Аудиоданные


//    @Column(name = "cover_image_music", columnDefinition = "BYTEA")
    private byte[] coverImage; // Обложка альбома

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album; // Связь с альбомом

}
