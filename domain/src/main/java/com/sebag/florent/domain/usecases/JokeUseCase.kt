package com.sebag.florent.domain.usecases

import com.sebag.florent.domain.models.Joke
import com.sebag.florent.domain.repositories.JokeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class JokeUseCase
@Inject constructor(
    private val jokeRepository: JokeRepository
) {

    fun getRandomJoke() : Single<Joke> {
        return jokeRepository.fetchRandomJoke()
    }
}