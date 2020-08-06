package com.sebag.florent.domain.usecases

import android.util.Log
import com.sebag.florent.domain.repositories.SampleRepository
import javax.inject.Inject

class SampleUseCase @Inject constructor(private val sampleRepository: SampleRepository) {

    fun fetchSampleData() : Int {
        return sampleRepository.getSampleData()
    }

}