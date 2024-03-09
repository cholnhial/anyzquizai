package dev.chol.anyquizai.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Controller
@RequestMapping("/api/image")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final OpenAiImageClient openaiImageClient;

    private final RestTemplate restTemplate;

    @Value("${spring.ai.openai.api-key}")
    private String openApiKey;

    @GetMapping
    public ResponseEntity<byte[]> generateImage(@RequestParam String topic) {
        ImageResponse response = openaiImageClient.call(
                new ImagePrompt(topic,
                        OpenAiImageOptions.builder()
                                .withQuality("hd")
                                .withN(1)
                                .withResponseFormat("b64_json")
                                .withHeight(1024)
                                .withWidth(1024).build()));
        var base64Json = response.getResults().get(0).getOutput().getB64Json();
        var bytes = Base64.getDecoder().decode(base64Json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
