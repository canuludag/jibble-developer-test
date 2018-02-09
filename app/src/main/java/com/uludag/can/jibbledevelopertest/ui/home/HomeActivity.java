package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.uludag.can.jibbledevelopertest.R;
import com.uludag.can.jibbledevelopertest.base.App;
import com.uludag.can.jibbledevelopertest.listeners.EditPostTitleListener;
import com.uludag.can.jibbledevelopertest.listeners.RecyclerItemTouchHelperListener;
import com.uludag.can.jibbledevelopertest.models.CombinedData;
import com.uludag.can.jibbledevelopertest.utils.RecyclerViewTouchHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements HomeActivityContract.View, RecyclerItemTouchHelperListener, EditPostTitleListener {

    // View injections
    @BindView(R.id.coordinatorContainer)
    CoordinatorLayout mCoordinatorContainer;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    // For the BottomSheet
    @BindView(R.id.bottomSheetContainer)
    ConstraintLayout mBottomSheetContainer;
    @BindView(R.id.etEditPostTitle)
    EditText mEditPostTitle;
    @BindView(R.id.btnBottomSheetSave)
    Button mBtnBottomSheetSave;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private ActionBar toolbar;

    // Dependency injections
    @Inject
    Context mContext;
    @Inject
    HomeActivityContract.Presenter mPresenter;

    private List<CombinedData> mDataList;
    private RecyclerViewAdapter mAdapter;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Initialize the injection
        ((App) getApplication()).getAppComponent().inject(this);

        // Initialize ButterKnife view injections
        ButterKnife.bind(this);

        setActionBarTitle(getString(R.string.toolbar_title));
        setupBottomSheet();
        setupRecyclerView();

    }

    @Override
    public void setActionBarTitle(@NotNull String title) {
        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    @Override
    public void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // Add touch helper to recyclerview
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerViewTouchHelper(0,
                ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void refreshRecyclerView() {

    }

    @Override
    public void populateAdapter(@NonNull List<CombinedData> combinedDataList) {
        mDataList = combinedDataList;
        mAdapter = new RecyclerViewAdapter(mDataList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {
        mPresenter.fetchCombinedData();
    }

    @Override
    public void displayProgressbar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setupBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetContainer);
        toggleBottomSheet(false);
        mBtnBottomSheetSave.setOnClickListener(view -> {
            toggleBottomSheet(false);
        });
    }

    @Override
    public void toggleBottomSheet(boolean state) {
        if (state) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void displaySnackBar(@NotNull String message) {
        Snackbar.make(mCoordinatorContainer, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setBottomSheetInputField(@NotNull String title) {
        mEditPostTitle.setText(title);
    }

    @NotNull
    @Override
    public String getBottomSheetInputData() {
        return mEditPostTitle.getText().toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        loadData();
    }

    @Override
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction, int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void editPostTitle(int position) {
        setBottomSheetInputField(mDataList.get(position).getPost().getTitle());
        toggleBottomSheet(true);
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            toggleBottomSheet(false);
        } else {
            super.onBackPressed();
        }
    }
}
