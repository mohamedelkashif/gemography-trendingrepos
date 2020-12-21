package com.gemography.trending.services;

import com.gemography.trending.domainobjects.Items;
import com.gemography.trending.domainobjects.Languages;
import com.gemography.trending.domainobjects.LanguagesInfo;

import java.io.IOException;
import java.util.List;

public interface LanguageService {
    LanguagesInfo getTrendingLanguages() throws IOException;
}
