package com.uludag.can.jibbledevelopertest.ui.home;

import android.support.annotation.NonNull;

import com.uludag.can.jibbledevelopertest.models.Album;
import com.uludag.can.jibbledevelopertest.models.CombinedData;
import com.uludag.can.jibbledevelopertest.models.Post;
import com.uludag.can.jibbledevelopertest.models.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Observable;

public class HomePresenter implements HomeActivityContract.Presenter {

    private HomeActivityContract.View mView;
    private HomeActivityContract.Model mModel;

    @Inject
    public HomePresenter(HomeActivityContract.Model model) {
        mModel = model;
    }

    @Override
    public void setView(@NotNull HomeActivityContract.View view) {
        mView = view;
    }

    @Override
    public void fetchCombinedData() {

        // Display progressbar
        mView.displayProgressbar();
        // Hide Empty states
        mView.toggleEmptyStates(false);
        // Hide bottom sheets
        mView.toggleEditDataBottomSheet(false);
        mView.toggleDisplayDataBottomSheet(false);

        // Create an empty data list for adapter
        ArrayList<CombinedData> dataList = new ArrayList<>();

        // Get our observables for each request endpoint
        Observable<List<Post>> postsObservable = mModel.getPosts();
        Observable<List<Album>> albumsObservable = mModel.getAlbums();
        Observable<List<User>> usersObservable = mModel.getUsers();

        // Combine and fetch the data
        Observable.zip(postsObservable, albumsObservable, usersObservable
                , (postResponses, albumResponses, userResponses) -> {

                    // For random index selection from results
                    // Each endpoint request have different size of results
                    Random rand = new Random();

                    // We are populating data list randomly. Duplicate data may possible.
                    for (int i = 0; i < 30; i++) {
                        int randomPostIndex = rand.nextInt(postResponses.size());
                        int randomAlbumIndex = rand.nextInt(albumResponses.size());
                        int randomUserIndex = rand.nextInt(userResponses.size());

                        dataList.add(new CombinedData(postResponses.get(randomPostIndex),
                                albumResponses.get(randomAlbumIndex), userResponses.get(randomUserIndex)));
                    }

                    return dataList;
                }).doOnComplete(() -> {
            // Request completed, populate the adapter and list
            mView.populateAdapter(dataList);
            mView.hideProgressbar();
        }).onErrorReturn(error -> {
            mView.hideProgressbar();
            mView.toggleEmptyStates(true);
            // Inform the user about the request fail
            mView.displayFailSnackBar();
            error.printStackTrace();
            return new ArrayList<>();
        }).subscribe();
    }

    @Override
    public boolean updatePostTitle(@NonNull String title, int position, @NotNull ArrayList<CombinedData> dataList) {
        String oldTitle = dataList.get(position).getPost().getTitle();

        dataList.get(position).getPost().setTitle(title);
        mView.refreshRecyclerView(dataList);

        return !oldTitle.equals(dataList.get(position).getPost().getTitle());
    }

    @NonNull
    @Override
    public ArrayList<CombinedData> removeItemFromDataList(int position, @NonNull ArrayList<CombinedData> dataList) {
        if (!dataList.isEmpty()) {
            dataList.remove(position);
        }

        return dataList;
    }

    @Override
    public boolean editDataInputValidation(@NotNull String input) {
        return input.length() > 0;
    }
}
