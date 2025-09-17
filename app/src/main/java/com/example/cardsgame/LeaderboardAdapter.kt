package com.example.cardsgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardsgame.R // Replace with your actual package name
import com.example.cardsgame.LeaderboardEntry

class LeaderboardAdapter(private val entries: List<Pair<Int, LeaderboardEntry>>) :
    RecyclerView.Adapter<LeaderboardAdapter.EntryViewHolder>() {

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameText: TextView = itemView.findViewById(R.id.usernameText)
        val scoreText: TextView = itemView.findViewById(R.id.scoreText)
        val rankText: TextView = itemView.findViewById(R.id.rankText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val (rank, entry) = entries[position]
        holder.usernameText.text = entry.username
        holder.scoreText.text = "Score: ${entry.score}"
        holder.rankText.text = "#${rank}"
    }

    override fun getItemCount(): Int = entries.size
}
