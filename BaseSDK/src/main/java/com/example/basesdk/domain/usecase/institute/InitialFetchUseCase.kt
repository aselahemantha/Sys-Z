package com.example.basesdk.domain.usecase.institute

import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.domain.repository.institute.ConfigurationRepository
import com.example.basesdk.util.Resource

class InitialFetchUseCase(
    private val repository: ConfigurationRepository,
) {
    suspend fun execute(): Resource<InstitutionConfig?> {
        return repository.initialFetchConfiguration()
    }
}
