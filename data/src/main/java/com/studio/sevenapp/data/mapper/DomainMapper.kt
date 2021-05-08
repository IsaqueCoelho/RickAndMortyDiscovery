package com.studio.sevenapp.data.mapper

interface DomainMapper<Dto, DomainModel> {
    fun mapToDomainModel(dto: Dto): DomainModel
    fun mapToDto(domainModel: DomainModel): Dto
}