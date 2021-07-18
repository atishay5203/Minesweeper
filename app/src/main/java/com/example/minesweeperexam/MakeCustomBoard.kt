package com.example.minesweeperexam

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_make_custom_board.*

class MakeCustomBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_custom_board)
        custom_board_info.setOnClickListener{
            val builder= AlertDialog.Builder(this)
            with(builder)
            {
                setTitle("Instructions")
                setMessage(
                        """Hi!
 To set up Your own custom Board, You need to provide the dimensions of your board,(preferably in the range of 5-20).
Also you need to provide the number of mines you wish to have in your mines board.
 Hope the Instructions are clear.
Enjoy the Game!! Good Luck!"""
                )
                setCancelable(false)
                setPositiveButton("Ok"
                ) { dialog, which -> }
            }
            val dialog= builder.create()
            dialog.show()
        }
        start_custom.setOnClickListener {
            // if the dimensions are not provided, toast will appear
            if(rows.text.isBlank()||columns.text.isBlank()||mines.text.isBlank())
            {
                Toast.makeText(this, "Details of Custom Board not provided",Toast.LENGTH_SHORT). show()
            }
            else
            {
                val rows= Integer.parseInt(rows.text.toString())
                val columns= Integer.parseInt(columns.text.toString())
                val mines= Integer.parseInt(mines.text.toString())
                val intent= Intent(this,MinesweeperGame::class.java).apply {
                    putExtra("rows",rows)
                    putExtra("columns",columns)
                    putExtra("mines",mines)
                    putExtra("boardType", "custom")
                }
                startActivity(intent)

            }
        }
    }
}