package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;

import com.uludag.can.jibbledevelopertest.R;
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

    private Context mContext;
    private HomeActivityContract.View mView;
    private HomeActivityContract.Model mModel;

    @Inject
    public HomePresenter(HomeActivityContract.Model model, Context context) {
        mModel = model;
        mContext = context;
    }

    @Override
    public void setView(@NotNull HomeActivityContract.View view) {
        mView = view;
    }

    @Override
    public void fetchCombinedData() {

        // Display progressbar
        mView.displayProgressbar();

        // Create an empty data list for adapter
        ArrayList<CombinedData> dataList = new ArrayList<>();

        // Get our observables for each request endpoint
        Observable<List<Post>> postsObservable = mModel.getPosts();
        Observable<List<Album>> albumsObservable = mModel.getAlbums();
        Observable<List<User>> usersObservable = mModel.getUsers();

        // TODO:cu An error occures while fetching data
        // Combine and fetch the data
        Observable.zip(postsObservable, albumsObservable, usersObservable
                , (postResponses, albumResponses, userResponses) -> {

            // For random index selection from results
            // Each endpoint request have different size of results
            Random rand = new Random();

            for (int i = 0; i < 30; i++) {
                int randomPostIndex = rand.nextInt(postResponses.size());
                int randomAlbumIndex = rand.nextInt(albumResponses.size());
                int randomUserIndex = rand.nextInt(userResponses.size());

                dataList.add(new CombinedData(postResponses.get(randomPostIndex),
                        albumResponses.get(randomAlbumIndex), userResponses.get(randomUserIndex)));
            }

            return dataList;
        }).doOnComplete(() -> {
            mView.populateAdapter(dataList);
            mView.hideProgressbar();
        }).doOnError(throwable -> {
            mView.hideProgressbar();
            mView.displaySnackBar(mContext.getString(R.string.snackbar_error_fetching_data));
            throwable.printStackTrace();
        }).onErrorReturn(error -> {
            mView.hideProgressbar();
            mView.displaySnackBar(mContext.getString(R.string.snackbar_error_fetching_data));
            error.printStackTrace();
            return new ArrayList<>();
        }).subscribe();
    }

    @Override
    public void updatePostTitle(@NonNull String title, int position, @NotNull ArrayList<CombinedData> dataList) {
        dataList.get(position).getPost().setTitle(title);
        mView.refreshRecyclerView(dataList);
    }
}
