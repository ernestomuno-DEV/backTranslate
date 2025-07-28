package com.translateapp.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/translate")
public class TranslatorController {
    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping
    public String translate(
            @RequestParam String text,
            @RequestParam String from,
            @RequestParam String to
    ){
        return translatorService.translate(text,from,to);
    }
}
