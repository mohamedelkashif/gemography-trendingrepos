package com.gemography.trending.domainobjects;

import lombok.Data;

import java.util.List;

@Data
public class Languages {
    private List<Items> repositories;
    private String languageName;
    private int repositoriesCount;
}
