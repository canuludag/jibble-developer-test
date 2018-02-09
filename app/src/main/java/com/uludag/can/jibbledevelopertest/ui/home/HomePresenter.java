package com.uludag.can.jibbledevelopertest.ui.home;

import com.uludag.can.jibbledevelopertest.models.CombinedData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomePresenter implements HomeActivityContract.Presenter {

    private HomeActivityContract.View mView;
    private HomeActivityContract.Model mModel;

    public HomePresenter(HomeActivityContract.Model model) {
        mModel = model;
    }

    @Override
    public void setView(@NotNull HomeActivityContract.View view) {
        mView = view;
    }

    @NotNull
    @Override
    public List<CombinedData> createCombinedData() {
        return null;
    }
}
