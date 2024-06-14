package me.patrick.dev.encurtador.url;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.PostUpdate;

public interface UrlRepository extends JpaRepository<UrlEntity, Long>{

    Optional<UrlEntity> findByShortUrl(String shortUrl);
    
    
    
}
