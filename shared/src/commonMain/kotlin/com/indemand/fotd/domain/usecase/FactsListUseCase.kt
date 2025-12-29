package com.indemand.fotd.domain.usecase

import com.indemand.fotd.core.UseCase
import com.indemand.fotd.domain.model.FactDetails

interface FactsListUseCase : UseCase<Unit, List<FactDetails>>