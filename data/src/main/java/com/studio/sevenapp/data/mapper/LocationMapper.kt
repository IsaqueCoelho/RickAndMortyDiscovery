package com.studio.sevenapp.data.mapper

import com.studio.sevenapp.data.model.LocationDto
import com.studio.sevenapp.domain.model.Location

class LocationMapper : DomainMapper<LocationDto, Location> {

    override fun mapToDomainModel(dto: LocationDto): Location {
        return Location(
            id = dto.id,
            name = dto.name,
            type = dto.type,
            dimension = dto.dimension
        )
    }

    override fun mapToDto(domainModel: Location): LocationDto {
        return LocationDto(
            id = domainModel.id,
            name = domainModel.name,
            type = domainModel.type,
            dimension = domainModel.dimension
        )
    }
}