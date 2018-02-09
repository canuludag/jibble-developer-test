package com.uludag.can.jibbledevelopertest.ui.home

import com.uludag.can.jibbledevelopertest.models.Album
import com.uludag.can.jibbledevelopertest.models.CombinedData
import com.uludag.can.jibbledevelopertest.models.Post
import com.uludag.can.jibbledevelopertest.models.User
import io.reactivex.Observable

interface HomeActivityContract {
    interface View {
        fun setupRecyclerView()

        fun loadData()

        fun displayProgressbar()

        fun hideProgressbar()

        fun refreshRecyclerView()

        fun setupBottomSheet()

        fun toggleBottomSheet(state: Boolean)

        fun populateAdapter(dataList: MutableList<CombinedData>)

        fun displaySnackBar(message: String)

        fun setBottomSheetInputField(title: String)

        fun getBottomSheetInputData(): String
    }

    interface Presenter {
        fun setView(view: HomeActivityContract.View)

        fun fetchCombinedData()
    }

    interface Model {
        fun getPosts(): Observable<List<Post>>

        fun getAlbums(): Observable<List<Album>>

        fun getUsers(): Observable<List<User>>
    }
}