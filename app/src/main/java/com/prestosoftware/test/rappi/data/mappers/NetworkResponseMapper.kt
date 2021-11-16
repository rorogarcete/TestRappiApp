package com.prestosoftware.test.rappi.data.mappers

import com.prestosoftware.test.rappi.data.response.NetworkResponseModel

interface NetworkResponseMapper<in FROM : NetworkResponseModel> {
    fun onLastPage(response: FROM): Boolean
}
