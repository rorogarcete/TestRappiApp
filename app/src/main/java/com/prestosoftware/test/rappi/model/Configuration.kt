package com.prestosoftware.test.rappi.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "configurations")
data class Configuration(
    @Embedded val images: Images,
    @PrimaryKey var id: String)