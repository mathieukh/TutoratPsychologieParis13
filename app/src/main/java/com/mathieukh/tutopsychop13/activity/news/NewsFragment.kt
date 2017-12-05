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

/*
* Fragment permettant d'afficher les news
 */
class NewsFragment : Fragment() {

    // Attribut permettant de stocker le ViewModel de News
    lateinit private var newsViewModel: NewsViewModel

    // Fonction appelée lors de la création de la vue
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.fragment_news)

    // Fonction appelée lorsque la vue est créée
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // On pointe vers l'instance ViewModel de News
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        // On définit le LayoutManager du RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        // On définit l'adapter du RecyclerView
        recyclerView.adapter = CardAdapter(newsViewModel.mNews)
    }

    // Fonction static permettant d'instancier en singleton le Fragment
    companion object {
        fun newInstance() = NewsFragment()
    }
}

/*
* Classe CardAdapter permettant de créer l'adapter qui permettra d'afficher les news sur le RecyclerView
 */
class CardAdapter(private val news: MutableLiveData<List<News>>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    // On compte la liste News ou on renvoie 0 si la liste est vue comme vide
    override fun getItemCount(): Int = news.value?.size ?: 0

    // On remplit le ViewHolder avec les données correctes
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.mDesc.text = news.value?.get(position)?.message
    }

    // On inflate le layout correspond pour les items du recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(parent.inflate(R.layout.item_news))

    // On définit les élements de notre ViewHolder en vue de le remplir pluss tard
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mDesc: TextView = itemView.findViewById(R.id.supportingText)
    }

}