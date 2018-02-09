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

        fun toggleBottomSheet(state: Boolean)
    }

    interface Presenter {
        fun setView(view: HomeActivityContract.View)

        fun createCombinedData(): Observable<List<CombinedData>>

        fun getAllData(): List<CombinedData>
    }

    interface Model {
        fun fetchPosts(): Observable<List<Post>>

        fun fetchAlbums(): Observable<List<Album>>

        fun fetchUsers(): Observable<List<User>>
    }
}