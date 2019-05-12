package com.github.jaydeepw.assignment01.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.github.jaydeepw.assignment01.Constants
import com.github.jaydeepw.assignment01.R
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.db.AppDatabase
import com.github.jaydeepw.assignment01.di.DaggerFragmentComponent
import com.github.jaydeepw.assignment01.di.PresenterModule
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import com.github.jaydeepw.assignment01.views.adapters.Adapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainFragment : Fragment(), MainContractInterface.View {

    @Inject
    lateinit var presenter: MainPresenter

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(activity?.applicationContext!!,
                AppDatabase::class.java, Constants.Companion.DB_NAME).build()

        val daggerFragmentComp = DaggerFragmentComponent.builder()
                .presenterModule(PresenterModule(this, AlbumRepository(activity?.application!!)))
                .build()

        daggerFragmentComp.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onGetData()
    }

    override fun showData(list: ArrayList<Album>?) {
        val adapter = Adapter(list, activity!!)
        val recycleListView = view?.findViewById<RecyclerView>(R.id.list_items)

        // Because this is a list of albums, makes more sense in using a GridLayoutManger instead
        // of the Linear layout manager.
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        recycleListView?.layoutManager = GridLayoutManager(activity, 2)

        // Access the RecyclerView Adapter and load the data into it
        recycleListView?.adapter = adapter
    }

    override fun showError(message: String) {
        if (view != null) {
            Snackbar.make(view!!, message, Snackbar.LENGTH_LONG)
        }
    }
}