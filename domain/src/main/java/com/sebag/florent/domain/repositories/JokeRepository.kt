package com.sebag.florent.domain.repositories

import com.sebag.florent.domain.models.Joke
import io.reactivex.rxjava3.core.Single

interface JokeRepository {

    fun fetchRandomJoke() : Single<Joke>
}