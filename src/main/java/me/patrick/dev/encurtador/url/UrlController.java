package me.patrick.dev.encurtador.url;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

    


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UrlController {


    public final UrlService urlService;


    @GetMapping()
    public ResponseEntity<?> getOriginalUrl(@RequestParam(value = "url") String shortUrl) {

        if(shortUrl == null || shortUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("URL não informada");
        }

        return  ResponseEntity.ok(new UrlService.ShortenUrl(shortUrl, urlService.getUrl(shortUrl)));
    }

    @PostMapping("/hints")
    public ResponseEntity<?> postMethodName(@RequestBody String shortUrl) {
        return ResponseEntity.ok(urlService.getUrlData(shortUrl));
    }
    

    
    @PostMapping("/short")
    public ResponseEntity<?> createShortUrl(@RequestBody String url) {



        if(url == null || url.isEmpty()){
            return ResponseEntity.badRequest().body("URL não informada");
        }
        


        UrlEntity savedUrl = urlService.saveUrl(url);

        if(savedUrl == null) {
            return ResponseEntity.internalServerError().body("Ocorreu um erro ao criar a url");
        }

     

        return ResponseEntity.status(HttpStatus.CREATED).body(new UrlService.ShortenUrl(savedUrl.getShortUrl(), url));
    }
    

}
