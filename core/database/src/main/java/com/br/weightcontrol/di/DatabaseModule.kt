package com.br.weightcontrol.di

import androidx.room.Room.databaseBuilder
import com.br.weightcontrol.database.DatabaseMigrations
import com.br.weightcontrol.database.WeiDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        databaseBuilder(
            context = androidContext(),
            klass = WeiDatabase::class.java,
            name = "weightcontroldb"
        )
            .addMigrations(*DatabaseMigrations.getMigrations())
            .build()
    }

    single { get<WeiDatabase>().trackDao() }
    single { get<WeiDatabase>().goalDao() }
}