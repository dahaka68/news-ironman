package com.dev.ironman.news.rest.restModels

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

@Entity
data class DBSource(

        @ColumnInfo(name = "name")
        val name: String? = null,

        @ColumnInfo(name ="id")
        val id: String? = null
)