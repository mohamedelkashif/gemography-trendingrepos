package com.gemography.trending.services;

import com.gemography.trending.domainobjects.LanguagesInfo;

import java.io.IOException;

public interface LanguageService {
    LanguagesInfo getTrendingLanguages() throws IOException;
}
