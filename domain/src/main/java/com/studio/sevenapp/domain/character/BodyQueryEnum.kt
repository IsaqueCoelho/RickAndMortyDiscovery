package com.studio.sevenapp.domain.character

enum class BodyQueryEnum(val value: String) {
    BODY_TYPE("application/json; charset=utf-8"),
    BODY_QUERY_PARAM("query"),
    GET_CHARACTERS_QUERY("query {characters{results{id,name,status,image}}}"),
    GET_CHARACTER_IN_DETAIL_QUERY("query {character(id: %d){name,status,species,type,gender,origin{id,name,type,dimension},location{id,name,type,dimension},image,episode{id,name,episode,air_date}}}")
}