/*
 * Created by Farhan Tanvir on 3/12/22, 1:21 AM
 * Last modified 3/12/22, 1:21 AM
 * Contact : farhantanvir65@gmail.com
 */

package com.farhan.tanvir.pexels.presentation.di

import com.farhan.tanvir.pexels.data.ImageApiService
import com.farhan.tanvir.pexels.data.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideImageRepository(imageApiService: ImageApiService): ImageRepository {
        return ImageRepository(imageApiService)
    }
}