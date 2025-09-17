
package com.example.cardsgame // your actual package name

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardsgame.LeaderboardAdapter
import com.example.cardsgame.LeaderboardEntry
import com.example.cardsgame.R
import com.google.firebase.database.*

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.leaderboard)

        recyclerView = findViewById(R.id.leaderboardRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchLeaderboardData()
    }

    private fun fetchLeaderboardData() {
        val ref = FirebaseDatabase.getInstance().getReference("leaderboard")
        ref.orderByChild("score").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val entries = snapshot.children.mapNotNull {
                    it.getValue(LeaderboardEntry::class.java)
                }.sortedByDescending { it.score }
                    .mapIndexed { index, entry -> index + 1 to entry }

                adapter = LeaderboardAdapter(entries)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LeaderboardActivity, "Failed to load leaderboard.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
