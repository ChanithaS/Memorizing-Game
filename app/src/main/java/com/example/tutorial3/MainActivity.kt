package com.example.tutorial3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() , View.OnClickListener {

//    private lateinit var but1 : ImageView
//    private lateinit var but2 : ImageView
//    private lateinit var but3 : ImageView
//    private lateinit var but4 : ImageView
//    private lateinit var but5 : ImageView
//    private lateinit var but6 : ImageView
//    private lateinit var but7 : ImageView
//    private lateinit var but8 : ImageView
//    private lateinit var but9 : ImageView
//    private lateinit var but10 : ImageView
//    private lateinit var but11 : ImageView
//    private lateinit var but12 : ImageView
//    private lateinit var but13 : ImageView
//    private lateinit var but14 : ImageView
//    private lateinit var but15 : ImageView
//    private lateinit var but16 : ImageView
//    private lateinit var but17 : ImageView
//    private lateinit var but18 : ImageView
//    private lateinit var but19 : ImageView
//    private lateinit var but20 : ImageView
//    private lateinit var but21 : ImageView
//    private lateinit var but22 : ImageView
//    private lateinit var but23 : ImageView
//    private lateinit var but24 : ImageView
//    private lateinit var but25 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //listeners()

        val buttons = arrayOf(
            findViewById(R.id.imageButton1),
            findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3),
            findViewById(R.id.imageButton4),
            findViewById<ImageView>(R.id.imageButton5)
        )
        val buttons1 = arrayOf(
            findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7),
            findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9),
            findViewById<ImageView>(R.id.imageButton10)
        )
        val buttons2 = arrayOf(
            findViewById(R.id.imageButton11),
            findViewById(R.id.imageButton12),
            findViewById(R.id.imageButton13),
            findViewById(R.id.imageButton14),
            findViewById<ImageView>(R.id.imageButton15)
        )
        val buttons3 = arrayOf(
            findViewById(R.id.imageButton16),
            findViewById(R.id.imageButton17),
            findViewById(R.id.imageButton18),
            findViewById(R.id.imageButton19),
            findViewById<ImageView>(R.id.imageButton20)
        )
        val buttons4 = arrayOf(
            findViewById(R.id.imageButton21),
            findViewById(R.id.imageButton22),
            findViewById(R.id.imageButton23),
            findViewById(R.id.imageButton24),
            findViewById<ImageView>(R.id.imageButton25)
        )

        var buttonsAll = arrayOf(buttons, buttons1, buttons2,buttons3,buttons4)

        randomGen(buttonsAll)
    }

    private fun randomGen(buttonsAll: Array<Array<ImageView>>) {
        val gridSize = Random.nextInt(0,5)

        for ((index, images) in buttonsAll.withIndex()){
            if(gridSize == 0){
                    if (index == 0 || index == 4){
                        for (values in images){
                            values.visibility = View.GONE
                        }
                    }
                    else{
                        images[0].visibility = View.GONE
                        images[4].visibility = View.GONE
                    }
            }
            else if (gridSize == 1){
                    if (index == 3 || index == 4){
                        for (values in images){
                            values.visibility = View.GONE
                        }
                    }
                    else{
                        images[4].visibility = View.GONE
                    }
            }
            else if (gridSize == 2){
                    if (index == 4){
                        for (values in images){
                            values.visibility = View.GONE
                        }
                    }
                    else{
                        images[3].visibility = View.GONE
                        images[4].visibility = View.GONE
                    }
            }
            else if (gridSize == 3){
                return
            }
            else if(gridSize == 4){
                images[4].visibility = View.GONE
            }
            else if (gridSize == 5){
                if (index == 4){
                    for (values in images){
                        values.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onClick(view: View?) {

       // but1.visibility = View.GONE

    }
//    private fun listeners(){
//        but1 = findViewById(R.id.imageButton1)
//        but1.setOnClickListener(this)
//        but2= findViewById(R.id.imageButton2)
//        but2.setOnClickListener(this)
//        but3 = findViewById(R.id.imageButton3)
//        but3.setOnClickListener(this)
//        but4= findViewById(R.id.imageButton4)
//        but4.setOnClickListener(this)
//        but5 = findViewById(R.id.imageButton5)
//        but5.setOnClickListener(this)
//        but6= findViewById(R.id.imageButton6)
//        but6.setOnClickListener(this)
//        but7 = findViewById(R.id.imageButton7)
//        but7.setOnClickListener(this)
//        but8= findViewById(R.id.imageButton8)
//        but8.setOnClickListener(this)
//        but9 = findViewById(R.id.imageButton9)
//        but9.setOnClickListener(this)
//        but10= findViewById(R.id.imageButton10)
//        but10.setOnClickListener(this)
//        but11 = findViewById(R.id.imageButton11)
//        but11.setOnClickListener(this)
//        but12= findViewById(R.id.imageButton12)
//        but12.setOnClickListener(this)
//        but13 = findViewById(R.id.imageButton13)
//        but13.setOnClickListener(this)
//        but14= findViewById(R.id.imageButton14)
//        but14.setOnClickListener(this)
//        but15 = findViewById(R.id.imageButton15)
//        but15.setOnClickListener(this)
//        but16= findViewById(R.id.imageButton16)
//        but16.setOnClickListener(this)
//        but17 = findViewById(R.id.imageButton17)
//        but17.setOnClickListener(this)
//        but18= findViewById(R.id.imageButton18)
//        but18.setOnClickListener(this)
//        but19 = findViewById(R.id.imageButton19)
//        but19.setOnClickListener(this)
//        but20= findViewById(R.id.imageButton20)
//        but20.setOnClickListener(this)
//        but21 = findViewById(R.id.imageButton21)
//        but21.setOnClickListener(this)
//        but22= findViewById(R.id.imageButton22)
//        but22.setOnClickListener(this)
//        but23 = findViewById(R.id.imageButton23)
//        but23.setOnClickListener(this)
//        but24= findViewById(R.id.imageButton24)
//        but24.setOnClickListener(this)
//        but25 = findViewById(R.id.imageButton25)
//        but25.setOnClickListener(this)
//    }
}