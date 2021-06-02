package com.example.application.repository;

public class AppRepository {
    private AppService appService;

    public AppRepository(AppService appService) {
        this.appService = appService;
    }
}
