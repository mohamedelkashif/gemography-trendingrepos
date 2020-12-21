package com.gemography.trending.services.impl;

import com.gemography.trending.domainobjects.GithubRepoInfo;
import com.gemography.trending.domainobjects.Items;
import com.gemography.trending.domainobjects.Languages;
import com.gemography.trending.domainobjects.LanguagesInfo;
import com.gemography.trending.services.LanguageService;
import com.google.gson.Gson;
import io.mikael.urlbuilder.UrlBuilder;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class LanguageServiceImpl implements LanguageService {

    private HttpClient httpClient;

    public LanguageServiceImpl() {
        try {
            SSLContext context = SSLContext.getInstance("TLSv1.3");
            context.init(null, null, null);

            httpClient = HttpClient.newBuilder()
                    .sslContext(context)
                    .connectTimeout(Duration.ofSeconds(15))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(15))
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();
        }
    }

    public LanguageServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public LanguagesInfo getTrendingLanguages() throws IOException {
        HttpRequest httpRequest = buildHttpRequest();
        LanguagesInfo languages = new LanguagesInfo();
        try {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            GithubRepoInfo githubRepoInfo = new Gson().fromJson(response.body(), GithubRepoInfo.class);

            Set<String> languageSet = new HashSet<>();
            for (Items items : githubRepoInfo.getItems()) {
                if (items.getLanguage() != null) {
                    languageSet.add(items.getLanguage());
                }
            }

            List<Languages> languageData = new ArrayList<>();
            for (String lang : languageSet) {
                Languages tempLang = new Languages();

                List<Items> repoInfo = getRepositoryOfLanguage(lang, githubRepoInfo.getItems());
                tempLang.setLanguageName(lang);
                tempLang.setRepositoriesCount(repoInfo.size());
                tempLang.setRepositories(repoInfo);
                languageData.add(tempLang);
            }
            languages.setLanguages(languageData);
            languages.setIncompleteResults(githubRepoInfo.isIncomplete_results());
            languages.setLanguagesCount(languageSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }

    private List<Items> getRepositoryOfLanguage(String languageName, List<Items> reposList) {
        List<Items> itemLanguagesList = new ArrayList<>();
        for (Items item : reposList) {
            Items tempRepo = new Items();
            if (item.getLanguage() != null && item.getLanguage().equals(languageName)) {
                tempRepo.setName(item.getName());
                tempRepo.setForks(item.getForks());
                tempRepo.setUrl(item.getUrl());
                tempRepo.setOwner(item.getOwner());
                tempRepo.setWatchers_count(item.getWatchers_count());
                tempRepo.setStargazers_count(item.getStargazers_count());
                tempRepo.setLanguage(languageName);
                itemLanguagesList.add(tempRepo);
            }
        }
        return itemLanguagesList;
    }

    private HttpRequest buildHttpRequest() {
        return HttpRequest.newBuilder()
                .uri(getUri())
                .GET()
                .build();
    }

    private URI getUri() {
        return UrlBuilder.empty()
                .withScheme("https")
                .withHost("api.github.com")
                .withPath("/search/repositories")
                .addParameter("q", "created:>2020-12-11")
                .addParameter("sort", "stars")
                .addParameter("order", "desc")
                .addParameter("per_page", "100")
                .toUri();
    }
}
