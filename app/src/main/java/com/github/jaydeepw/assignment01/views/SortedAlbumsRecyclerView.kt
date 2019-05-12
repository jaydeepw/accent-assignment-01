package com.github.jaydeepw.assignment01.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.github.jaydeepw.assignment01.models.dataclasses.Album
import com.github.jaydeepw.assignment01.views.adapters.Adapter
import java.util.*

class SortedAlbumsRecyclerView : RecyclerView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var adapter: com.github.jaydeepw.assignment01.views.adapters.Adapter? = null

    /**
     * Set the parameter list to the adapter if not set already, update it otherwise.
     * In either situation, this method takes care of the ensuring that the list is sorted
     * as needed.
     */
    fun setOrUpdateAdapter(list: ArrayList<Album>) {

        // sort the list
        Collections.sort(list, object : Comparator<Album> {
            override fun compare(lhs: Album, rhs: Album): Int {
                return lhs.title.compareTo(rhs.title)
            }
        })

        if (adapter == null) {
            adapter = Adapter(list, context!!)
            super.setAdapter(adapter)
        } else {
            adapter = getAdapter() as com.github.jaydeepw.assignment01.views.adapters.Adapter
            adapter?.updateAll(list)
        }
    }

}