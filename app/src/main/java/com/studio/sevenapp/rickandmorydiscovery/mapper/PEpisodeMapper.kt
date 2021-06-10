package com.studio.sevenapp.rickandmorydiscovery.mapper

import com.studio.sevenapp.domain.model.Episode
import com.studio.sevenapp.rickandmorydiscovery.model.PEpisode

class PEpisodeMapper : PresentationMapper<Episode, PEpisode> {

    override fun mapToPresentationModel(domainModel: Episode): PEpisode {
        return PEpisode(
            id = domainModel.id,
            name = domainModel.name,
            episodeCode = domainModel.episodeCode,
            airDate = domainModel.airDate
        )
    }

    override fun mapToDomainModel(presentationModel: PEpisode): Episode {
        return Episode(
            id = presentationModel.id,
            name = presentationModel.name,
            episodeCode = presentationModel.episodeCode,
            airDate = presentationModel.airDate
        )
    }

    fun mapToPresentationList(domainList: List<Episode>): List<PEpisode> {
        return domainList.map { mapToPresentationModel(it) }
    }

    fun mapToDomainList(presentationList: List<PEpisode>): List<Episode> {
        return presentationList.map { mapToDomainModel(it) }
    }
}