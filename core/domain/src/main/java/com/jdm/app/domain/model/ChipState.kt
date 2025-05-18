package com.jdm.app.domain.model

import androidx.annotation.StringRes

data class ChipState(
    val id: String,
    @StringRes val  title: Int,
    val isChecked: Boolean = false
)
