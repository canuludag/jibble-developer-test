package com.uludag.can.jibbledevelopertest;

import com.uludag.can.jibbledevelopertest.ui.home.HomeActivityContract;
import com.uludag.can.jibbledevelopertest.ui.home.HomePresenter;

import org.junit.Before;

import static org.mockito.Mockito.mock;

public class HomePresenterTests {

    HomeActivityContract.Model mockHomeRepository;
    HomeActivityContract.View mockView;
    HomePresenter mHomePresenter;

    @Before
    public void setUp() throws Exception {

        // Setup mock model repository
        mockHomeRepository = mock(HomeActivityContract.Model.class);

        // Setup mock view
        mockView = mock(HomeActivityContract.View.class);

        // Implement presenter
        mHomePresenter = new HomePresenter(mockHomeRepository);
        mHomePresenter.setView(mockView);
    }


}
