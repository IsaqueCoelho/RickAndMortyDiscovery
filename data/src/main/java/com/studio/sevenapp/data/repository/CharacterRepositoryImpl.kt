package com.studio.sevenapp.data.repository

import com.google.gson.JsonObject
import com.studio.sevenapp.data.mapper.CharacterInDetailMapper
import com.studio.sevenapp.data.mapper.CharacterMapper
import com.studio.sevenapp.data.service.CharacterService
import com.studio.sevenapp.domain.character.BodyQueryEnum
import com.studio.sevenapp.domain.character.CharacterRepository
import com.studio.sevenapp.domain.model.CharacterInDetail
import com.studio.sevenapp.domain.model.CharacterPage
import okhttp3.MediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl
@Inject
constructor(
    private val characterService: CharacterService,
    private val jsonObject: JsonObject,
    private val mediaType: MediaType,
    private val characterMapper: CharacterMapper,
    private val characterInDetailMapper: CharacterInDetailMapper
) : CharacterRepository {

    override suspend fun getCharacters(page: Int): CharacterPage {

        val body = BodyQueryEnum.GET_CHARACTERS_QUERY.value.format(page)
        val queyParam = BodyQueryEnum.BODY_QUERY_PARAM.value

        // Pode dar problema no futuro =)
        jsonObject.addProperty(queyParam, body)

        val bodyRequest = jsonObject.toString()
            .toRequestBody(mediaType)

        val jsonInString: String = characterService.get(bodyRequest)

        val response = characterMapper.fromStringJsonToResponse(jsonInString)

        return CharacterPage(
            characters = characterMapper.fromDtoListToDomain(response.results),
            nextPage = response.info.next
        )
    }

    override suspend fun getCharacterDetail(id: Int): CharacterInDetail {

        val body = BodyQueryEnum.GET_CHARACTER_IN_DETAIL_QUERY.value.format(id)
        val queyParam = BodyQueryEnum.BODY_QUERY_PARAM.value

        // Pode dar problema no futuro =)
        jsonObject.addProperty(queyParam, body)

        val bodyRequest = jsonObject.toString()
            .toRequestBody(mediaType)

        val jsonInString: String = characterService.get(bodyRequest)

        val characterDto = characterInDetailMapper.fromStringJsonToDto(jsonInString)

        return characterInDetailMapper.mapToDomainModel(characterDto)
    }
}