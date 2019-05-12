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

    private lateinit var adapter: com.github.jaydeepw.assignment01.views.adapters.Adapter

    fun setAlbumsAdapter(list: ArrayList<Album>) {

        Collections.sort(list, object : Comparator<Album> {
            override fun compare(lhs: Album, rhs: Album): Int {
                return lhs.title.compareTo(rhs.title)
            }
        })

        adapter = Adapter(list, context!!)
        super.setAdapter(adapter)
    }

    fun updateItems(albums: List<Album>) {
        adapter.updateAll(albums)
    }
}