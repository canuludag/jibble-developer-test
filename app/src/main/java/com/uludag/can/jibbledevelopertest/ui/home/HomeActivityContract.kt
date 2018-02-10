package com.uludag.can.jibbledevelopertest.ui.home

import com.uludag.can.jibbledevelopertest.models.Album
import com.uludag.can.jibbledevelopertest.models.CombinedData
import com.uludag.can.jibbledevelopertest.models.Post
import com.uludag.can.jibbledevelopertest.models.User
import io.reactivex.Observable

// Contains interface methods for the MVP Architecture
interface HomeActivityContract {
    interface View {
        fun setupRecyclerView()

        fun loadData()

        fun displayProgressbar()

        fun hideProgressbar()

        fun refreshRecyclerView(refreshedDataList: ArrayList<CombinedData>)

        fun setupBottomSheets()

        fun toggleEditDataBottomSheet(state: Boolean)

        fun toggleDisplayDataBottomSheet(state: Boolean)

        fun populateAdapter(dataList: ArrayList<CombinedData>)

        fun displaySnackBar(message: String)

        fun displayFailSnackBar()

        fun setEditDataBottomSheetFields(dataPosition: Int)

        fun getBottomSheetInputData(): String

        fun setDetailBottomSheetFields(dataPosition: Int)

        fun setActionBarTitle(title: String)

        fun hideSoftKeyboard()

        fun toggleEmptyStates(state: Boolean)
    }

    interface Presenter {
        fun setView(view: HomeActivityContract.View)

        fun fetchCombinedData()

        fun updatePostTitle(title: String, position: Int, dataList: ArrayList<CombinedData>): Boolean

        fun removeItemFromDataList(position: Int, dataList: ArrayList<CombinedData>): ArrayList<CombinedData>

        fun editDataInputValidation(input: String): Boolean
    }

    interface Model {
        fun getPosts(): Observable<List<Post>>

        fun getAlbums(): Observable<List<Album>>

        fun getUsers(): Observable<List<User>>
    }
}