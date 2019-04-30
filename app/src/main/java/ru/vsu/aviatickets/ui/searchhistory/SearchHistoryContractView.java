package ru.vsu.aviatickets.ui.searchhistory;

public interface SearchHistoryContractView {
    void setupAdapter();
    void notifyRemoved(int index);
}
