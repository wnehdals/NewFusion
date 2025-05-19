package com.jdm.app.model

data class DialogToken(
    val title: String = "",
    val msg: String = "",
    val rightTextRes: Int,
    val leftTextRes: Int? = null,
    val metaData: Map<String, String> = mutableMapOf()
)
