package com.translateapp.app;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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
            @RequestParam(required = false) String from,
            @RequestParam String to
    ){
        return translatorService.translate(text,from,to);
    }
}
