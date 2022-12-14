package com.example.myquizapp

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(),View.OnClickListener{

    lateinit var totalQuestionTextView:TextView
    lateinit var questionTextView:TextView
    lateinit var ansA:TextView
    lateinit var ansB:TextView
    lateinit var ansC:TextView
    lateinit var ansD:TextView
    lateinit var submitBtn:Button
    var score = 0
    var totalQuestion:Int = QuestionAnswer.question.size
    var currentQuestionIndex = 0
    var selectedAnswer = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalQuestionTextView = findViewById(R.id.total_question)
        questionTextView = findViewById(R.id.question)
        ansA = findViewById(R.id.ans_A)
        ansB = findViewById(R.id.ans_B)
        ansC = findViewById(R.id.ans_C)
        ansD = findViewById(R.id.ans_D)
        submitBtn = findViewById(R.id.submit_btn)

        ansA.setOnClickListener(this)
        ansB.setOnClickListener(this)
        ansC.setOnClickListener(this)
        ansD.setOnClickListener(this)
        submitBtn.setOnClickListener(this)
        totalQuestionTextView.text  = "Total questions is: $totalQuestion"
        loadNewQuestion()
    }



    override fun onClick( view: View) {
        ansA.setBackgroundColor(Color.parseColor("#002E5D"))
        ansB.setBackgroundColor(Color.parseColor("#002E5D"))
        ansC.setBackgroundColor(Color.parseColor("#002E5D"))
        ansD.setBackgroundColor(Color.parseColor("#002E5D"))


        ansA.setTextColor(Color.WHITE)
        ansB.setTextColor(Color.WHITE)
        ansC.setTextColor(Color.WHITE)
        ansD.setTextColor(Color.WHITE)
        val clickedButton = view as Button
        if (clickedButton.id == R.id.submit_btn){
            if ( selectedAnswer == QuestionAnswer.correctAnswers[currentQuestionIndex]){
                score ++
            }
            currentQuestionIndex ++
            loadNewQuestion()
        }else{
            selectedAnswer = clickedButton.text.toString()
            clickedButton.setBackgroundColor(Color.parseColor("#800020"))
            clickedButton.setTextColor(Color.WHITE)
        }

    }
    private fun loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion){
            finishQuiz()
            return
        }
        questionTextView.text = QuestionAnswer.question[currentQuestionIndex]
        ansA.text = QuestionAnswer.choices[currentQuestionIndex][0]
        ansB.text = QuestionAnswer.choices[currentQuestionIndex][1]
        ansC.text = QuestionAnswer.choices[currentQuestionIndex][2]
        ansD.text = QuestionAnswer.choices[currentQuestionIndex][3]
    }
    private fun finishQuiz(){
        var passStatus = ""
        passStatus = if (score > totalQuestion * 0.60){
            "Passed "
        }else{
            "Failed"
        }
        fun createDialog() {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Pass Status")
            alertDialogBuilder.setMessage("Score is $score out of $totalQuestion")
            alertDialogBuilder.setPositiveButton("Restart") { dialogInterface: DialogInterface, i: Int ->
                restartQuiz()
            }
            alertDialogBuilder.setCancelable(false)

            alertDialogBuilder.show()
        }
        createDialog()
    }

    private fun restartQuiz() {
        score = 0
        currentQuestionIndex = 0
        loadNewQuestion()
    }

}