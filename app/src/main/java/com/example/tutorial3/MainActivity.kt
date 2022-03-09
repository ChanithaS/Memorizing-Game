package com.example.tutorial3

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() , View.OnClickListener {

    //storing the button indexes in the generated random grid
    private val answer = mutableListOf<String>()
    private lateinit var timerTxt : TextView
    private lateinit var score : TextView

    private var buttonsAll = arrayOf(arrayOf<ImageView>())
    var timeleft:Int = 5
    var startGame = false
    var correctAns = 0
    var noOfClicks = 0

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
        timerTxt.text = "Pattern will be displayed in 5sec"
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
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            //finding the respective button
//            pattern()
//        }, 5000)
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
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            //finding the respective button
//            removePattern()
//            startGame = true
//        }, 5000)
    }

    fun removePattern(){
        for(arr in buttonsAll){
            for (buttons in arr){
                buttons.setImageResource(R.drawable.start)
            }
        }
        noOfClicks = 0
    }

    override fun onClick(view: View?) {
        //if starting the game is true
        if (startGame){
            //increasing the no.of clicks clicked by user
            noOfClicks++
            //setting is there boolean to false at start
            var isThere = false
            //getting the id of the button clicked
            val clickBtnName = resources.getResourceEntryName(view?.id!!)
            //checking for every id name stored in the answers array.. if clicked button is there
            for (buttonIds in answer){
                if (clickBtnName == buttonIds){
                    //if only setting is there to true
                    isThere = true
                }
            }
            //if the button is a correct answer
            if (isThere){
                //increasing correct answers
                correctAns++
                //getting the button from the 2d array changing its image
                for(arr in buttonsAll){
                    for (buttons in arr){
                        if (resources.getResourceEntryName(buttons.id) == clickBtnName) {
                            buttons.setImageResource(R.drawable.correct)
                        }
                    }
                }
                //changing the button to normal after 2 seconds
                changeBack(clickBtnName)
            }else {
                for(arr in buttonsAll){
                    for (buttons in arr){
                        if (resources.getResourceEntryName(buttons.id) == clickBtnName) {
                            buttons.setImageResource(R.drawable.wrong)
                        }
                    }
                }
                //changing the button to normal after 2 seconds
                changeBack(clickBtnName)
            }
            if (noOfClicks >= answer.size){
                reset()
            }
        }

    }
    private fun changeBack(clickBtnName: String) {
        //initializing a handler
        val handler = Handler()
        handler.postDelayed(Runnable {
            //finding the respective button
            for(arr in buttonsAll){
                for (buttons in arr){
                    if (resources.getResourceEntryName(buttons.id) == clickBtnName) {
                        //setting to default color
                        buttons.setImageResource(R.drawable.start)
                    }
                }
            }
        }, 2000)
    }
    fun reset(){
        startGame = false
        removePattern()
        for(arr in buttonsAll){
            for (buttons in arr){
                buttons.visibility = View.VISIBLE
            }
        }
        answer.clear()
        timeleft += 5
        randomGen()
    }

    private fun listeners(){
        for(arr in buttonsAll) {
            for (buttons in arr) {
                buttons.setOnClickListener(this)
            }
        }
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
                                //making the buttons intractable by setting the boolean to true
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

}