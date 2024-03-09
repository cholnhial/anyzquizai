package dev.chol.anyquizai.web.api;

import dev.chol.anyquizai.service.AIImageGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/image")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
  private final AIImageGeneratorService aiImageGeneratorService;

    @GetMapping
    public ResponseEntity<byte[]> generateImage(@RequestParam String topic) {

        var bytes = aiImageGeneratorService.generateImage(topic, 1024, 1024);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
