package com.sebag.florent.data

import com.sebag.florent.domain.repositories.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor() : SampleRepository {

    override fun getSampleData(): Int {
        return (1)
    }
}