package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.composition.R

@BindingAdapter("requiredAnswers")
fun  bindRequiredAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int){
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percent: Int){
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent
    )
}

@BindingAdapter("countOfRightAnswers", "countOfQuestions" )
fun bindScorePercentage(textView: TextView, countOfRightAnswers: Int, countOfQuestions: Int){
    textView.text = String.format(
       textView.context.getString(R.string.score_percentage),
       countOfRightAnswers.toDouble() /  countOfQuestions * 100
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean){
    if (winner){
        imageView.setImageResource(R.drawable.ic_smile)
    } else {
        imageView.setImageResource(R.drawable.ic_sad)
    }
}