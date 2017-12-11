package com.mathieukh.tutopsychop13.activity.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.mathieukh.tutopsychop13.R
import com.mathieukh.tutopsychop13.Util.inflate
import com.mathieukh.tutopsychop13.data.entities.News
import kotlinx.android.synthetic.main.fragment_news.*

/*
* Fragment permettant d'afficher les news
 */
class NewsFragment : Fragment() {

    // Attribut permettant de stocker le ViewModel de News
    lateinit private var newsViewModel: NewsViewModel

    // Fonction appelée lors de la création de la vue
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_news)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // On pointe vers l'instance ViewModel de News qu'on attache au fragment
        newsViewModel = ViewModelProviders.of(activity!!).get(NewsViewModel::class.java)
        // On définit le LayoutManager du RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        // On définit l'adapter du RecyclerView
        recyclerView.adapter = CardAdapter(newsViewModel.mNews)
        // On définit le listener sur le swipe
        swipeRefreshLayout.setOnRefreshListener { newsViewModel.refreshNews() }
        // On écoute les changements sur les news
        newsViewModel.mNews.observe(this, Observer {
            recyclerView.adapter.notifyDataSetChanged()
        })
        // On écoute les changements sur la valeur loading qui permet de déterminer si un chargement de données est en cours
        newsViewModel.mLoading.observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it!!
        })
    }

    // Fonction static permettant d'instancier en singleton le Fragment
    companion object {
        fun newInstance() = NewsFragment()
    }
}
/*
* Classe CardAdapter permettant de créer l'adapter qui permettra d'afficher les news sur le RecyclerView
 */
class CardAdapter(private val news: LiveData<List<News>>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    // On compte la liste News ou on renvoie 0 si la liste est vue comme vide
    override fun getItemCount(): Int = news.value?.size ?: 0

    // On remplit le ViewHolder avec les données correctes
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        news.value?.get(position)?.let { new ->
            holder.mTitle.text = TimeAgo.using(new.published.time).trim().capitalize()
            holder.mDesc.text = new.message
        }
    }

    // On inflate le layout correspond pour les items du recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(parent.inflate(R.layout.item_news))

    // On définit les élements de notre ViewHolder en vue de le remplir pluss tard
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle: TextView = itemView.findViewById(R.id.titleText)
        var mDesc: TextView = itemView.findViewById(R.id.supportingText)
    }
}