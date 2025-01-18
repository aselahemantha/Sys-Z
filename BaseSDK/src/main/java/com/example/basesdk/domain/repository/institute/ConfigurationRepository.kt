package com.example.basesdk.domain.repository.institute

import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.util.Resource


interface ConfigurationRepository {
    suspend fun initialFetchConfiguration(): Resource<InstitutionConfig?>

    suspend fun fetchConfiguration(): Resource<InstitutionConfig?>
}
