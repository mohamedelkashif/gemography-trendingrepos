package com.gemography.trending.domainobjects;

import lombok.Data;

@Data
public class Items {
    private String url;
    private String name;
    private int stargazers_count;
    private int forks;
    private int watchers_count;
    private Owner owner;
    private String language;
}
