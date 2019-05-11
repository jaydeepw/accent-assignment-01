package com.github.jaydeepw.assignment01.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.jaydeepw.assignment01.R
import com.github.jaydeepw.assignment01.contracts.MainContractInterface
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.presenters.MainPresenter
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), MainContractInterface.View {

    private var presenter: MainPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)

        presenter?.onData()
    }

    override fun showData(list: List<Album>?) {
        Toast.makeText(activity, "showing data from fragment " + list?.size, Toast.LENGTH_LONG).show()
        Log.d("", "ready to show data")
    }

    override fun showError(message: String) {
        if (view != null) {
            Snackbar.make(view!!, message, Snackbar.LENGTH_LONG)
        }
    }
}