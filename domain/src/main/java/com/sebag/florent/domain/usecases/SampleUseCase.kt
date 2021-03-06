package com.sebag.florent.domain.usecases

import com.sebag.florent.domain.repositories.SampleRepository
import javax.inject.Inject

class SampleUseCase @Inject constructor(private val sampleRepository: SampleRepository) {

    fun fetchSampleData() : Int = sampleRepository.getSampleData()

}