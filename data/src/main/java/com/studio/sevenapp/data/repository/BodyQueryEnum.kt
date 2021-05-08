package com.studio.sevenapp.data.repository

enum class BodyQueryEnum(val value: String) {
    BODY_TYPE("application/json; charset=utf-8"),
    BODY_QUERY_PARAM("query"),
    GET_CHARACTERS_QUERY("query {characters{results{id,name,status,image}}}")
}