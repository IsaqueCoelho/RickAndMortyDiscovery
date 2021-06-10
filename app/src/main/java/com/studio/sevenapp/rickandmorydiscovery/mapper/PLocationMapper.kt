package com.studio.sevenapp.rickandmorydiscovery.mapper

import com.studio.sevenapp.domain.model.Location
import com.studio.sevenapp.rickandmorydiscovery.model.PLocation

class PLocationMapper : PresentationMapper<Location, PLocation> {

    override fun mapToPresentationModel(domainModel: Location): PLocation {
        return PLocation(
            id = domainModel.id,
            name = domainModel.name,
            type = domainModel.type,
            dimension = domainModel.dimension
        )
    }

    override fun mapToDomainModel(presentationModel: PLocation): Location {
        return Location(
            id = presentationModel.id,
            name = presentationModel.name,
            type = presentationModel.type,
            dimension = presentationModel.dimension
        )
    }
}