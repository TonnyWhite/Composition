package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level
import java.lang.RuntimeException
import androidx.activity.OnBackPressedCallback as OnBackPressedCallback1

class GameFinishedFragment : Fragment() {


    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("GameFinishedFragment == null")


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

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }

    }

    private fun bindView(){
        binding.gameResult = args.gameResult
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    private fun retryGame() {
      findNavController().popBackStack()

    }
    

}