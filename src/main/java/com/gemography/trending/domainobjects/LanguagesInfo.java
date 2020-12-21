package com.gemography.trending.domainobjects;

import lombok.Data;

import java.util.List;

@Data
public class LanguagesInfo {
    private List<Languages> languages;
    private int languagesCount;
    private boolean incompleteResults;
}
