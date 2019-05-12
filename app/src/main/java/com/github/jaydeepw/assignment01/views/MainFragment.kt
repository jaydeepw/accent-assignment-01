package com.github.jaydeepw.assignment01.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.github.jaydeepw.assignment01.Constants
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.db.AlbumRepository
import com.github.jaydeepw.assignment01.db.AppDatabase
import com.github.jaydeepw.assignment01.di.DaggerFragmentComponent
import com.github.jaydeepw.assignment01.di.PresenterModule
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import com.google.android.material.snackbar.Snackbar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class MainFragment : Fragment(), MainContractInterface.View {

    private var recycleListView: SortedAlbumsRecyclerView? = null
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
        return inflater.inflate(com.github.jaydeepw.assignment01.R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onGetData()
    }

    override fun showData(list: ArrayList<Album>?) {
        recycleListView = view?.findViewById(com.github.jaydeepw.assignment01.R.id.list_items)

        // Because this is a list of albums, makes more sense in using a GridLayoutManger instead
        // of the Linear layout manager.
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        recycleListView?.layoutManager = GridLayoutManager(activity, 2)

        // Access the RecyclerView Adapter and load the data into it
        recycleListView?.setAlbumsAdapter(list!!)
    }

    override fun showError(messageResId: Int) {
        if (view != null) {
            Snackbar.make(view!!, getString(messageResId), Snackbar.LENGTH_LONG)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(albums: List<Album>) {
        Toast.makeText(activity, "==> albums.size " + albums.size, Toast.LENGTH_SHORT).show()
        recycleListView?.updateItems(albums)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}