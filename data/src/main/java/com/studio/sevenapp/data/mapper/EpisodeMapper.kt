package com.studio.sevenapp.data.mapper

import com.studio.sevenapp.data.model.EpisodeDto
import com.studio.sevenapp.domain.model.Episode

class EpisodeMapper : DomainMapper<EpisodeDto, Episode> {
    override fun mapToDomainModel(dto: EpisodeDto): Episode {
        return Episode(
            id = dto.id,
            name = dto.name,
            episodeCode = dto.episodeCode
        )
    }

    override fun mapToDto(domainModel: Episode): EpisodeDto {
        return EpisodeDto(
            id = domainModel.id,
            name = domainModel.name,
            episodeCode = domainModel.episodeCode
        )
    }

    fun fromDtoList(dtoList: List<EpisodeDto>): List<Episode> {
        return dtoList.map { mapToDomainModel(it) }
    }

    fun fromDomainData(domainList: List<Episode>): List<EpisodeDto> {
        return domainList.map { mapToDto(it) }
    }
}