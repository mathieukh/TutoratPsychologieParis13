package com.mathieukh.tutopsychop13.activity.news

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mathieukh.tutopsychop13.R
import com.mathieukh.tutopsychop13.Util.inflate
import com.mathieukh.tutopsychop13.data.News
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    lateinit private var newsViewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_news, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = CardAdapter(newsViewModel.mNews)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }
}

class CardAdapter(private val news: MutableLiveData<List<News>>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    override fun getItemCount(): Int = news.value?.size ?: 0

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.mDesc.text = news.value?.get(position)?.message
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(parent.inflate(R.layout.item_news))

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mDesc: TextView = itemView.findViewById(R.id.supportingText)
    }

}