package com.sebag.florent.data.repositories

import com.sebag.florent.data.api.IcnDB
import com.sebag.florent.domain.models.JokeModel
import com.sebag.florent.domain.repositories.JokeRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class JokeRepositoryImpl
@Inject constructor(
    private val service : IcnDB
) : JokeRepository {

    override fun fetchRandomJoke(): Single<JokeModel> {
        return service.randomJoke()
            .subscribeOn(Schedulers.io())
            .map { it.joke }
    }
}