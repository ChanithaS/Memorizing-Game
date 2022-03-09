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

    //storing the green button indexes in the generated random pattern
    private val answer = mutableListOf<String>()
    //two text views
    private lateinit var timerTxt : TextView
    private lateinit var score : TextView

    //array containing all the buttons
    private var buttonsAll = arrayOf(arrayOf<ImageView>())
    //initializing the time left for timer
    var timeleft:Int = 5
    //not letting the buttons on click to function
    var startGame = false
    //for no of correct answers and the no of clicks
    var correctAns = 0
    var noOfClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTxt = findViewById(R.id.timer)
        score = findViewById(R.id.textView)

        //creating 5 arrays containing buttons for each row
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
        //adding button rows containing array to buttonsAll array.. therefore creating a 2D array
        buttonsAll = arrayOf(buttons, buttons1, buttons2,buttons3,buttons4)
        //calling the function to initialize all buttons with on click listners
        listeners()
        //starting off the game
        randomGen()
    }

    private fun randomGen() {
        //generating a value randomly for gridSize  3×3, 3×4, 4×3, 5×5, 4×5, or 5×4.
        val gridSize = Random.nextInt(0,5)

        //going through all the buttons
        for ((index, images) in buttonsAll.withIndex()){
            //if gridSize is 0 th one ... meaning 3×3
            if(gridSize == 0){
                //setting the visibility of the buttons to GONE according to the pattern
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
            //if gridSize is 1 th one ... meaning 3×4
            else if (gridSize == 1){
                //setting the visibility of the buttons to GONE according to the pattern
                if (index == 3 || index == 4){
                    for (values in images){
                        values.visibility = View.GONE
                    }
                }
                else{
                    images[4].visibility = View.GONE
                }
            }
            //if gridSize is 2 th one ... meaning 4×3
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
            //if gridSize is 3 th one ... meaning 5×5
                //do nothing cause its set by default
            //if gridSize is 2 th one ... meaning 4×5
            else if(gridSize == 4){
                images[4].visibility = View.GONE
            }
            //if gridSize is 2 th one ... meaning 5×4
            else if (gridSize == 5){
                if (index == 4){
                    for (values in images){
                        values.visibility = View.GONE
                    }
                }
            }
        }
        //calling the timer
        timer(1)

        //another method to call the timer ... but cant display the timer count
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
        //increasing the timer and calling the timer function
        timeleft += 5
        timer(2)

        //another method to call the timer ... but cant display the timer count
//        val handler = Handler()
//        handler.postDelayed(Runnable {
//            //finding the respective button
//            removePattern()
//            startGame = true
//        }, 5000)
    }

    fun removePattern(){
        //changing all the images to normal image
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
                //getting the button from the 2d array changing its image to green
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
                //changing the button color to red with cross
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
            //displaying the no of correct answers
            score.text = " $correctAns / ${answer.size}"
            //displaying the no of attemps left
            timerTxt.text = "No.Of Attempts Left = ${answer.size - noOfClicks}"

            //if no of clicks clicked by user exceeds the attempts.. restarting the game
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

    private fun reset(){
        //resetting the game
        startGame = false
        //removing the pattern
        removePattern()
        //setting all buttons to visible
        for(arr in buttonsAll){
            for (buttons in arr){
                buttons.visibility = View.VISIBLE
            }
        }
        score.text = ""
        //clearing the answers containing array
        answer.clear()
        //adding time
        timeleft += 5
        //resetting score
        correctAns = 0
        //starting the main function
        randomGen()
    }

    private fun listeners(){
        //setting on click listeners to all the buttons
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
                                //removing the pattern
                                removePattern()
                                timerTxt.text = "Select the pattern"
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