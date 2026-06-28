package com.studio.sevenapp.data.mapper

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.studio.sevenapp.data.model.CharacterDto
import com.studio.sevenapp.data.model.CharacterResponseObject
import com.studio.sevenapp.domain.model.Character
import com.studio.sevenapp.domain.model.StatusEnum
import java.util.*

class CharacterMapper(
    private val gson: Gson
) : DomainMapper<CharacterDto, Character> {

    @SuppressLint("DefaultLocale")
    override fun mapToDomainModel(dto: CharacterDto): Character {
        return Character(
            id = dto.id,
            name = dto.name,
            status = StatusEnum.valueOf(dto.status.uppercase(Locale.getDefault())),
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

    fun fromStringJsonToResponse(json: String): CharacterResponseObject {

        val jsonObject = JsonParser.parseString(json).asJsonObject

        val charactersObject = jsonObject.getAsJsonObject("data")
            .getAsJsonObject("characters")
            .toString()

        return gson.fromJson(charactersObject, CharacterResponseObject::class.java)
    }

    fun fromDtoListToDomain(dtoList: List<CharacterDto>) : List<Character>{
        return dtoList.map { mapToDomainModel(it) }
    }
}