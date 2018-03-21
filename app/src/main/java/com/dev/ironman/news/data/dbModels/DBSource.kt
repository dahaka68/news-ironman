package com.dev.ironman.news.data.dbModels

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class DBSource(
        @ColumnInfo(name = "i_d")
        @PrimaryKey(autoGenerate = true)
        var iD: Int = 0,
        @Ignore
        @ColumnInfo(name = "name")
        var name: String? = null,
        @Ignore
        @ColumnInfo(name = "id")
        var id: String? = null
)