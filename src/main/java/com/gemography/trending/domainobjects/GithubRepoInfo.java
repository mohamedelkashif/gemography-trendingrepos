package com.gemography.trending.domainobjects;

import lombok.Data;

import java.util.List;

@Data
public class GithubRepoInfo {
    private List<Items> items;

    private int total_count;

    private boolean incomplete_results;

}
