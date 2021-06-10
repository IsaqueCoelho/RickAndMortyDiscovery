package com.studio.sevenapp.rickandmorydiscovery.mapper

import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.rickandmorydiscovery.model.PCharacterInDetail

class PCharacterInDetailMapper(
    private val pLocationMapper: PLocationMapper,
    private val pEpisodeMapper: PEpisodeMapper
) : PresentationMapper<CharacterInDetail, PCharacterInDetail> {

    override fun mapToPresentationModel(domainModel: CharacterInDetail): PCharacterInDetail {
        return PCharacterInDetail(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            type = domainModel.type,
            gender = domainModel.gender,
            origin = pLocationMapper.mapToPresentationModel(domainModel.origin),
            location = pLocationMapper.mapToPresentationModel(domainModel.origin),
            image = domainModel.image,
            episodes = pEpisodeMapper.mapToPresentationList(domainModel.episodes)
        )
    }

    override fun mapToDomainModel(presentationModel: PCharacterInDetail): CharacterInDetail {
        return CharacterInDetail(
            id = presentationModel.id,
            name = presentationModel.name,
            status = presentationModel.status,
            species = presentationModel.species,
            type = presentationModel.type,
            gender = presentationModel.gender,
            origin = pLocationMapper.mapToDomainModel(presentationModel.origin),
            location = pLocationMapper.mapToDomainModel(presentationModel.origin),
            image = presentationModel.image,
            episodes = pEpisodeMapper.mapToDomainList(presentationModel.episodes)
        )
    }
}