package com.gemography.trending.controllers;

import com.gemography.trending.domainobjects.LanguagesInfo;
import com.gemography.trending.services.LanguageService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/")
    @ApiImplicitParam(name = "Getting trending repositories based on languages")
    public LanguagesInfo getLanguages() throws IOException {
        return languageService.getTrendingLanguages();
    }


}
