package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val id: Int,
    val nextKey: Int?,
    val prevKey: Int?
)
