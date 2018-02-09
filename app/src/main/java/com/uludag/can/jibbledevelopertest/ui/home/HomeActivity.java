package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
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
import com.uludag.can.jibbledevelopertest.listeners.RecyclerItemTouchHelperListener;
import com.uludag.can.jibbledevelopertest.models.CombinedData;
import com.uludag.can.jibbledevelopertest.utils.RecyclerViewTouchHelper;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements HomeActivityContract.View, RecyclerItemTouchHelperListener {

    // View injections
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

    // Dependency injections
    @Inject
    Context mContext;
    @Inject
    HomeActivityContract.Presenter mPresenter;

    private List<CombinedData> dataList;
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

        setupBottomSheet();
        setupRecyclerView();


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
    public void loadData() {

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
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction, int position) {
        mAdapter.removeItem(position);
    }
}
