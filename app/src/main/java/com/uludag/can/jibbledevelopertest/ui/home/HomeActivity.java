package com.uludag.can.jibbledevelopertest.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uludag.can.jibbledevelopertest.R;
import com.uludag.can.jibbledevelopertest.base.App;
import com.uludag.can.jibbledevelopertest.listeners.DisplayDataDetailListener;
import com.uludag.can.jibbledevelopertest.listeners.EditPostTitleListener;
import com.uludag.can.jibbledevelopertest.listeners.RecyclerItemTouchHelperListener;
import com.uludag.can.jibbledevelopertest.models.CombinedData;
import com.uludag.can.jibbledevelopertest.utils.RecyclerViewTouchHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements HomeActivityContract.View, RecyclerItemTouchHelperListener,
        EditPostTitleListener, DisplayDataDetailListener {

    private final static String LIST_STATE_KEY = "recycler_list_state";
    private final static String LIST_DATASET = "recycler_dataset";
    private final static String EDIT_TITLE_POSITION = "edit_title_position";
    private final static String DISPLAY_DATA_DETAIL_POSITION = "display_data_detail_position";
    private Parcelable listState;
    private int editTitlePosition = -1;
    private int displayDataDetailPosition = -1;

    // View injections
    @BindView(R.id.coordinatorContainer)
    CoordinatorLayout mCoordinatorContainer;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    // For the BottomSheet Edit Data
    @BindView(R.id.bottomSheetContainerEditData)
    ConstraintLayout mBottomSheetContainerEditData;
    @BindView(R.id.etEditPostTitle)
    EditText mEditPostTitle;
    @BindView(R.id.btnBottomSheetSave)
    Button mBtnBottomSheetSave;
    // For the BottomSheet Display Detail
    @BindView(R.id.bottomSheetContainerDisplayDetail)
    ConstraintLayout mBottomSheetContainerDisplayDetail;
    @BindView(R.id.tvDataDetailPostTitle)
    TextView tvDataDetailPostTitle;
    @BindView(R.id.tvDataDetailUser)
    TextView tvDataDetailUser;
    @BindView(R.id.tvDataDetailAlbumTitle)
    TextView tvDataDetailAlbumTitle;
    @BindView(R.id.tvDataDetailPostBody)
    TextView tvDataDetailPostBody;


    private ActionBar toolbar;

    // Dependency injections
    @Inject
    Context mContext;
    @Inject
    HomeActivityContract.Presenter mPresenter;

    private ArrayList<CombinedData> mDataList;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private BottomSheetBehavior mEditDataBottomSheetBehavior;
    private BottomSheetBehavior mDisplayDataBottomSheetBehavior;

    // For serialization of data
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Initialize the injection
        ((App) getApplication()).getAppComponent().inject(this);

        // Initialize ButterKnife view injections
        ButterKnife.bind(this);

        setActionBarTitle(getString(R.string.toolbar_title));
        setupBottomSheets();
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
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Add touch helper to recyclerview
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerViewTouchHelper(0,
                ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void refreshRecyclerView(@NonNull ArrayList<CombinedData> refreshedDataList) {
        mDataList = refreshedDataList;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void populateAdapter(@NonNull ArrayList<CombinedData> combinedDataList) {
        mDataList = combinedDataList;
        mAdapter = new RecyclerViewAdapter(mDataList, this, this);
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
    public void setupBottomSheets() {
        // Edit Data Bottom Sheet
        mEditDataBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetContainerEditData);
        toggleEditDataBottomSheet(false);
        mBtnBottomSheetSave.setOnClickListener(view -> {
            String newTitle = getBottomSheetInputData();
            mPresenter.updatePostTitle(newTitle, editTitlePosition, mDataList);
            // TODO:cu There is a bug. Bottom Sheet not closing after keyboard disappears
            toggleEditDataBottomSheet(false);
            hideSoftKeyboard();
        });

        // Display Data Bottom Sheet
        mDisplayDataBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheetContainerDisplayDetail);
        toggleDisplayDataBottomSheet(false);
    }

    @Override
    public void toggleEditDataBottomSheet(boolean state) {
        if (state) {
            mEditDataBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            mEditDataBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void toggleDisplayDataBottomSheet(boolean state) {
        if (state) {
            mDisplayDataBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            mDisplayDataBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void displaySnackBar(@NotNull String message) {
        Snackbar
                .make(mCoordinatorContainer, message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.snackbar_action_retry, view -> {
                    mPresenter.fetchCombinedData();
                })
                .show();
    }

    @Override
    public void setEditDataBottomSheetFields(int dataPosition) {
        mEditPostTitle.setText(mDataList.get(dataPosition).getPost().getTitle());
    }

    @NotNull
    @Override
    public String getBottomSheetInputData() {
        return mEditPostTitle.getText().toString();
    }

    @Override
    public void setDetailBottomSheetFields(int dataPosition) {
        CombinedData data = mDataList.get(dataPosition);
        tvDataDetailPostTitle.setText(data.getPost().getTitle());
        tvDataDetailUser.setText(data.getUser().getName());
        tvDataDetailAlbumTitle.setText(data.getAlbum().getTitle());
        tvDataDetailPostBody.setText(data.getPost().getBody());
    }

    @Override
    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
        if (listState != null) {
            mLayoutManager.onRestoreInstanceState(listState); // If configuration change happened
        } else {
            loadData(); // If it's the first time creation
        }
    }

    @Override
    public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction, int position) {
        mAdapter.removeItem(position);
    }

    @Override
    public void editPostTitle(int position) {
        editTitlePosition = position;
        setEditDataBottomSheetFields(position);
        toggleDisplayDataBottomSheet(false);
        toggleEditDataBottomSheet(true);
    }

    @Override
    public void displayDataDetail(int dataPosition) {
        displayDataDetailPosition = dataPosition;
        setDetailBottomSheetFields(dataPosition);
        toggleEditDataBottomSheet(false);
        mDisplayDataBottomSheetBehavior
                .setPeekHeight(180);
        toggleDisplayDataBottomSheet(true);
    }

    @Override
    public void onBackPressed() {
        if (mEditDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            toggleEditDataBottomSheet(false);
        } else if (mDisplayDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED
                || mDisplayDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            toggleDisplayDataBottomSheet(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the recyclerview state
        listState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);

        // Selected Data positions
        outState.putInt(EDIT_TITLE_POSITION, editTitlePosition);
        outState.putInt(DISPLAY_DATA_DETAIL_POSITION, displayDataDetailPosition);

        // Convert ArrayList to serialized string with GSON
        String serializedData = gson.toJson(mDataList);
        outState.putString(LIST_DATASET, serializedData);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore list state
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);

            editTitlePosition = savedInstanceState.getInt(EDIT_TITLE_POSITION);
            displayDataDetailPosition = savedInstanceState.getInt(DISPLAY_DATA_DETAIL_POSITION);

            // Convert serialized string to ArrayList with GSON
            String serializedData = savedInstanceState.getString(LIST_DATASET);
            mDataList = gson.fromJson(serializedData, new TypeToken<ArrayList<CombinedData>>() {
            }.getType());

            if (mDataList != null) {
                populateAdapter(mDataList);
            } else {
                loadData();
            }

            // Prevent keyboard opening due to edit text focus
            mEditPostTitle.clearFocus();

            // Check if any bottom sheet is displayed
            // If so fill the fields
            if (mDisplayDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED
                    || mDisplayDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                setDetailBottomSheetFields(displayDataDetailPosition);
            } else if (mEditDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED
                    || mEditDataBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                setEditDataBottomSheetFields(editTitlePosition);
            }

            hideSoftKeyboard();
        }
    }

}
