package me.patrick.dev.encurtador.url;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.patrick.dev.encurtador.exceptions.UrlNotFoundException;

@Service
@RequiredArgsConstructor
@Data

public class UrlService {

    @Data
    @RequiredArgsConstructor
    static class ShortenUrl {
        private final String shortUrl;
        private final String originalUrl;
    }

    @Data
    static class ShortenDTO extends ShortenUrl {

        private String title;
        private LocalDateTime created_at;
        private long hints;

        public ShortenDTO(String shortUrl, String originalUrl, String title, LocalDateTime created_at, long hints) {
            super(shortUrl, originalUrl);
            this.title = title;
            this.created_at = created_at;
            this.hints = hints;
        }

    }

    private final UrlRepository urlRepository;

    public String getUrl(String shortUrl) {
        UrlEntity entity = urlRepository.findByShortUrl(shortUrl).orElseThrow(UrlNotFoundException::new);
        entity.setHints(entity.getHints() + 1);
        entity.setUpdated_at(LocalDateTime.now());
        urlRepository.save(entity);
        return entity.getOriginalUrl();
    }

    public ShortenDTO getUrlData(String shortUrl) {
        UrlEntity entity = urlRepository.findByShortUrl(shortUrl).orElseThrow(UrlNotFoundException::new);
        return new ShortenDTO(entity.getShortUrl(), entity.getOriginalUrl(), entity.getTitle(), entity.getCreated_at(), entity.getHints());
    }

    public UrlEntity saveUrl(String originalUrl) {

        String title = getUrlTitle(originalUrl);

        UrlEntity urlEntity = UrlEntity.builder().originalUrl(originalUrl).shortUrl(generateShortUrl()).created_at(
                LocalDateTime.now()).updated_at(LocalDateTime.now()).hints(0).title(title).build();
        return urlRepository.save(urlEntity);

    }

    private String generateShortUrl() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }

        urlRepository.findByShortUrl(sb.toString()).ifPresent(url -> {
            generateShortUrl();
            System.out.println(sb.toString() + " já existe, criando novamente...");
            return;
        });

        return sb.toString();

    }

    public String getUrlTitle(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String titulo = doc.title();
            return titulo;
        } catch (IOException e) {
            e.printStackTrace();
            return url;
        }
    }

}
