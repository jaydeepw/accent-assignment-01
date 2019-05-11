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
import com.github.jaydeepw.assignment01.presenters.MainPresenter

class MainFragment : Fragment(), MainContractInterface.View {

    var presenter: MainPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)

        presenter?.onData()
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showData(list: List<String>?) {
        Toast.makeText(activity, "showing data", Toast.LENGTH_LONG).show()
        Log.d("", "ready to show data")
    }
}