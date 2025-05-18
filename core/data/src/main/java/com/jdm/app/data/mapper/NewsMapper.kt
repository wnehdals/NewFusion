package com.jdm.app.data.mapper

import com.jdm.app.data.model.NewsResp
import com.jdm.app.model.News

internal fun NewsResp.toDomain(idx: Int): News {
    return News(
        id = idx,
        date = 0L,
        category = category?: "",
        subject = subject?: "",
        title = title?: "",
        content = content?: "",
        source = source?: ""
    )
}