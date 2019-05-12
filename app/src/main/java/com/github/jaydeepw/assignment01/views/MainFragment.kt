package com.github.jaydeepw.assignment01.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.assignment01.R
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.di.DaggerFragmentComponent
import com.github.jaydeepw.assignment01.di.PresenterModule
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import com.github.jaydeepw.assignment01.views.adapters.AnimalAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainFragment : Fragment(), MainContractInterface.View {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val daggerFragmentComp = DaggerFragmentComponent.builder()
                .presenterModule(PresenterModule(this))
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
        Toast.makeText(activity, "showing data from fragment " + list?.size, Toast.LENGTH_LONG).show()
        Log.d("", "ready to show data")
        val adapter = AnimalAdapter(list, activity!!)
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