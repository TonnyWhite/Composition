package com.example.composition.domain.usecases

import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository

class GenerateQuestionsUseCase(private val repository: GameRepository) {
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, CONST_OF_OPTIONS)

    }

    private companion object{
        private const val CONST_OF_OPTIONS = 6
    }
}