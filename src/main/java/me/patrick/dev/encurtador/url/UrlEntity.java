package me.patrick.dev.encurtador.url;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor 
@Entity
@Data
public class UrlEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String originalUrl;

    @NotNull private String title;

    @Column(unique = true)
    private String shortUrl;

    private long hints;


  


    private LocalDateTime created_at;

 
    private LocalDateTime updated_at;

}
