package com.studio.sevenapp.rickandmorydiscovery.mapper

interface PresentationMapper<DomainModel, PresentationModel> {
    fun mapToPresentationModel(domainModel: DomainModel): PresentationModel
    fun mapToDomainModel(presentationModel: PresentationModel): DomainModel
}