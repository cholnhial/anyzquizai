package dev.chol.anyquizai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class OpenAiImageGeneratorServiceImpl implements AIImageGeneratorService {
    private final OpenAiImageClient openaiImageClient;

    @Override
    public byte[] generateImage(String prompt, int width, int height) {
        ImageResponse response = openaiImageClient.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .withQuality("hd")
                                .withN(1)
                                .withResponseFormat("b64_json")
                                .withHeight(height)
                                .withWidth(width).build()));
        var base64Json = response.getResults().get(0).getOutput().getB64Json();
        return Base64.getDecoder().decode(base64Json);
    }
}
