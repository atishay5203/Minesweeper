package com.example.minesweeperexam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_stats.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //make custom directs user to the activity where user can input his own dimensions of the board
        make_custom?.setOnClickListener{
            val intent = Intent(this@MainActivity, MakeCustomBoard::class.java)
              startActivity(intent)
        }
        var level=-1
        //self indicates the board to be created itself by the system as per the difficulty chosen by user
        val boardType= "self"
        easy.setOnClickListener{
            level=0
        }
        medium.setOnClickListener{
            level=1
        }
       difficult.setOnClickListener{
            level=2
        }
        // start game will take the user to the game screen but will show an alert dialog ton the user if no difficulty is chosen,instructing the user to do so
        start_game?.setOnClickListener {
            if(level==-1)
            {
                 val builder= AlertDialog.Builder(this)
                with(builder)
                {
                    setTitle(getString(R.string.difficulty_message))
                    setMessage(getString(R.string.select_difficulty_message))
                    setCancelable(false)
                    setPositiveButton("Ok"
                    ) { dialog, which -> }
                }
                val dialog= builder.create()
                dialog.show()

            }
            else
            {
                val intent= Intent(this, MinesweeperGame::class.java).apply {
                    putExtra("Level",level)
                    putExtra("boardType", boardType)

                }
                startActivity(intent)
            }
        }
        // on clicking stats user will be directed to stats page where all the stats regarding game are visible
         stats?.setOnClickListener {
// these shared preferences are directed to main activity by the mine sweeper game activity
                sharedPreferences=
                    this.getSharedPreferences("Stats",Context.MODE_PRIVATE)

                val intent = Intent(this, GameStats::class.java).apply {
                    with(sharedPreferences) {
                        putExtra("bestTime",  getInt("best",0))
                       putExtra("lastTime",getInt("last",0))
                        putExtra("played", getInt("total",0))
                        putExtra("won",getInt("won",0))
                        putExtra("lost",getInt("lost",0))
                    }

                }

            startActivity(intent)
        }


    }
}