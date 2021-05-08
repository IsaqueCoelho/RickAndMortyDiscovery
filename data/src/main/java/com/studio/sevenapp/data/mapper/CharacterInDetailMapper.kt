package com.studio.sevenapp.data.mapper

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.studio.sevenapp.data.model.CharacterInDetailDto
import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.domain.model.GenderEnum
import com.studio.sevenapp.domain.model.StatusEnum

class CharacterInDetailMapper(
    private val locationMapper: LocationMapper,
    private val episodeMapper: EpisodeMapper,
    private val gson: Gson
) : DomainMapper<CharacterInDetailDto, CharacterInDetail> {

    @SuppressLint("DefaultLocale")
    override fun mapToDomainModel(dto: CharacterInDetailDto): CharacterInDetail {
        return CharacterInDetail(
            id = dto.id,
            name = dto.name,
            status = StatusEnum.valueOf(dto.status.toUpperCase()),
            species = dto.species,
            type = dto.type,
            gender = GenderEnum.valueOf(dto.gender.toUpperCase()),
            origin = locationMapper.mapToDomainModel(dto.location),
            location = locationMapper.mapToDomainModel(dto.location),
            image = dto.image,
            episodes = episodeMapper.fromDtoList(dto.episodes)
        )
    }

    override fun mapToDto(domainModel: CharacterInDetail): CharacterInDetailDto {
        return CharacterInDetailDto(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status.value,
            species = domainModel.species,
            type = domainModel.type,
            gender = domainModel.gender.value,
            origin = domainModel.location.let { locationMapper.mapToDto(it) },
            location = domainModel.location.let { locationMapper.mapToDto(it) },
            image = domainModel.image,
            episodes = domainModel.episodes.let { episodeMapper.fromDomainData(it) }
        )
    }

    fun fromStringJsonToDto(json: String): CharacterInDetailDto {

        val jsonObject = JsonParser.parseString(json).asJsonObject

        val jsonArray = jsonObject
            .getAsJsonObject("data")
            .getAsJsonObject("character")
            .toString()

        val listType = object : TypeToken<CharacterInDetailDto>() {}.type

        return gson.fromJson(jsonArray, listType)
    }
}