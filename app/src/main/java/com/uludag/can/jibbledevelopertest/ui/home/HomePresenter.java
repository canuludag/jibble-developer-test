package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.models.CombinedData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePresenter implements HomeActivityContract.Presenter {
    @Override
    public void setView(@NotNull HomeActivityContract.View view) {

    }

    @NotNull
    @Override
    public List<CombinedData> getCombinedData() {
        return null;
    }
}
