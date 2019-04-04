package ru.vsu.aviatickets;

import android.app.Application;

import ru.vsu.aviatickets.ticketssearch.providers.SkyScannerProviderAPI;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SkyScannerProviderAPI skyScannerProviderAPI = new SkyScannerProviderAPI();
        skyScannerProviderAPI.getTickets();
    }

}

