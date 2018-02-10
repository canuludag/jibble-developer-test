package com.uludag.can.jibbledevelopertest;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.CombinedData;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;
import com.uludag.can.jibbledevelopertest.ui.home.HomeActivityContract;
import com.uludag.can.jibbledevelopertest.ui.home.HomePresenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePresenterTests {

    HomeActivityContract.Model mockHomeRepository;
    HomeActivityContract.View mockView;
    HomePresenter mHomePresenter;
    private ArrayList<CombinedData> mFakeDataSet;

    @Before
    public void setUp() throws Exception {

        // Setup mock model repository
        mockHomeRepository = mock(HomeActivityContract.Model.class);

        // Setup mock view
        mockView = mock(HomeActivityContract.View.class);

        // Implement presenter
        mHomePresenter = new HomePresenter(mockHomeRepository);
        mHomePresenter.setView(mockView);

        prepareFakeDataSet();
    }

    private void prepareFakeDataSet() {
        mFakeDataSet = new ArrayList<>();
        Post post = new Post();
        post.setTitle("Sample Title");
        post.setBody("This is sample post body for unit testing implementations.");

        Album album = new Album();
        album.setTitle("Album Title");

        User user = new User();
        user.setName("Can Uludag");

        for (int i = 0; i < 30; i++) {
            mFakeDataSet.add(new CombinedData(post, album, user));
        }
    }

    @Test
    public void updatingPostTitleShouldChangeTheExistingTitle() throws Exception {
        boolean isTitleUpdated;
        int dataPosition = 2;

        // When getBottomSheetInputData() called, return this string
        when(mockView.getBottomSheetInputData()).thenReturn("Updated Title");

        // Run the logic
        isTitleUpdated = mHomePresenter.updatePostTitle(mockView.getBottomSheetInputData(), dataPosition, mFakeDataSet);

        // Verify that mView.getBottomSheetInputData() method called only once
        verify(mockView, times(1)).getBottomSheetInputData();

        // Verify that mView.refreshRecyclerView() method called only once
        verify(mockView, times(1)).refreshRecyclerView(mFakeDataSet);

        // Check that update changed the post title
        assertTrue(isTitleUpdated);
    }

    @Test
    public void inputDataForPostTitleMustNotBeEmpty() throws Exception {
        boolean isValidInput;

        // When getBottomSheetInputData() called, return empty
        when(mockView.getBottomSheetInputData()).thenReturn("");

        // Run the logic
        isValidInput = mHomePresenter.editDataInputValidation(mockView.getBottomSheetInputData());

        // Verify that mView.getBottomSheetInputData() method called only once
        verify(mockView, times(1)).getBottomSheetInputData();

        assertTrue(!isValidInput);
    }
}
