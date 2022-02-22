package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level
import java.lang.RuntimeException
import androidx.activity.OnBackPressedCallback as OnBackPressedCallback1

class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("GameFinishedFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListener()
        bindView()

    }

    private fun setupOnClickListener(){
        val callback = object : OnBackPressedCallback1(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }

    }

    private fun bindView(){
        with(binding){
            if (gameResult.winner){
                emojiResult.setImageDrawable(ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_smile
                ))
            } else {
                emojiResult.setImageDrawable(ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_sad
                ))
            }

            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                gameResult.countOfRightAnswers.toDouble() /  gameResult.countOfQuestions * 100
            )

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgs(){
       requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

    }
    
    companion object{
        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}