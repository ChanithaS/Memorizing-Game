package com.example.tutorial3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var but1 : ImageView
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


    //storing the button indexes in the generated random grid
    private val answer = mutableListOf<String>()
    private lateinit var timerTxt : TextView
    private lateinit var score : TextView

//    private var buttons = arrayOf<ImageView>()
//    private var buttons1 = arrayOf<ImageView>()
//    private var buttons2 = arrayOf<ImageView>()
//    private var buttons3 = arrayOf<ImageView>()
//    private var buttons4 = arrayOf<ImageView>()
    private var buttonsAll = arrayOf(arrayOf<ImageView>())
    var timeleft:Int = 5
    var startGame = false
    var correctAns = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTxt = findViewById(R.id.timer)
        score = findViewById(R.id.textView)

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

        buttonsAll = arrayOf(buttons, buttons1, buttons2,buttons3,buttons4)
        listeners()
        randomGen()
    }

    private fun randomGen() {
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
        timer(1)
    }
    private fun timer(i: Int) {
        //time to repeat every sec
        val period:Long  = 1000
        //setting timer to start boolean to true
        var timerStart = true

        //creating a timer
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                //checking if to start timer then only starting the time
                if (timerStart){
                    //decreasing time
                    timeleft--
                    //calling functions on a thread
                    runOnUiThread( Runnable()
                    {
                        //count down for displaying the timer after 5sec
                        if (i == 1){
                            timerTxt.text = "Pattern will Display in $timeleft"
                            if (timeleft == 0){
                                //printing thr pattern
                                pattern()
                                //stopping the timer
                                timerStart = false
                            }
                        }
                        //count down for removing the pattern
                        else if (i == 2){
                            timerTxt.text = "Time left to Memorize $timeleft"
                            if (timeleft == 0){
                                removePattern()
                                startGame = true
                                //stopping the timer
                                timerStart = false
                            }
                        }
                    })
                }
            }
        }, 0, period)
    }

    fun pattern(){
        val elements = mutableListOf<String>()
        //going through the double array
        for(arr in buttonsAll){
            for (buttons in arr){
                //if the element is visible adding the id of the element to elements array
                if (buttons.visibility == View.VISIBLE) {
                    elements.add(resources.getResourceEntryName(buttons.id))
                } else {
                    // Either gone or invisible
                }
            }
        }

        elements.shuffle()
        val patternSize = Random.nextInt(3,elements.size)

        for (n in 0..patternSize)
        {
            answer.add(elements[n])
            val name = elements[n]
            for(arr in buttonsAll){
                for (buttons in arr){
                   if (resources.getResourceEntryName(buttons.id) == name) {
                       buttons.setImageResource(R.drawable.correct)
                   }
                }
            }
        }
        timeleft += 5
        timer(2)
    }

    fun removePattern(){
        for(arr in buttonsAll){
            for (buttons in arr){
                buttons.setImageResource(R.drawable.start)
            }
        }
    }

    override fun onClick(view: View?) {
        if (startGame){
            var isThere = false
            val clickBtnName = resources.getResourceEntryName(view?.id!!)
            for (buttonIds in answer){
                if (clickBtnName == buttonIds){
                    isThere = true
                }
            }
            if (isThere){
                correctAns++
                for(arr in buttonsAll){
                    for (buttons in arr){
                        if (resources.getResourceEntryName(buttons.id) == clickBtnName) {
                            buttons.setImageResource(R.drawable.correct)
                        }
                    }
                }
            }else {
                for(arr in buttonsAll){
                    for (buttons in arr){
                        if (resources.getResourceEntryName(buttons.id) == clickBtnName) {
                            buttons.setImageResource(R.drawable.wrong)
                        }
                    }
                }
            }
        }
    }
    private fun listeners(){
        for(arr in buttonsAll) {
            for (buttons in arr) {
                buttons.setOnClickListener(this)
            }
        }
    }
}