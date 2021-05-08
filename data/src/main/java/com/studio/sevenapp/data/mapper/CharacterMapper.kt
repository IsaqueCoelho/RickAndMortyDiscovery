package com.studio.sevenapp.data.mapper

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.studio.sevenapp.data.model.CharacterDto
import com.studio.sevenapp.domain.model.Character
import com.studio.sevenapp.domain.model.StatusEnum

class CharacterMapper(
    private val gson: Gson
) : DomainMapper<CharacterDto, Character> {

    @SuppressLint("DefaultLocale")
    override fun mapToDomainModel(dto: CharacterDto): Character {
        return Character(
            id = dto.id,
            name = dto.name,
            status = StatusEnum.valueOf(dto.status.toUpperCase()),
            image = dto.image
        )
    }

    override fun mapToDto(domainModel: Character): CharacterDto {
        return CharacterDto(
            id = domainModel.id,
            name = domainModel.name,
            status = domainModel.status.value,
            image = domainModel.image
        )
    }

    fun fromStringJsonToDtoList(json: String): List<CharacterDto> {

        val jsonObject = JsonParser.parseString(json).asJsonObject

        val jsonArray = jsonObject.getAsJsonObject("data")
            .getAsJsonObject("characters")
            .getAsJsonArray("results").toString()

        val listType = object : TypeToken<List<CharacterDto>>() {}.type

        return gson.fromJson(jsonArray, listType)
    }

    fun fromDtoListToDomain(dtoList: List<CharacterDto>) : List<Character>{
        return dtoList.map { mapToDomainModel(it) }
    }
}